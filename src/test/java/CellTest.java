import org.game.Cell;
import org.game.enums.Event;
import org.game.stubs.PublisherStub;
import org.game.stubs.SubscriberStub;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CellTest {
    private final PublisherStub publisherStub = new PublisherStub();
    private final SubscriberStub subscriberStub = new SubscriberStub();
    @Test
    public void successfullyInstantiateCell(){
        assertDoesNotThrow(()->new Cell(2,3));
    }

    @Test
    public void publishesItsStateOnReceivingBroadcastStateEvent(){
        Cell cell = new Cell(3,4);
        this.publisherStub.publish(Event.BROADCAST_STATE, null);
        assertTrue(this.subscriberStub.check(Event.CELL_STATE, cell));
    }

    @Test
    public void updatesItsStateOnReceivingUpdateStateEvent(){
        new Cell(3,4);
        publisherStub.publish(Event.UPDATE_STATE, null);
        assertTrue(subscriberStub.check(Event.CELL_DESTROY, "3 4"));
    }
}
