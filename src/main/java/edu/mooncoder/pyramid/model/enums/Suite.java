package edu.mooncoder.pyramid.model.enums;

public enum Suite {
    CLUBS(0), DIAMONDS(20), HEARTS(40), SPADES(60);

    private final int value;

    Suite(int value) {
        this.value = value;
    }

    public static Suite getSuite(char icon) {
        return switch (icon) {
            case '♥' -> HEARTS;
            case '♦' -> DIAMONDS;
            case '♣' -> CLUBS;
            case '♠' -> SPADES;
            default -> null;
        };
    }

    public int getValue() {
        return value;
    }
}
