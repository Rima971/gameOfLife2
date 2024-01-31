package org.example.interfaces;

import org.example.EventsBus;
import org.example.enums.Event;

public interface IPublisher {
    default void publish(Event event, Object o){
        EventsBus.INSTANCE.sendEvent(event, o);
    }
}
