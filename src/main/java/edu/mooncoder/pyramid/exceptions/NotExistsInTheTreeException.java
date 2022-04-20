package edu.mooncoder.pyramid.exceptions;

public class NotExistsInTheTreeException extends ExceptionWithStatus {
    public NotExistsInTheTreeException(String card) {
        super(404, "La carta <" + card + "> no se encuentra en el arbol.");
    }
}
