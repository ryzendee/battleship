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
        PlayerInfo playerInfo = readPlayerInfo();

        GameField gameField = new GameField();
        ShipReader shipReader = new ShipReader(System.in, shipSizeList);

        while (shipReader.isShipSizeListEmpty()) {
            gameFieldPrinter.print(gameField);
            placeShip(shipReader, gameField);
        }

        return new Player(playerInfo, gameField);
    }

    private static PlayerInfo readPlayerInfo() {
        PlayerInfoReader playerInfoReader = new PlayerInfoReader(System.in);
        return playerInfoReader.read();
    }

    private static void placeShip(ShipReader shipReader, GameField gameField) {
        try {
            Ship ship = shipReader.read();
            gameField.placeShip(ship);
            shipReader.removeShipSize(ship.getSize());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
