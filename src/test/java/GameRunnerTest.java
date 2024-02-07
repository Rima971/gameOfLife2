import org.game.GameRunner;
import org.game.enums.Event;
import org.game.stubs.SubscriberStub;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class GameRunnerTest {

    @Test
    public void periodicallySendsEventsOnRunning() throws InterruptedException {
        GameRunner gameRunner = spy(new GameRunner());
        gameRunner.execute(1,1,0.9);

        verify(gameRunner).publish(Event.BROADCAST_STATE, null);
    }
}
