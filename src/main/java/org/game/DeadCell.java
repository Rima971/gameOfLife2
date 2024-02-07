package org.game;

import org.game.enums.Event;
import org.game.enums.Symbol;

public class DeadCell extends GridItem {
    public DeadCell(int row, int column) {
        super(row, column);
        this.subscribe(Event.UPDATE_STATE, this);
    }

    @Override
    public void onEvent(Event event, Object o) {
        super.onEvent(event, o);
        this.update();
    }
    private void update(){
        if (this.getSurroundingCellsCount()==3){
            this.produce();
        }
        this.resetSurroundingCellCount();
    }

    private void produce(){
        this.publish(Event.CELL_PRODUCE, super.toString());
    }

    @Override
    public String toString(){
        return Symbol.EMPTY.representation;
    }
}
