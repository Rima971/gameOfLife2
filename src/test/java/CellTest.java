import org.example.Cell;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CellTest {
    @Test
    public void successfullyInstantiateCell(){
        assertDoesNotThrow(()->new Cell(2,3));
    }

    @Test
    public void throwsExceptionOnInstantiatingWithNegativeCoordinates(){
        assertThrows(InvalidParameterException.class, ()->new Cell(-2,3));
        assertThrows(InvalidParameterException.class, ()->new Cell(12,-3));
        assertThrows(InvalidParameterException.class, ()->new Cell(-12,-3));
    }
}
