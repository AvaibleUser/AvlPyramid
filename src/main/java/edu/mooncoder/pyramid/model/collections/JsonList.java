package edu.mooncoder.pyramid.model.collections;

public class JsonList {
    private CardNode root;
    private CardNode tail;

    public void add(String card) {
        CardNode tmp = new CardNode(card);

        if (tail != null) tail.next = tmp;
        if (root == null) root = tmp;

        tail = tmp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        int index = 0;
        CardNode cardNode = root;

        while (cardNode != null) {
            if (cardNode != root) builder.append(", ");
            builder.append('"').append(index++).append('"').append(":\"").append(cardNode).append('"');
            cardNode = cardNode.next;
        }
        builder.append('}');

        return builder.toString();
    }

    private static class CardNode {
        private final String repCard;
        private CardNode next;

        public CardNode(String repCard) {
            this.repCard = repCard;
        }

        @Override
        public String toString() {
            return repCard;
        }
    }
}
