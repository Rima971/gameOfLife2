package org.example;

import org.example.enums.Event;
import org.example.interfaces.IPublisher;
import org.example.interfaces.ISubscriber;

import java.security.InvalidParameterException;

public class Cell implements IPublisher, ISubscriber {
    private int row, column;
    public Cell(int row, int column){
        if (row<0 || column<0) throw new InvalidParameterException("Cell cannot be created with negative parameters");
        this.row = row;
        this.column = column;
//        this.subscribe(Event.BROADCAST_STATE, this);
//        this.subscribe(Event.UPDATE_STATE, this);
//        this.subscribe(Event.CELL_STATE, this);
    }
    @Override
    public void onEvent(Event event, Object o) {
    }
}
