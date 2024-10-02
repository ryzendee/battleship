package com.ryzend.battleship.printer;

import com.ryzend.battleship.models.Coordinate;
import com.ryzend.battleship.models.GameField;

import java.io.PrintStream;

public class GameFieldPrinter extends AbstractPrinter<GameField> {

    private static final String SHIP = "[S]";
    private static final String EMPTY_CELL = "[ ]";
    private static final String SHIP_HIT = "[X]";
    private static final String MISS_HIT = "[M]";

    public boolean visibleShips = true;

    public GameFieldPrinter(PrintStream stream) {
        super(stream);
    }

    public void setVisibleShips(boolean visibleShips) {
        this.visibleShips = visibleShips;
    }

    @Override
    public void print(GameField gameField) {
        printColumnHeaders(gameField.getWidth());

        for (int y = 0; y < gameField.getHeight(); y++) {
            printRow(gameField, y);
        }
    }

    private void printColumnHeaders(int width) {
        System.out.print("  \t");
        for (int x = 0; x < width; x++) {
            System.out.print(x + "\t");
        }
        System.out.println();
    }

    private void printRow(GameField gameField, int y) {
        System.out.print(y + "\t");  // для номера строки

        for (int x = 0; x < gameField.getWidth(); x++) {
            Coordinate coordinate = new Coordinate(x, y);
            printCell(gameField, coordinate);
        }

        System.out.println();
    }

    private void printCell(GameField gameField, Coordinate coordinate) {
        if (isShipHit(gameField, coordinate)) {
            System.out.print(SHIP_HIT + "\t");
        } else if (isMissHit(gameField, coordinate)) {
            System.out.print(MISS_HIT + "\t");
        } else if (containsShip(gameField, coordinate) && visibleShips) {
            System.out.print(SHIP + "\t");
        } else {
            System.out.print(EMPTY_CELL + "\t");
        }
    }


    private boolean containsShip(GameField gameField, Coordinate coordinate) {
        return gameField.hasShipOnCoordinate(coordinate);
    }

    private boolean isMissHit(GameField gameField, Coordinate coordinate) {
        return gameField.isCoordinateAttacked(coordinate) && !gameField.hasShipOnCoordinate(coordinate);
    }

    private boolean isShipHit(GameField gameField, Coordinate coordinate) {
        return gameField.isCoordinateAttacked(coordinate) && gameField.hasShipOnCoordinate(coordinate);
    }
}
