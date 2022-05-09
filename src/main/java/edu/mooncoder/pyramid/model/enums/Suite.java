package edu.mooncoder.pyramid.model.enums;

import edu.mooncoder.pyramid.exceptions.CardOutOfBoundsException;

public enum Suite {
    CLUBS(0), DIAMONDS(20), HEARTS(40), SPADES(60);

    private final int value;

    Suite(int value) {
        this.value = value;
    }

    public static Suite getSuite(char icon) throws CardOutOfBoundsException {
        return switch (icon) {
            case '♥' -> HEARTS;
            case '♦' -> DIAMONDS;
            case '♣' -> CLUBS;
            case '♠' -> SPADES;
            default -> throw new CardOutOfBoundsException("");
        };
    }

    public int getValue() {
        return value;
    }
}
