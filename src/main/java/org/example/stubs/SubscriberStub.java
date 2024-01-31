package org.example.stubs;

import org.example.enums.Event;
import org.example.interfaces.ISubscriber;

public class SubscriberStub implements ISubscriber {
    private Event event;
    private Object payload;
    public SubscriberStub(){
        for (Event event: Event.values()){
            this.subscribe(event, this);
        }
    }

    @Override
    public void onEvent(Event event, Object o) {
        this.event = event;
        this.payload = o;
    }

    public boolean check(Event event, Object o){
        return event == this.event && o == this.payload;
    }
}
