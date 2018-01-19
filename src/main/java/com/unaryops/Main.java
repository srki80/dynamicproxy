package com.unaryops;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Processor eventProcessorProxy = (Processor) Proxy.newProxyInstance(
                Main.class.getClassLoader(), new Class[] { Processor.class },
                new EventProcessorProxy(new EventProcessor()));
        eventProcessorProxy.processEvent(new Event("Event 1"));
    }
}
