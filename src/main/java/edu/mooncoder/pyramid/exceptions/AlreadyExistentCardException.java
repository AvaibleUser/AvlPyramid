package edu.mooncoder.pyramid.exceptions;

public class AlreadyExistentCardException extends ExceptionWithStatus {
    public AlreadyExistentCardException(String card) {
        super(406, "La carta <" + card + "> ya existe en el arbol.");
    }
}
