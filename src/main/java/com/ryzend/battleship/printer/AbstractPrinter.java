package com.ryzend.battleship.printer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public abstract class AbstractPrinter<E> {

    protected final PrintStream printStream;

    public AbstractPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public abstract void print(E element);
}
