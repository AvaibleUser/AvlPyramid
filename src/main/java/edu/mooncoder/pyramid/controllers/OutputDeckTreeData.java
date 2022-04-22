package edu.mooncoder.pyramid.controllers;

import edu.mooncoder.pyramid.model.collections.DeckTree;
import edu.mooncoder.pyramid.model.enums.Order;
import edu.mooncoder.pyramid.model.rederers.TreeRenderer;

public class OutputDeckTreeData {
    public static String getDeckAsJson(Order order) {
        DeckTree tree = DeckTreeManager.getInstance().getDeck();
        return tree.toString(order);
    }

    public static String getDeckLevelAsJson(int level) {
        DeckTree tree = DeckTreeManager.getInstance().getDeck();
        return tree.toString(level);
    }

    public static void renderDeckAsImage() {
        DeckTreeManager.getInstance().getDeck().render();
    }
}
