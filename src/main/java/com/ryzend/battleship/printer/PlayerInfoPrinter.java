package com.ryzend.battleship.printer;

import com.ryzend.battleship.models.PlayerInfo;

import java.io.PrintStream;

public class PlayerInfoPrinter extends AbstractPrinter<PlayerInfo> {

    public PlayerInfoPrinter(PrintStream printStream) {
        super(printStream);
    }

    @Override
    public void print(PlayerInfo element) {
        System.out.println(element.getName());
    }
}
