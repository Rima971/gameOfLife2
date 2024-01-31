package org.example;

public class Grid {
    private final int row, column;
    private final double seedingPercentage;
    public int cellCount = 0;
    public Grid(int row, int column, double seedingPercentage){
        this.row = row;
        this.column = column;
        this.seedingPercentage = seedingPercentage;
    }

}
