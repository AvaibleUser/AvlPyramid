package edu.mooncoder.pyramid.exceptions;

public class CardOutOfBoundsException extends ExceptionWithStatus {
    public CardOutOfBoundsException(String card) {
        super(400, "La carta <" + card + "> no se encuentra en el mazo.");
    }
}
