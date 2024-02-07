package org.game.gridItemTypes;

import org.game.GridItem;
import org.game.enums.Event;
import org.game.enums.Symbol;

public class Void extends GridItem {
    public Void(int row, int column) {
        super(row, column);
        this.subscribe(Event.UPDATE_STATE, this);
    }

    @Override
    public void onEvent(Event event, Object o) {
        super.onEvent(event, o);
        this.update();
    }
    private void update(){
        System.out.println("in update void: "+this.getSurroundingCellsCount());
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
