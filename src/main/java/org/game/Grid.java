package org.game;

import org.game.enums.Event;
import org.game.exceptions.GridNonPositiveParameters;
import org.game.exceptions.GridOutOfRangeSeedingPercentage;
import org.game.gridItemTypes.Cell;
import org.game.gridItemTypes.Void;
import org.game.interfaces.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class Grid implements ISubscriber {
    private final int rows, columns, initiallyRequiredCellsCount;
    public int cellsCount = 0;
    private final GridItem[][] grid;
    private final List<Thread> threads = new ArrayList<>();

    public Grid(int rows, int columns, double seedingPercentage) {
        if (rows <= 0 || columns <= 0 || seedingPercentage <= 0)
            throw new GridNonPositiveParameters();
        if (seedingPercentage >= 1)
            throw new GridOutOfRangeSeedingPercentage();
        this.rows = rows;
        this.columns = columns;
        this.initiallyRequiredCellsCount = (int) Math.round(seedingPercentage * rows * columns);
        this.grid = new GridItem[rows][columns];
        this.initialize();
    }


    private void initialize() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.grid[i][j] = new Void(i, j);
            }
        }
        this.populateCellsRandomly();
        this.subscribe(Event.CELL_DESTROY, this);
        this.subscribe(Event.CELL_PRODUCE, this);
    }

    private void populateCellsRandomly() {
        while (this.cellsCount < this.initiallyRequiredCellsCount) {
            int randomRow = this.getRandomRow();
            int randomColumn = this.getRandomColumn();
            if (this.grid[randomRow][randomColumn].getClass() == Cell.class) {
                continue;
            }
            this.grid[randomRow][randomColumn] = new Cell(randomRow, randomColumn);
            this.cellsCount++;
        }
    }

    private int getRandomRow() {
        return (int) Math.floor(Math.random() * this.rows);
    }

    private int getRandomColumn() {
        return (int) Math.floor(Math.random() * this.columns);
    }

    public void implementThreads(){
        threads.forEach(Thread::run);
    }

    @Override
    public void onEvent(Event event, Object o) {
        if (o.getClass() != String.class) return;
        String[] coordinates = ((String) o).split(" ");
        int row = Integer.parseInt(coordinates[0]);
        int column = Integer.parseInt(coordinates[1]);
        switch (event) {
            case Event.CELL_DESTROY:
                threads.add(new Thread(()->{
                    this.grid[row][column] = new Void(row, column);
                    this.cellsCount--;
                }));
            case Event.CELL_PRODUCE:
                threads.add(new Thread(()->{
                    this.grid[row][column] = new Cell(row, column);
                    this.cellsCount++;
                }));

        }

    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (GridItem[] gridRow : this.grid) {
            for (GridItem item : gridRow) {
                output.append(item.toString());
            }
            output.append("\n");
        }
        return output.toString();
    }
}
