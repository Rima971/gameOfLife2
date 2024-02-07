import org.game.Cell;
import org.game.GridItem;
import org.game.enums.Event;
import org.game.stubs.PublisherStub;
import org.game.stubs.SubscriberStub;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GridItemTest {
    private final class GridItemChild extends GridItem {
        public GridItemChild(int row, int column){
            super(row, column);
        }

        public int getNeighbourCellCount(){
            return super.getSurroundingCellsCount();
        }

        public void resetNeighbouringCellCount(){
            super.resetSurroundingCellCount();
        }
    }
    @Test
    public void successfullyInstantiateGridItem(){
        assertDoesNotThrow(()->new GridItem(2,3));
    }

    @Test
    public void throwsExceptionOnInstantiatingWithNegativeCoordinates(){
        assertThrows(InvalidParameterException.class, ()->new GridItem(-2,3));
        assertThrows(InvalidParameterException.class, ()->new GridItem(12,-3));
        assertThrows(InvalidParameterException.class, ()->new GridItem(-12,-3));
    }
    @Test
    public void correctlyCountsNeighbours(){
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(2,4));
        cells.add(new Cell(4,4));
        cells.add(new Cell(1,4));
        GridItemChild item = new GridItemChild(3,4);
        cells.forEach(cell->cell.publish(Event.CELL_STATE, cell));

        assertEquals(2, item.getNeighbourCellCount());

        item.resetNeighbouringCellCount();
        cells.add(new Cell(2,5));
        cells.add(new Cell(2,6));
        cells.add(new Cell(2,3));
        cells.forEach(cell->cell.publish(Event.CELL_STATE, cell));

        assertEquals(4, item.getNeighbourCellCount());
    }
}
