import org.game.enums.Event;
import org.game.interfaces.IPublisher;
import org.game.interfaces.ISubscriber;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventBusTest {
    private static class PublisherStub implements IPublisher {
        public PublisherStub(){}
    }

    private static class SubscriberStub implements ISubscriber {
        public Event event;
        public Object payload;
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
    }

    private final PublisherStub publisherStub = new PublisherStub();
    private final SubscriberStub subscriberStub = new SubscriberStub();

    @Test
    public void publisherIsAbleToSendEvents(){
        assertDoesNotThrow(()->publisherStub.publish(Event.BROADCAST_STATE,null));
        assertDoesNotThrow(()->publisherStub.publish(Event.UPDATE_STATE,null));
        assertDoesNotThrow(()->publisherStub.publish(Event.CELL_STATE,null));
        assertDoesNotThrow(()->publisherStub.publish(Event.CELL_DESTROY,null));
    }

    @Test
    public void subscriberIsAbleToGetNotifiedOfEvents(){
        publisherStub.publish(Event.BROADCAST_STATE, null);
        assertEquals(Event.BROADCAST_STATE, subscriberStub.event);
        assertNull(subscriberStub.payload);

        publisherStub.publish(Event.CELL_STATE, publisherStub);
        assertEquals(Event.CELL_STATE, subscriberStub.event);
        assertEquals(publisherStub, subscriberStub.payload);
    }
}
