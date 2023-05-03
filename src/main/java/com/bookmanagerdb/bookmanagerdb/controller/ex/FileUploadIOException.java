package com.bookmanagerdb.bookmanagerdb.controller.ex;
public class FileUploadIOException extends FileUpLoadException{
    public FileUploadIOException() {
        super();
    }

    public FileUploadIOException(String s) {
        super(s);
    }

    public FileUploadIOException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileUploadIOException(Throwable throwable) {
        super(throwable);
    }

    protected FileUploadIOException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
