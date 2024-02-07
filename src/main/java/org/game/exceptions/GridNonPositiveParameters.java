package org.game.exceptions;

import java.security.InvalidParameterException;

public class GridNonPositiveParameters extends InvalidParameterException {
    public GridNonPositiveParameters(){
        super("A grid cannot be created with a non-positive row, column or seedingPercentage");
    }
}
