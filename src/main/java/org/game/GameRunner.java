package org.game;

import org.game.enums.Event;
import org.game.interfaces.IPublisher;

import static java.lang.Thread.sleep;

public class GameRunner implements IPublisher {
    public GameRunner(){}

    public void execute(int row, int column, double seedingPercentage) throws InterruptedException {
        Grid grid = new Grid(row, column, seedingPercentage);
        int i = 0;
        while (grid.cellsCount>0 && i<1){
            this.publish(Event.BROADCAST_STATE, null);
            sleep(500);
            this.publish(Event.UPDATE_STATE, null);
            sleep(500);
            i++;
        }

    }
}
