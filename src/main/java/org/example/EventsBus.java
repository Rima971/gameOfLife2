package org.example;

import org.example.enums.Event;
import org.example.interfaces.ISubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventsBus {
    public static final EventsBus INSTANCE = new EventsBus();
    private static final HashMap<Event, List<ISubscriber>> subscribers = new HashMap<>();
    private EventsBus(){
        for (Event event: Event.values()){
            EventsBus.subscribers.put(event, new ArrayList<>());
        }
    }

    public void subscribe(Event event, ISubscriber subscriber){
        EventsBus.subscribers.get(event).add(subscriber);
    }

    public void sendEvent(Event event, Object o){
        EventsBus.subscribers.get(event).forEach((subscriber)->subscriber.onEvent(event, o));
    }

}
