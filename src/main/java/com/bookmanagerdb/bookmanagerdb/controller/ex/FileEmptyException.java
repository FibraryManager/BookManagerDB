package com.bookmanagerdb.bookmanagerdb.controller.ex;

public class FileEmptyException extends FileUpLoadException{

    public FileEmptyException() {
        super();
    }

    public FileEmptyException(String s) {
        super(s);
    }

    public FileEmptyException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileEmptyException(Throwable throwable) {
        super(throwable);
    }

    protected FileEmptyException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
