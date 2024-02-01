package org.game;

import org.game.enums.Event;
import org.game.interfaces.IPublisher;
import org.game.interfaces.ISubscriber;

import java.security.InvalidParameterException;

public class GridItem implements IPublisher, ISubscriber {
    private final int row, column;
    private int surroundingCellsCount = 0;
    public GridItem(int row, int column){
        if (row<0 || column<0) throw new InvalidParameterException("Cell cannot be created with negative parameters");
        this.row = row;
        this.column = column;
        this.subscribe(Event.CELL_STATE, this);
    }

    @Override
    public void onEvent(Event event, Object o) {
        if (o == null || o.getClass() != this.getClass()) return;
        this.checkForNeighbours((GridItem) o);
    }

    private void checkForNeighbours(GridItem item){
        if (this == item || (this.row == item.row && this.column == item.column)) return;
        if ((Math.abs(item.row-this.row) == 1 || Math.abs(item.row-this.row) == 0) && (Math.abs(item.column-this.column) == 1 || Math.abs(item.column-this.column) == 0)){
            this.surroundingCellsCount++;
        }
    };

    public int getSurroundingCellsCount(){
        return this.surroundingCellsCount;
    }

    public void resetSurroundingCellCount(){
        this.surroundingCellsCount = 0;
    }

    @Override
    public String toString(){
        return this.row + " " + this.column;
    }
}
