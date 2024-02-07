package org.game;

import org.game.enums.Event;
import org.game.enums.Symbol;

public class Cell extends GridItem {
    public Cell(int row, int column){
        super(row, column);
        this.subscribe(Event.BROADCAST_STATE, this);
        this.subscribe(Event.UPDATE_STATE, this);
    }

    @Override
    public void onEvent(Event event, Object o) {
        super.onEvent(event, o);
        switch (event){
            case Event.BROADCAST_STATE :
                this.publish(Event.CELL_STATE, this);
                break;
            case Event.UPDATE_STATE:
                this.update();
        }
    }

    private void update(){
        if (this.getSurroundingCellsCount()<2 || this.getSurroundingCellsCount()>3){
            this.destroy();
        }
        this.resetSurroundingCellCount();
    }

    private void destroy(){
        this.publish(Event.CELL_DESTROY, super.toString());
    }

    @Override
    public String toString(){
        return Symbol.ALIVE.representation;
    }
}
