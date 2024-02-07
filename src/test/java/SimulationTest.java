import org.game.Simulation;
import org.game.enums.Event;
import org.junit.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SimulationTest {

    @Test
    public void periodicallySendsEventsOnRunning() throws InterruptedException {
        Simulation simulation = spy(new Simulation());
        simulation.run(1,1,0.9);

        verify(simulation).publish(Event.BROADCAST_STATE, null);
        verify(simulation).publish(Event.UPDATE_STATE, null);
    }
}
