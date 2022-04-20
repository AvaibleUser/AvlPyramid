package edu.mooncoder.pyramid.exceptions;

public class NodeWithChildrenException extends ExceptionWithStatus {
    public NodeWithChildrenException(String card) {
        super(409, "La carta <" + card + "> no esta en el frente de la pila de cartas.");
    }
}
