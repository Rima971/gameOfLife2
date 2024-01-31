package org.example;

import org.example.enums.Event;
import org.example.interfaces.IPublisher;

import java.util.concurrent.ScheduledExecutorService;

import static java.lang.Thread.sleep;

public class Executor implements IPublisher {
    public Executor(){}

    public void run(int row, int column, double seedingPercentage) throws InterruptedException {
        Grid grid = new Grid(row, column, seedingPercentage);
        while (grid.cellsCount>0){
            this.publish(Event.BROADCAST_STATE, null);
            sleep(500);
            this.publish(Event.UPDATE_STATE, null);
            sleep(500);
        }

    }
}
