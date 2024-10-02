package com.ryzend.battleship.printer;

import java.io.PrintStream;

public abstract class AbstractPrinter<E> {

    protected final PrintStream printStream;

    public AbstractPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public abstract void print(E element);
}
