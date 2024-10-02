package com.ryzend.battleship.models;

import com.ryzend.battleship.enums.Orientation;

import java.util.*;

public class GameField {

    private static final int DEFAULT_HEIGHT = 10;
    private static final int DEFAULT_WIDTH = 10;

    private final Map<Coordinate, Ship> coordinateShipMap = new HashMap<>();
    private final Set<Coordinate> attackedCoordinates = new HashSet<>();

    private final int height;
    private final int width;

    public GameField(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public GameField() {
        this.height = DEFAULT_HEIGHT;
        this.width = DEFAULT_WIDTH;
    }

    public Map<Coordinate, Ship> getCoordinateShipMap() {
        return coordinateShipMap;
    }

    public Set<Coordinate> getAttackedCoordinates() {
        return attackedCoordinates;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void placeShip(Ship ship) {
        Set<Coordinate> shipCoordinates = calculateShipCoordinates(ship);

        for (Coordinate coordinate : shipCoordinates) {
            coordinateShipMap.put(coordinate, ship);
        }
    }

    public boolean hitCoordinate(Coordinate coordinate) {
        attackedCoordinates.add(coordinate);

        Ship ship = coordinateShipMap.get(coordinate);
        if (ship != null) {
            ship.hit();
            return true;
        }

        return false;
    }

    public boolean isCoordinateWithinBounds(Coordinate coordinate) {
        return coordinate.getX() >= 0 && coordinate.getX() < width &&
                coordinate.getY() >= 0 && coordinate.getY() < height;

    }

    public boolean isCoordinateAttacked(Coordinate coordinate) {
        return attackedCoordinates.contains(coordinate);
    }

    public boolean hasShipOnCoordinate(Coordinate coordinate) {
        return coordinateShipMap.get(coordinate) != null;
    }

    public boolean isAllShipsSunk() {
        for (Ship ship : coordinateShipMap.values()) {
            if (!ship.isSunk()) {
                return false;
            }
        }

        return true;
    }

    private Set<Coordinate> calculateShipCoordinates(Ship ship) {
        Set<Coordinate> shipCoordinates = new HashSet<>();
        for (int i = 0; i < ship.getSize(); i++) {
            Coordinate shipHeadCoordinate = ship.getHeadCoordinate();
            Coordinate shiftedCoordinate;

            if (ship.getOrientation().equals(Orientation.HORIZONTAL)) {
                shiftedCoordinate = shipHeadCoordinate.shift(i, 0);
            } else {
                shiftedCoordinate = shipHeadCoordinate.shift(0, i);
            }

            if (!isCoordinateWithinBounds(shiftedCoordinate)) {
                throw new IllegalArgumentException("Корабль выходит за пределы карты!");
            }

            if (!isSafeToPlace(shiftedCoordinate)) {
                throw new IllegalArgumentException("Корабль находиться слишком близко к другому кораблю!");
            }

            shipCoordinates.add(shiftedCoordinate);
        }

        return shipCoordinates;
    }

    private boolean isSafeToPlace(Coordinate coordinate) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }

                Coordinate adjacentCoordinate = coordinate.shift(dx, dy);

                if (isCoordinateWithinBounds(adjacentCoordinate)) {
                    if (hasShipOnCoordinate(adjacentCoordinate)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
