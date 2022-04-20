package edu.mooncoder.pyramid.exceptions;

public class NotExactAmountOfParametersExceptions extends ExceptionWithStatus {
    public NotExactAmountOfParametersExceptions(String msg) {
        super(400, msg);
    }
}
