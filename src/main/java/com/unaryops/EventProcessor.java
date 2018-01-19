package com.unaryops;

import com.unaryops.Transactional;

import java.awt.*;

@Transactional
public class EventProcessor implements Processor {
    public void processEvent(Event event) {
        System.out.println("Processing event: " + event.name);
        throw new NullPointerException("Roll back");
    }
}
