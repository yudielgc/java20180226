package com.xyzcorp;

import java.io.IOException;

public class TimeBomb {

    public void thisWillThrowAIOException() throws IOException {
        throw new IOException("Can't connect to internet");
    }

    public void thisWillThrowACustomException() throws MyCheckedException {
        throw new MyCheckedException("I don't feel well");
    }
}
