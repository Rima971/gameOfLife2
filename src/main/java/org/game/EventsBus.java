package org.game;

import org.game.enums.Event;
import org.game.interfaces.ISubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventsBus {
    public static final EventsBus INSTANCE = new EventsBus();
    private final HashMap<Event, List<ISubscriber>> subscribers = new HashMap<>();
    private EventsBus(){
        for (Event event: Event.values()){
            this.subscribers.put(event, new ArrayList<>());
        }
    }

    public void subscribe(Event event, ISubscriber subscriber){
        this.subscribers.get(event).add(subscriber);
    }

    public void sendEvent(Event event, Object o){
        this.subscribers.get(event).forEach((subscriber)->subscriber.onEvent(event, o));
    }

}
