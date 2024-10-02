package com.ryzend.battleship.reader;

import com.ryzend.battleship.enums.Orientation;
import com.ryzend.battleship.models.Coordinate;
import com.ryzend.battleship.models.Ship;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ShipReader extends AbstractReader<Ship> {

    private final List<Integer> shipSizeList;

    public ShipReader(InputStream stream, List<Integer> shipSizeList) {
        super(stream);
        this.shipSizeList = shipSizeList;
    }


    @Override
    public Ship read() {
        System.out.println("Введите данные для корабля");

        printAvailableShipSizes();
        int shipIndex = scan.nextInt();

        System.out.println("Введите x для координат головы корабля");
        int x = scan.nextInt();

        System.out.println("Введите y для координат головы корабля");
        int y = scan.nextInt();

        System.out.println("Введите ориентацию (гор, верт)");
        String orientationFromInput = scan.next();

        Orientation shipOrientation = orientationFromInput.equals("гор")
                ? Orientation.HORIZONTAL
                : Orientation.VERTICAL;

        return new Ship(shipSizeList.get(shipIndex), new Coordinate(x, y), shipOrientation);
    }

    public void printAvailableShipSizes() {
        for (int i = 0; i < shipSizeList.size(); i++) {
            System.out.println(i + ". Размер: " + shipSizeList.get(i));
        }
    }
}
