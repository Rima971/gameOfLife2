import org.game.gridItemTypes.Cell;
import org.game.gridItemTypes.Void;
import org.game.enums.Event;
import org.game.stubs.PublisherStub;
import org.game.stubs.SubscriberStub;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VoidTest {
    private final PublisherStub publisherStub = new PublisherStub();
    private final SubscriberStub subscriberStub = new SubscriberStub();
    @Test
    public void successfullyInstantiateCell(){
        assertDoesNotThrow(()->new Void(2,3));
    }

    @Test
    public void dispatchesProduceEventOnReceivingUpdateStateEvent(){
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(2,4));
        cells.add(new Cell(4,4));
        cells.add(new Cell(4,5));
        Void aVoid = spy(new Void(3,4));

        cells.forEach(cell -> cell.publish(Event.CELL_STATE, cell));
        publisherStub.publish(Event.UPDATE_STATE, null);
        verify(aVoid).publish(Event.CELL_PRODUCE, "3 4");
        assertEquals(subscriberStub.event,Event.CELL_PRODUCE);
        assertEquals(subscriberStub.payload, "3 4");
    }
}
