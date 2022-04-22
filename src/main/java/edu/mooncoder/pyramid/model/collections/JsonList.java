package edu.mooncoder.pyramid.model.collections;

import java.util.Iterator;

public class JsonList implements Iterable<Object[]> {
    private CardNode root;
    private CardNode tail;

    private int index = 0;

    public void add(String card) {
        CardNode tmp = new CardNode(card, index++);

        if (tail != null) tail.next = tmp;
        if (root == null) root = tmp;

        tail = tmp;
    }

    public void add(String card, int index) {
        CardNode tmp = new CardNode(card, index);

        if (tail != null) tail.next = tmp;
        if (root == null) root = tmp;

        tail = tmp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");

        for (Object[] cardNode : this) {
            int index = (int) cardNode[0];
            String card = (String) cardNode[1];

            if (!cardNode[1].equals(root.repCard)) builder.append(", ");
            builder.append('"').append(index).append('"').append(":\"").append(card).append('"');
        }
        builder.append('}');

        return builder.toString();
    }

    @Override
    public Iterator<Object[]> iterator() {
        return new Iterator<>() {
            private CardNode node;

            @Override
            public boolean hasNext() {
                return node != tail;
            }

            @Override
            public Object[] next() {
                if (node == null) node = root;
                else node = node.next;
                return new Object[]{node.index, node.repCard};
            }
        };
    }

    private static class CardNode {
        private final String repCard;
        private final int index;
        private CardNode next;

        public CardNode(String repCard, int index) {
            this.repCard = repCard;
            this.index = index;
        }

        @Override
        public String toString() {
            return repCard;
        }
    }
}
