package edu.mooncoder.pyramid.exceptions;

public abstract class ExceptionWithStatus extends Exception {
    private final int status;

    public ExceptionWithStatus(int status, String msg) {
        super(msg);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
