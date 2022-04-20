package edu.mooncoder.pyramid.exceptions;

public class ExpectedParameterNotFoundException extends ExceptionWithStatus {
    public ExpectedParameterNotFoundException(String msg) {
        super(400, msg);
    }
}
