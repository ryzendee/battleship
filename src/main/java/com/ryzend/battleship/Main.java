package com.ryzend.battleship;

import com.ryzend.battleship.models.*;
import com.ryzend.battleship.printer.GameFieldPrinter;
import com.ryzend.battleship.reader.AttackCoordinateReader;
import com.ryzend.battleship.reader.PlayerInfoReader;
import com.ryzend.battleship.reader.ShipReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> shipSizeList = List.of(4, 3, 3, 2, 2, 1, 1, 1);

        GameFieldPrinter gameFieldPrinter = new GameFieldPrinter(System.out);
        gameFieldPrinter.setVisibleShips(true);

        System.out.println("Игрок №1");
        Player firstPlayer = initPlayer(gameFieldPrinter, new ArrayList<>(shipSizeList));

        System.out.println("Игрок №2");
        Player secondPlayer = initPlayer(gameFieldPrinter, new ArrayList<>(shipSizeList));

        BattleshipGame game = new BattleshipGame(firstPlayer, secondPlayer, new AttackCoordinateReader(System.in), gameFieldPrinter);
        game.start();
    }

    private static Player initPlayer(GameFieldPrinter gameFieldPrinter, List<Integer> shipSizeList) {
        PlayerInfoReader playerInfoReader = new PlayerInfoReader(System.in);
        PlayerInfo playerInfo = playerInfoReader.read();

        GameField gameField = new GameField();
        ShipReader shipReader = new ShipReader(System.in, shipSizeList);
        while (!shipSizeList.isEmpty()) {
            try {
                gameFieldPrinter.print(gameField);

                Ship ship = shipReader.read();
                gameField.placeShip(ship);
                shipSizeList.remove(Integer.valueOf(ship.getSize()));
            } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return new Player(playerInfo, gameField);
    }

}
