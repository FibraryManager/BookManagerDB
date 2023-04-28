package com.bookmanagerdb.bookmanagerdb.controller.ex;

public class FileTypeException extends FileUpLoadException{
    public FileTypeException() {
        super();
    }

    public FileTypeException(String s) {
        super(s);
    }

    public FileTypeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileTypeException(Throwable throwable) {
        super(throwable);
    }

    protected FileTypeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
