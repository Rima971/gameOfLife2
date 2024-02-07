import org.game.Cell;
import org.game.DeadCell;
import org.game.GridItem;
import org.game.enums.Event;
import org.game.stubs.PublisherStub;
import org.game.stubs.SubscriberStub;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DeadCellTest {
    private final PublisherStub publisherStub = new PublisherStub();
    private final SubscriberStub subscriberStub = new SubscriberStub();
    @Test
    public void successfullyInstantiateCell(){
        assertDoesNotThrow(()->new DeadCell(2,3));
    }

    @Test
    public void dispatchesProduceEventOnReceivingUpdateStateEvent(){
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(2,4));
        cells.add(new Cell(4,4));
        cells.add(new Cell(4,5));
        DeadCell deadCell = spy(new DeadCell(3,4));

        cells.forEach(cell -> cell.publish(Event.CELL_STATE, cell));
        publisherStub.publish(Event.UPDATE_STATE, null);
//        verify(deadCell).publish(Event.CELL_PRODUCE, "3 4");
        assertTrue(subscriberStub.check(Event.CELL_PRODUCE, "3 4"));

    }
}
