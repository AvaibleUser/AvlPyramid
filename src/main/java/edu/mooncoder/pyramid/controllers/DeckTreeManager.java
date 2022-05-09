package edu.mooncoder.pyramid.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.mooncoder.pyramid.exceptions.*;
import edu.mooncoder.pyramid.model.collections.DeckTree;
import edu.mooncoder.pyramid.model.enums.Suite;

import java.util.Map;

public class DeckTreeManager {
    private static final DeckTreeManager inst = new DeckTreeManager();
    private final DeckTree deck;

    private DeckTreeManager() {
        this.deck = new DeckTree();
    }

    public static DeckTreeManager getInstance() {
        return inst;
    }

    private int getCardValue(String cardInfo) throws CardOutOfBoundsException {
        Suite suite;
        try {
            suite = Suite.getSuite(cardInfo.charAt(cardInfo.length() - 1));
        } catch (CardOutOfBoundsException e) {
            throw new CardOutOfBoundsException(cardInfo);
        }

        return switch (cardInfo.substring(0, cardInfo.length() - 1)) {
            case "As", "A", "1" -> 1;
            case "J", "11" -> 11;
            case "Q", "12" -> 12;
            case "K", "13" -> 13;
            case "2", "3", "4", "5", "6", "7", "8", "9", "10" ->
                    Integer.parseInt(cardInfo.substring(0, cardInfo.length() - 1));
            default -> throw new CardOutOfBoundsException(cardInfo);
        } + suite.getValue();
    }

    public void addAllCardsToDeck(String json) throws ExceptionWithStatus {
        for (Map.Entry<String, JsonElement> entry : JsonParser.parseString(json).getAsJsonObject().entrySet()) {
            getCardValue(entry.getValue().getAsString());
        }
        deck.clear();
        for (Map.Entry<String, JsonElement> entry : JsonParser.parseString(json).getAsJsonObject().entrySet()) {
            String cardInfo = entry.getValue().getAsString();
            deck.insert(getCardValue(cardInfo), cardInfo);
        }
    }

    public void addCardToDeck(String json) throws ExceptionWithStatus {
        JsonObject toInsert = JsonParser.parseString(json).getAsJsonObject();
        if (toInsert.size() != 1) {
            throw new NotExactAmountOfParametersExceptions("Se esperaba un unico valor a añadir.");
        } else if (toInsert.get("insert") == null) {
            throw new ExpectedParameterNotFoundException("No se encontro el parametro 'insert'.");
        }
        String cardInfo = toInsert.get("insert").getAsString();
        deck.insert(getCardValue(cardInfo), cardInfo);
    }

    public void deleteCards(String json) throws ExceptionWithStatus {
        JsonObject toDelete = JsonParser.parseString(json).getAsJsonObject();

        try {
            if (toDelete.size() == 1) {
                String cardInfo = toDelete.get("delete_1").getAsString();
                int cardValue = getCardValue(cardInfo);

                if (cardValue % 20 == 13) {
                    deck.delete(cardValue, cardInfo);
                } else {
                    throw new NotEqualToThirteenException(cardInfo);
                }
            } else if (toDelete.size() == 2) {
                String cardInfo1 = toDelete.get("delete_1").getAsString();
                String cardInfo2 = toDelete.get("delete_2").getAsString();
                int cardValue1 = getCardValue(cardInfo1);
                int cardValue2 = getCardValue(cardInfo2);

                if ((cardValue1 % 20) + (cardValue2 % 20) == 13) {
                    deck.delete(cardValue1, cardInfo1, cardValue2, cardInfo2);
                } else {
                    throw new NotEqualToThirteenException(cardInfo1, cardInfo2);
                }
            } else {
                throw new NotExactAmountOfParametersExceptions("Se esperaba una o dos cartas a eliminar.");
            }
        } catch (NullPointerException e) {
            throw new ExpectedParameterNotFoundException("No se encontro el parametro 'delete_1' o 'delete_2.");
        }
    }

    DeckTree getDeck() {
        return deck;
    }
}
