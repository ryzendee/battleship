package com.ryzend.battleship.models;


public class Player {

    private final PlayerInfo playerInfo;
    private final GameField gameField;

    public Player(PlayerInfo playerInfo, GameField gameField) {
        this.playerInfo = playerInfo;
        this.gameField = gameField;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public GameField getGameField() {
        return gameField;
    }
}
