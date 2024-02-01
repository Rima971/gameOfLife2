package org.game.interfaces;

import org.game.EventsBus;
import org.game.enums.Event;

public interface IPublisher {
    default void publish(Event event, Object o){
        EventsBus.INSTANCE.sendEvent(event, o);
    }
}
