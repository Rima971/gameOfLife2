package org.game.interfaces;

import org.game.EventsBus;
import org.game.enums.Event;

public interface ISubscriber {
    default void subscribe(Event event, ISubscriber subscriber){
        EventsBus.INSTANCE.subscribe(event, subscriber);
    }
    void onEvent(Event event, Object o);
}
