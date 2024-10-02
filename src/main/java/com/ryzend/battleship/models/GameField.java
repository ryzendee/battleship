package com.ryzend.battleship.models;

import com.ryzend.battleship.enums.Orientation;

import java.util.*;

public class GameField {

    private static final int DEFAULT_HEIGHT = 10;
    private static final int DEFAULT_WIDTH = 10;
    private static final int ADJACENT_RADIUS = 1;

    private final Map<Coordinate, Ship> coordinateShipMap = new HashMap<>();
    private final Set<Coordinate> attackedCoordinates = new HashSet<>();
    private final int height;
    private final int width;

    public GameField(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public GameField() {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
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
        return coordinateShipMap.containsKey(coordinate);
    }

    public boolean isAllShipsSunk() {
        return coordinateShipMap.values().stream().allMatch(Ship::isSunk);
    }


    private Set<Coordinate> calculateShipCoordinates(Ship ship) {
        Set<Coordinate> shipCoordinates = new HashSet<>();

        for (int i = 0; i < ship.getSize(); i++) {
            Coordinate shiftedCoordinate = shiftCoordinate(ship.getHeadCoordinate(), ship.getOrientation(), i);

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

    private Coordinate shiftCoordinate(Coordinate headCoordinate, Orientation orientation, int offset) {
        if (orientation.equals(Orientation.HORIZONTAL)) {
            return headCoordinate.shift(offset, 0);
        } else {
            return headCoordinate.shift(0, offset);
        }
    }

    private boolean isSafeToPlace(Coordinate coordinate) {
        for (int dx = -ADJACENT_RADIUS; dx <= ADJACENT_RADIUS; dx++) {
            for (int dy = -ADJACENT_RADIUS; dy <= ADJACENT_RADIUS; dy++) {
                if (isSelfCoordinate(dx, dy)) {
                    continue;
                }

                if (!isAdjacentCoordinateSafe(coordinate, dx, dy)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isSelfCoordinate(int dx, int dy) {
        return dx == 0 && dy == 0;
    }

    private boolean isAdjacentCoordinateSafe(Coordinate coordinate, int dx, int dy) {
        Coordinate adjacentCoordinate = coordinate.shift(dx, dy);

        if (isCoordinateWithinBounds(adjacentCoordinate)) {
            return !hasShipOnCoordinate(adjacentCoordinate);
        }

        return true;
    }

}
