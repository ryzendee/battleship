package com.ryzend.battleship.reader;

import com.ryzend.battleship.models.Coordinate;

import java.io.InputStream;

public class AttackCoordinateReader extends AbstractReader<Coordinate> {

    public AttackCoordinateReader(InputStream stream) {
        super(stream);
    }

    @Override
    public Coordinate read() {
        System.out.println("Попытка попасть по кораблю");

        System.out.println("Введите x: ");
        int x = scan.nextInt();

        System.out.println("Введите y: ");
        int y = scan.nextInt();

        return new Coordinate(x, y);
    }
}
