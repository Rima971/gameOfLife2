package org.example;

import org.example.enums.Event;
import org.example.interfaces.IPublisher;
import org.example.interfaces.ISubscriber;

public class Cell implements IPublisher, ISubscriber {
    private int row, column;
    public Cell(int row, int column){
        this.row = row;
        this.column = column;
        this.subcribe(Event.BROADCAST_STATE, this);
        this.subcribe(Event.UPDATE_STATE, this);
        this.subcribe(Event.CELL_STATE, this);
    }
    @Override
    public void onEvent(Event event, Object o) {
    }
}
