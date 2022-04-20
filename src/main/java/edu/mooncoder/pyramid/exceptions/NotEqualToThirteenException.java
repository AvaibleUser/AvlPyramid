package edu.mooncoder.pyramid.exceptions;

public class NotEqualToThirteenException extends ExceptionWithStatus {
    public NotEqualToThirteenException(String card) {
        super(406, "La valor de la carta <" + card + "> no es igual a 13.");
    }

    public NotEqualToThirteenException(String card1, String card2) {
        super(406, "La suma del valor de la carta <" + card1 + "> y la carta <" + card2 + "> no es igual a 13.");
    }
}
