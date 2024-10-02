package com.ryzend.battleship.models;

import java.util.Objects;

public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate shift(int x, int y) {
        return new Coordinate(
                this.x + x,
                this.y + y
        );
    }
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Coordinate that = (Coordinate) object;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
