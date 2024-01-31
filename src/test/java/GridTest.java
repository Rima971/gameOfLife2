import org.example.Grid;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GridTest {
    @Test
    public void successfullyInstantiateGrid(){
        assertDoesNotThrow(()->new Grid(2,3,0.1));
    }

    @Test
    public void throwsExceptionOnPassingNonPositiveParameters(){
        assertThrows(InvalidParameterException.class, ()->new Grid(-2, 3, 0.1));
        assertThrows(InvalidParameterException.class, ()->new Grid(12, -23, 0.1));
        assertThrows(InvalidParameterException.class, ()->new Grid(-16, -20, 0.1));
        assertThrows(InvalidParameterException.class, ()->new Grid(0, 0, 0.1));
        assertThrows(InvalidParameterException.class, ()->new Grid(1, 2, -0.7));
    }
}
