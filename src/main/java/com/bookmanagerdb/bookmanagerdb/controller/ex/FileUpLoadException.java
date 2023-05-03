package com.bookmanagerdb.bookmanagerdb.controller.ex;

public class FileUpLoadException extends RuntimeException{
    public FileUpLoadException() {
        super();
    }

    public FileUpLoadException(String s) {
        super(s);
    }

    public FileUpLoadException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileUpLoadException(Throwable throwable) {
        super(throwable);
    }

    protected FileUpLoadException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
