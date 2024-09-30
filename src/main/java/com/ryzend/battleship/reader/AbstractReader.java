package com.ryzend.battleship.reader;

import java.io.InputStream;
import java.util.Scanner;

public abstract class AbstractReader <E>{

    protected final Scanner scan;

    public AbstractReader(InputStream stream) {
        this.scan = new Scanner(stream);
    }

    public abstract E read();
}
