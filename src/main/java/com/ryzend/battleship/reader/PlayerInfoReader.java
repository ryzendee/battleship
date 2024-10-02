package com.ryzend.battleship.reader;

import com.ryzend.battleship.models.PlayerInfo;

import java.io.InputStream;

public class PlayerInfoReader extends AbstractReader<PlayerInfo> {

    public PlayerInfoReader(InputStream stream) {
        super(stream);
    }

    @Override
    public PlayerInfo read() {
        System.out.println("Введите свое имя");
        String name = scan.next();

        return new PlayerInfo(name);
    }
}
