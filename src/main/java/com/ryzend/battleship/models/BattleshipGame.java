package com.ryzend.battleship.models;

import com.ryzend.battleship.printer.GameFieldPrinter;
import com.ryzend.battleship.reader.AttackCoordinateReader;

import java.util.InputMismatchException;

public class BattleshipGame {

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final AttackCoordinateReader attackCoordinateReader;
    private final GameFieldPrinter gameFieldPrinter;

    public BattleshipGame(Player firstPlayer, Player secondPlayer, AttackCoordinateReader attackCoordinateReader, GameFieldPrinter gameFieldPrinter) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.attackCoordinateReader = attackCoordinateReader;
        this.gameFieldPrinter = gameFieldPrinter;
    }

    public void start() {
        boolean gameActive = true;
        while (gameActive) {
            gameFieldPrinter.setVisibleShips(false);
            GameField firstPlayerField = firstPlayer.getGameField();
            GameField secondPlayerField = secondPlayer.getGameField();

            playTurn(firstPlayer, secondPlayerField);
            gameActive = isGameOver(firstPlayer, secondPlayerField);

            playTurn(secondPlayer, firstPlayerField);
            gameActive = isGameOver(secondPlayer, firstPlayerField);
        }
    }

    private void playTurn(Player current, GameField opponentField) {
        System.out.println(current.getPlayerInfo().getName() + " твой ход");

        displayOpponentField(opponentField);

        while (true) {
            try {
                Coordinate coordinateToAttack = attackCoordinateReader.read();

                if (!isCoordinateValid(coordinateToAttack, opponentField)) {
                    continue;
                }

                if (isAttackSuccessful(coordinateToAttack, opponentField)) {
                    System.out.println("Попадание!");
                } else {
                    System.out.println("Промах");
                    break;
                }

            } catch (InputMismatchException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void displayOpponentField(GameField opponentField) {
        System.out.println("Поле противника");
        gameFieldPrinter.print(opponentField);
    }

    private boolean isCoordinateValid(Coordinate coordinateToAttack, GameField opponentField) {
        if (opponentField.isCoordinateAttacked(coordinateToAttack)) {
            System.out.println("Координата уже была атакована");
            return false;
        }

        if (!opponentField.isCoordinateWithinBounds(coordinateToAttack)) {
            System.out.println("Такой координаты не существует");
            return false;
        }

        return true;
    }

    private boolean isAttackSuccessful(Coordinate coordinateToAttack, GameField opponentField) {
        return opponentField.hitCoordinate(coordinateToAttack);
    }

    private boolean isGameOver(Player currentPlayer, GameField opponentField) {
        if (opponentField.isAllShipsSunk()) {
            printWinner(currentPlayer, opponentField);
            return true;
        }

        return false;
    }

    private void printWinner(Player activePlayer, GameField opponentField) {
        gameFieldPrinter.setVisibleShips(true);
        gameFieldPrinter.print(opponentField);
        System.out.println(activePlayer.getPlayerInfo().getName() + " победил!");
    }
}
