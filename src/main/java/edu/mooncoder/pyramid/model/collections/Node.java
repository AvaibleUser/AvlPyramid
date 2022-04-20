package edu.mooncoder.pyramid.model.collections;

import edu.mooncoder.pyramid.exceptions.AlreadyExistentCardException;
import edu.mooncoder.pyramid.exceptions.NodeWithChildrenException;
import edu.mooncoder.pyramid.exceptions.NotExistsInTheTreeException;

class Node {
    private final int card;
    private final String rep;

    private Node leftChild;
    private Node rightChild;

    public Node(int card, String rep) {
        this.card = card;
        this.rep = rep;
    }

    public static boolean verifyAndDelete(Node nodeToVerify, Node nodeToDelete) throws NotExistsInTheTreeException, NodeWithChildrenException {
        if (nodeToDelete == null) {
            throw new NotExistsInTheTreeException(nodeToVerify.rep);
        } else if (nodeToVerify.card == nodeToDelete.card) {
            if (nodeToVerify.leftChild == null && nodeToVerify.rightChild == null) {
                return true;
            } else {
                throw new NodeWithChildrenException(nodeToVerify.rep);
            }
        }
        return false;
    }

    public static boolean isThatNode(Node nodeToSearch, Node possibleNode) {
        if (possibleNode == null) {
            return false;
        } else {
            return possibleNode.card == nodeToSearch.card;
        }
    }

    private int getLevel() {
        int left = 1, right = 1;

        if (leftChild != null) left += leftChild.getLevel();
        if (rightChild != null) right += rightChild.getLevel();

        return Math.max(left, right);
    }

    private int getLevelDifference() {
        int left = 0, right = 0;
        if (leftChild != null) {
            left = leftChild.getLevel();
        }
        if (rightChild != null) {
            right = rightChild.getLevel();
        }
        return right - left;
    }

    private Node leftRotation() {
        Node newParent = leftChild;

        leftChild = newParent.rightChild;
        newParent.rightChild = this;

        return newParent;
    }

    private Node rightRotation() {
        Node newParent = rightChild;

        rightChild = newParent.leftChild;
        newParent.leftChild = this;

        return newParent;
    }

    private Node tryToBalance() {
        int diff = getLevelDifference();
        if (diff < -1) {
            if (0 < leftChild.getLevelDifference()) {
                leftChild = leftChild.rightRotation();
            }
            return leftRotation();
        } else if (1 < diff) {
            if (rightChild.getLevelDifference() < 0) {
                rightChild = rightChild.leftRotation();
            }
            return rightRotation();
        }
        return this;
    }

    public void addToList(int level, JsonList list) {
        if (level == 1) {
            list.add(this.rep);
        } else if (level == 2) {
            if (leftChild != null) {
                list.add(leftChild.rep);
            }
            if (rightChild != null) {
                list.add(rightChild.rep);
            }
        } else if (level > 2) {
            if (leftChild != null) {
                leftChild.addToList(level - 1, list);
            }
            if (rightChild != null) {
                rightChild.addToList(level - 1, list);
            }
        }

    }

    public void toStringPreOrder(JsonList list) {
        list.add(this.rep);

        if (leftChild != null) {
            leftChild.toStringPreOrder(list);
        }
        if (rightChild != null) {
            rightChild.toStringPreOrder(list);
        }
    }

    public void toStringInOrder(JsonList list) {
        if (leftChild != null) {
            leftChild.toStringInOrder(list);
        }

        list.add(this.rep);

        if (rightChild != null) {
            rightChild.toStringInOrder(list);
        }
    }

    public void toStringPostOrder(JsonList list) {
        if (leftChild != null) {
            leftChild.toStringPostOrder(list);
        }
        if (rightChild != null) {
            rightChild.toStringPostOrder(list);
        }
        list.add(this.rep);
    }

    public boolean hasChildren() {
        return leftChild != null || rightChild != null;
    }

    public Node insert(Node node) throws AlreadyExistentCardException {
        if (node.card < this.card) {
            if (leftChild == null) {
                leftChild = node;
            } else {
                leftChild = leftChild.insert(node);
            }
        } else if (node.card > this.card) {
            if (rightChild == null) {
                rightChild = node;
            } else {
                rightChild = rightChild.insert(node);
            }
        } else {
            throw new AlreadyExistentCardException(node.rep);
        }
        return tryToBalance();
    }

    public Node delete(Node node) throws NotExistsInTheTreeException, NodeWithChildrenException {
        if (node.card < this.card) {
            if (verifyAndDelete(node, leftChild)) {
                leftChild = null;
            } else {
                leftChild = leftChild.delete(node);
            }
        } else if (node.card > this.card) {
            if (verifyAndDelete(node, rightChild)) {
                rightChild = null;
            } else {
                rightChild = rightChild.delete(node);
            }
        }
        return tryToBalance();
    }

    public Node search(Node node) {
        if (node.card < this.card) {
            if (isThatNode(node, leftChild)) {
                return leftChild;
            } else if (leftChild != null) {
                return leftChild.search(node);
            } else {
                return null;
            }
        } else if (node.card > this.card) {
            if (isThatNode(node, rightChild)) {
                return rightChild;
            } else if (rightChild != null) {
                return rightChild.search(node);
            } else {
                return null;
            }
        }
        return this;
    }

    public Node searchParent(Node node) {
        if (node.card < this.card) {
            if (isThatNode(node, leftChild)) {
                return this;
            } else if (leftChild != null) {
                return leftChild.searchParent(node);
            }
        } else if (node.card > this.card) {
            if (isThatNode(node, rightChild)) {
                return this;
            } else if (rightChild != null) {
                return rightChild.searchParent(node);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return rep;
    }
}
