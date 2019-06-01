package hu.nagyf.algorithms.datastructures.tree;

import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<T>> {
    private final BinaryTree<T> tree;
    private final Comparator<T> comparator;

    public BinarySearchTree(final T rootValue) {
        tree = new BinaryTree<>(rootValue);
        comparator = Comparator.naturalOrder();
    }

    public BinarySearchTree(final T rootValue, final Comparator<T> comparator) {
        tree = new BinaryTree<>(rootValue);
        this.comparator = comparator;
    }

    /**
     * Gets the underlying tree
     *
     * @return the tree
     */
    public BinaryTree<T> getTree() {
        return tree;
    }

    public boolean isPresent(final T key) {
        var it = tree.getRoot();

        while(it != null) {
            var compared = comparator.compare(key, it.value);
            switch (compared) {
            case 0:
                return true;
            case -1:
                it = it.left;
                break;
            case 1:
                it = it.right;
                break;
            }
        }

        return false;
    }

    public void insert(final T key) {
        var it = tree.getRoot();

        while(it != null) {
            var comparison = comparator.compare(key, it.value);

            if (comparison == 0) {
                return;
            } else if (comparison < 0) {
                if (it.left == null) {
                    it.insertLeft(key);
                    return;
                } else {
                    it = it.left;
                }
            } else {
                if (it.right == null) {
                    it.insertRight(key);
                    return;
                } else {
                    it = it.right;
                }
            }
        }
    }

    public void remove(final T key) {
        var node = findKey(key);
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            // 1. No child nodes, simple delete it
            node.remove();
        } else if (node.left == null) {
            // 2. One child node (right), replace the current with the child
            node.parent.replaceChild(node, node.right);
        } else if (node.right == null) {
            // 3. One child node (left), replace the current with the child
            node.parent.replaceChild(node, node.left);
        } else {
            // 4. Two child nodes, select the in order successor of the current node, and replace the current with it
            var successor = new BinaryTree.InOrderIterator<>(node).next();
            node.value = successor.value;

            if (successor.left == null && successor.right == null) {
                successor.parent.removeChild(successor);
            } else if (successor.right == null) {
                successor.value = successor.left.value;
                successor.removeChild(successor.left);
            } else {
                successor.value = successor.right.value;
                successor.removeChild(successor.right);
            }
        }
    }

    private BinaryTree.Node<T> findKey(final T key) {
        var it = tree.getRoot();
        while(it != null) {
            var comparison = comparator.compare(key, it.value);
            if (comparison == 0) {
                return it;
            } else if (comparison < 0) {
                it = it.left;
            } else {
                it = it.right;
            }
        }
        return null;
    }
}
