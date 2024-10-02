package com.ryzend.battleship.models;

import com.ryzend.battleship.enums.Orientation;


public class Ship {
    private final int size;
    private final Coordinate headCoordinate;
    private final Orientation orientation;
    private int health;

    public Ship(int size, Coordinate headCoordinate, Orientation orientation) {
        this.size = size;
        this.headCoordinate = headCoordinate;
        this.orientation = orientation;
        this.health = size;
    }

    public void hit() {
        this.health--;
    }

    public boolean isSunk() {
        return health == 0;
    }

    public int getSize() {
        return size;
    }

    public Coordinate getHeadCoordinate() {
        return headCoordinate;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getHealth() {
        return health;
    }
}
