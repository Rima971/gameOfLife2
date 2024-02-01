import org.game.GameRunner;
import org.game.enums.Event;
import org.game.stubs.SubscriberStub;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class GameRunnerTest {
    @Test
    public void periodicallySendsEventsOnRunning() throws InterruptedException {
        SubscriberStub stub = new SubscriberStub();
        GameRunner gameRunner = new GameRunner();
        gameRunner.execute(1,1,0.9);
        assertTrue(stub.check(Event.UPDATE_STATE, null));
    }
}
