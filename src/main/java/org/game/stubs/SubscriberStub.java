package org.game.stubs;

import org.game.enums.Event;
import org.game.interfaces.ISubscriber;

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
        System.out.println(event + " " + o);
        this.event = event;
        this.payload = o;
    }

    public boolean check(Event event, Object o){
        return event == this.event && o.equals(this.payload);
    }
}
