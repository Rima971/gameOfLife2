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
    @Test
    public void successfullyInstantiateCell(){
        assertDoesNotThrow(()->new Void(2,3));
    }

    @Test
    public void dispatchesProduceEventWhenSurroundedBy3Cells(){
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(2,4));
        cells.add(new Cell(4,4));
        cells.add(new Cell(4,5));
        Void aVoid = spy(new Void(3,4));

        cells.forEach(cell -> aVoid.onEvent(Event.CELL_STATE, cell));
        aVoid.onEvent(Event.UPDATE_STATE, null);
        verify(aVoid, times(1)).publish(Event.CELL_PRODUCE, "3 4");
    }
    @Test
    public void doesNotDispatchesProduceEventWhenNotSurroundedByExactly3Cells(){
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(2,4));
        cells.add(new Cell(4,8)); // not neighbour
        cells.add(new Cell(4,5));
        Void aVoid = spy(new Void(3,4));

        cells.forEach(cell -> aVoid.onEvent(Event.CELL_STATE, cell));
        aVoid.onEvent(Event.UPDATE_STATE, null);
        verify(aVoid, never()).publish(Event.CELL_PRODUCE, "3 4");
    }
}
