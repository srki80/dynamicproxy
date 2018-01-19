package com.unaryops;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventProcessorProxy implements InvocationHandler {
    private final Map<String, Method> methods = new HashMap<>();

    private Object target;
    private boolean transactional = false;

    public EventProcessorProxy(Object target) {
        this.target = target;

        Annotation[] declaredAnnotations = target.getClass().getDeclaredAnnotations();

        for (Annotation annotation : declaredAnnotations) {
            if (annotation.toString().equals("Transactional")) {
                transactional = true;
            }
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        try {
            long start = System.nanoTime();
            result = method.invoke(target, args);
            long elapsed = System.nanoTime() - start;
            System.out.println("Elapsed: " + elapsed);
        } catch (Exception e) {
            System.out.println("Rolling back");
        }
        return result;
    }
}
