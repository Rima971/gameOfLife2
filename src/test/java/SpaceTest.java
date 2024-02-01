import org.game.Cell;
import org.game.Space;
import org.game.enums.Event;
import org.game.stubs.PublisherStub;
import org.game.stubs.SubscriberStub;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpaceTest {
    private final PublisherStub publisherStub = new PublisherStub();
    private final SubscriberStub subscriberStub = new SubscriberStub();
    @Test
    public void successfullyInstantiateCell(){
        assertDoesNotThrow(()->new Space(2,3));
    }

    @Test
    public void dispatchesProduceEventOnReceivingUpdateStateEvent(){
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(2,4));
        cells.add(new Cell(4,4));
        cells.add(new Cell(4,5));
        Space space = new Space(3,4);

        cells.forEach(cell->cell.publish(Event.CELL_STATE, cell));
        System.out.println(space.getSurroundingCellsCount());
        publisherStub.publish(Event.UPDATE_STATE, null);

        assertTrue(subscriberStub.check(Event.CELL_PRODUCE, "3 4"));
    }
}
