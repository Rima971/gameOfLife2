import org.game.Grid;
import org.game.exceptions.GridNonPositiveParameters;
import org.game.exceptions.GridOutOfRangeSeedingPercentage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GridTest {
    @Test
    public void successfullyInstantiateGrid(){
        assertDoesNotThrow(()->new Grid(2,3,0.1));
    }

    @Test
    public void throwsExceptionOnPassingNonPositiveParameters(){
        assertThrows(GridNonPositiveParameters.class, ()->new Grid(-2, 3, 0.1));
        assertThrows(GridNonPositiveParameters.class, ()->new Grid(12, -23, 0.1));
        assertThrows(GridNonPositiveParameters.class, ()->new Grid(-16, -20, 0.1));
        assertThrows(GridNonPositiveParameters.class, ()->new Grid(0, 0, 0.1));
        assertThrows(GridNonPositiveParameters.class, ()->new Grid(1, 2, -0.7));
    }

    @Test
    public void throwsExceptionOnPassingSeedingPercentageBeyondTheRangeOf0And1(){
        assertThrows(GridOutOfRangeSeedingPercentage.class, ()->new Grid(2, 3, 0));
        assertThrows(GridOutOfRangeSeedingPercentage.class, ()->new Grid(12, 23, 1));
        assertThrows(GridOutOfRangeSeedingPercentage.class, ()->new Grid(0, 0, 0.1));
    }

    @Test
    public void grid_of_20x20_with_point2_seeding_percentage_generates_80_alive_cells_initially(){
        Grid grid = new Grid(20, 20, 0.2);
        // round(0.2 * 20 * 20)
        assertEquals(80, grid.cellsCount);
    }

    @Test
    public void grid_of_7x11_with_point1_seeding_percentage_generates_8_alive_cells_initially(){
        Grid grid = new Grid(7, 11, 0.1);
        // round( 0.1 * 7 * 11 )
        assertEquals(8, grid.cellsCount);
    }

    @Test
    public void grid_of_4x11_with_point1_seeding_percentage_generates_4_alive_cells_initially(){
        Grid grid = new Grid(4, 11, 0.1);
        // round( 0.1 * 4 * 11 )
        assertEquals(4, grid.cellsCount);
    }
}
