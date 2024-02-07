package org.game.exceptions;

import java.security.InvalidParameterException;

public class GridOutOfRangeSeedingPercentage extends InvalidParameterException {
    public GridOutOfRangeSeedingPercentage(){
        super("A grid cannot be created with a seedingPercentage beyond the range of 0-1");
    }
}
