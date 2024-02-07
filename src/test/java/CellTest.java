import org.game.gridItemTypes.Cell;
import org.game.enums.Event;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CellTest {
    @Test
    public void successfullyInstantiateCell(){
        assertDoesNotThrow(()->new Cell(2,3));
    }

    @Test
    public void publishesItsStateOnReceivingBroadcastStateEvent(){
        Cell cell = spy(new Cell(3,4));
        cell.onEvent(Event.BROADCAST_STATE, null);

        verify(cell, times(1)).publish(Event.CELL_STATE, cell);
    }

    @Test
    public void updatesItsStateOnReceivingUpdateStateEvent(){
        Cell cell = spy(new Cell(3,4));
        cell.onEvent(Event.UPDATE_STATE, null);

        verify(cell, times(1)).publish(Event.CELL_DESTROY, "3 4");
    }
}
