package com.ryzend.battleship.reader;

import com.ryzend.battleship.models.Player;

import java.io.InputStream;

public class PlayerReader extends AbstractReader<Player> {

    private static final String MESSAGE = "Введите свое имя";

    public PlayerReader(InputStream stream) {
        super(stream);
    }

    @Override
    public Player read() {
        System.out.println(MESSAGE);
        String name = scan.next();

        return new Player(name);
    }
}
