package edu.mooncoder.pyramid.model.collections;

import edu.mooncoder.pyramid.exceptions.AlreadyExistentCardException;
import edu.mooncoder.pyramid.exceptions.NodeWithChildrenException;
import edu.mooncoder.pyramid.exceptions.NotExistsInTheTreeException;
import edu.mooncoder.pyramid.model.enums.Order;
import edu.mooncoder.pyramid.model.rederers.TreeRenderer;

public class DeckTree {
    private Node root;

    private static boolean verifyNodeWithChildren(Node toSearch, Node toVerify) throws NodeWithChildrenException, NotExistsInTheTreeException {
        if (Node.isThatNode(toSearch, toVerify)) {
            if (toVerify.hasChildren()) {
                throw new NodeWithChildrenException(toVerify.toString());
            }
            return true;
        } else if (toVerify == null) {
            throw new NotExistsInTheTreeException(toSearch.toString());
        }
        return false;
    }

    public void insert(int card, String rep) throws AlreadyExistentCardException {
        if (root == null) {
            root = new Node(card, rep);
            return;
        }
        root = root.insert(new Node(card, rep));
    }

    public void delete(int card1, String rep1, int card2, String rep2) throws NotExistsInTheTreeException, NodeWithChildrenException {
        Node toSearch1 = new Node(card1, rep1);
        Node toSearch2 = new Node(card2, rep2);

        verifyNodeWithChildren(toSearch1, root);
        verifyNodeWithChildren(toSearch2, root);

        Node parent1 = root.searchParent(toSearch1);
        Node parent2 = root.searchParent(toSearch2);

        verifyNodeWithChildren(toSearch1, parent1);
        verifyNodeWithChildren(toSearch2, parent2);

        Node treeNode1 = parent1.search(toSearch1);
        Node treeNode2 = parent2.search(toSearch2);

        verifyNodeWithChildren(toSearch1, treeNode1);
        verifyNodeWithChildren(toSearch2, treeNode2);

        if (parent1.searchParent(parent2) == parent1) {
            parent2.delete(toSearch2);
            parent1.delete(toSearch1);
        } else {
            parent1.delete(toSearch1);
            parent2.delete(toSearch2);
        }
    }

    public void delete(int card, String rep) throws NotExistsInTheTreeException, NodeWithChildrenException {
        Node toSearch = new Node(card, rep);

        if (verifyNodeWithChildren(toSearch, root)) {
            root = null;
        } else {
            root.delete(toSearch);
        }
    }

    public void render() {
        if (root == null) return;

        TreeRenderer renderer = new TreeRenderer();
        JsonList nodeList = new JsonList();

        int index = 0;

        root.addRenderDataToList(nodeList, null, renderer, 0);

        for (Object[] cardInfo : nodeList) {
            int depth = (int) cardInfo[0];
            String[] nodes = ((String) cardInfo[1]).split("\\|");
            String card = nodes[0];

            int factor = 2 + (depth < 4 ? 0 : depth % 3);
            renderer.setAttributesToNode(card, 1 < nodes.length ? nodes[1] : null, depth * factor, index++);
        }
    }

    public String toString(Order order) {
        JsonList list = new JsonList();

        if (root != null) {
            switch (order) {
                case PREORDER -> root.toStringPreOrder(list);
                case INORDER -> root.toStringInOrder(list);
                case POSTORDER -> root.toStringPostOrder(list);
            }
        }
        return list.toString();
    }

    public String toString(int level) {
        if (level < 1) return "{}";
        JsonList list = new JsonList();

        if (root != null) root.addToList(level, list);
        return list.toString();
    }

    public void clear() {
        root = null;
    }
}
