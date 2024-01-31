package org.example.interfaces;

import org.example.EventsBus;
import org.example.enums.Event;

public interface ISubscriber {
    default void subcribe(Event event, ISubscriber subscriber){
        EventsBus.INSTANCE.subscribe(event, subscriber);
    }
    void onEvent(Event event, Object o);
}
