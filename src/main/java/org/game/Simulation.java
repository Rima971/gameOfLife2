package org.game;

import org.game.enums.Event;
import org.game.interfaces.IPublisher;

import static java.lang.Thread.sleep;

public class Simulation implements IPublisher {
    private final IO io = new IO();
    public Simulation(){}
    public void run(int rows, int columns, double seedingPercentage) throws InterruptedException {
        Grid grid = new Grid(rows, columns, seedingPercentage);
        while (grid.cellsCount>0){
            io.clearScreenLinux();
            this.publish(Event.BROADCAST_STATE, null);
            sleep(500);
            this.publish(Event.UPDATE_STATE, null);
            sleep(500);
            grid.implementThreads();
            sleep(500);
            io.printLine(grid.toString());
        }

    }

    public void display() throws InterruptedException {
        io.printLine("Welcome to the Game Of Life! \n");
        io.printLine("Enter the number of rows: ");
        int rows = io.scanner.nextInt();
        io.printLine("Enter the number of columns: ");
        int columns = io.scanner.nextInt();
        io.printLine("Enter the seeding percentage (0-1): ");
        double seedingPercentage = io.scanner.nextDouble();
        this.run(rows, columns, seedingPercentage);
    }
}
