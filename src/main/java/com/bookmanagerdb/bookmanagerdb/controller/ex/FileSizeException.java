package com.bookmanagerdb.bookmanagerdb.controller.ex;

public class FileSizeException extends FileUpLoadException{

    public FileSizeException() {
        super();
    }

    public FileSizeException(String s) {
        super(s);
    }

    public FileSizeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileSizeException(Throwable throwable) {
        super(throwable);
    }

    protected FileSizeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
