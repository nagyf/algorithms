package hu.nagyf.algorithms.datastructures.tree;

import hu.nagyf.algorithms.datastructures.AbstractIterator;
import hu.nagyf.algorithms.datastructures.Stack;
import hu.nagyf.algorithms.datastructures.queue.FIFOQueue;
import hu.nagyf.algorithms.datastructures.queue.Queue;

/**
 * A Binary Tree is a special Tree, in which each node can have 0, 1 or 2 children.
 *
 * @param <T> the type of data to store within the nodes
 */
public class BinaryTree<T> {

    private Node<T> root;

    /**
     * Constructs a binary tree with a root value
     *
     * @param rootValue the value of the root node
     */
    public BinaryTree(final T rootValue) {
        root = new Node<>(null, rootValue);
    }

    /**
     * Returns the root node
     */
    public Node<T> getRoot() {
        return root;
    }

    public PreOrderIterator<T> preOrderIterator() {
        return new PreOrderIterator<>(root);
    }

    public InOrderIterator<T> inOrderIterator() {
        return new InOrderIterator<>(root);
    }

    public PostOrderIterator<T> postOrderIterator() {
        return new PostOrderIterator<>(root);
    }

    public BreadthFirstIterator<T> breadthFirstIterator() {
        return new BreadthFirstIterator<>(root);
    }

    /**
     * A node of the tree.
     *
     * @param <U> the type of the data stored within the node
     */
    public static class Node<U> {
        /**
         * The parent of the current node.
         * For the root node it is null.
         */
        public Node<U> parent;

        /**
         * The left child of this node. Can be null.
         */
        public Node<U> left;

        /**
         * The right child of this node. Can be null.
         */
        public Node<U> right;
        public U value;

        public Node(final Node<U> parent, final U value) {
            this.parent = parent;
            this.value = value;
        }

        /**
         * Insert a new child on the left branch.
         *
         * @param value the value to insert
         * @return the new node that was inserted
         */
        public Node<U> insertLeft(final U value) {
            var newNode = new Node<>(this, value);
            if (left != null) {
                newNode.left = left;
                left.parent = newNode;
            }
            left = newNode;
            return newNode;
        }

        /**
         * Insert a new child on the right branch.
         *
         * @param value the value to insert
         * @return the new node that was inserted
         */
        public Node<U> insertRight(final U value) {
            var newNode = new Node<>(this, value);
            if (right != null) {
                newNode.right = right;
                right.parent = newNode;
            }
            right = newNode;
            return newNode;
        }

        /**
         * If the specified node is a child of this node, then remove it.
         *
         * @param node the node to be removed
         * @return true if it was removed, otherwise false
         */
        public boolean removeChild(final Node<U> node) {
            if (left == node) {
                left = null;
                return true;
            }

            if (right == node) {
                right = null;
                return true;
            }

            return false;
        }

        /**
         * Remove this node. The Root node cannot be removed.
         *
         * @return true if it was removed
         */
        public boolean remove() {
            if (parent == null) {
                return false;
            }

            if (parent.left == this) {
                parent.left = null;
                return true;
            }

            if (parent.right == this) {
                parent.right = null;
                return true;
            }

            return false;
        }

        /**
         * Replace the child node with another node.
         *
         * @param nodeToReplace the node to replace
         * @param replaceWith the node to replace with
         * @return true if it was replaced
         */
        public boolean replaceChild(final Node<U> nodeToReplace, final Node<U> replaceWith) {
            if (left == nodeToReplace) {
                left = replaceWith;
                replaceWith.parent = this;
                return true;
            }

            if (right == nodeToReplace) {
                right = replaceWith;
                replaceWith.parent = this;
                return true;
            }

            return false;
        }
    }

    /**
     * First visits the main node, and then the left and the right branches.
     */
    public static class PreOrderIterator<U> extends AbstractIterator<Node<U>> {
        private Stack<Node<U>> path;

        public PreOrderIterator(final Node<U> current) {
            path = new Stack<>();
            path.push(current);
        }

        @Override
        public boolean hasNext() {
            return !path.isEmpty();
        }

        @Override
        public Node<U> next() {
            var next = path.pop();
            var node = next.get();
            if (node.right != null) {
                path.push(node.right);
            }

            if (node.left != null) {
                path.push(node.left);
            }
            return node;
        }
    }

    /**
     * First visits the left branch, then the main node and then the right branch.
     */
    public static class InOrderIterator<U> extends AbstractIterator<Node<U>> {
        private Queue<Node<U>> path;

        public InOrderIterator(final Node<U> current) {
            path = new FIFOQueue<>();

            var it = current;
            var stack = new Stack<Node<U>>();
            pushLefts(stack, it);

            while(!stack.isEmpty()) {
                var next = stack.pop().get();
                path.offer(next);
                it = next.right;

                pushLefts(stack, it);
            }
        }

        private void pushLefts(final Stack<Node<U>> stack, final Node<U> current) {
            var it = current;
            while(it != null) {
                stack.push(it);
                it = it.left;
            }
        }

        @Override
        public boolean hasNext() {
            return path.peek().isPresent();
        }

        @Override
        public Node<U> next() {
            return path.poll().get();
        }
    }

    /**
     * First visits the left and the right branches, and then the main node.
     */
    public static class PostOrderIterator<U> extends AbstractIterator<Node<U>> {
        private Queue<Node<U>> path;

        public PostOrderIterator(final Node<U> current) {
            path = new FIFOQueue<>();

            var first = new Stack<Node<U>>();
            var second = new Stack<Node<U>>();
            first.push(current);

            while(!first.isEmpty()) {
                var next = first.pop().get();
                second.push(next);
                if (next.left != null) {
                    first.push(next.left);
                }
                if (next.right != null) {
                    first.push(next.right);
                }
            }

            while(!second.isEmpty()) {
                path.offer(second.pop().get());
            }
        }

        @Override
        public boolean hasNext() {
            return path.peek().isPresent();
        }

        @Override
        public Node<U> next() {
            return path.poll().get();
        }
    }

    /**
     * Visits the nodes of the tree level by level, from left to right.
     */
    public static class BreadthFirstIterator<U> extends AbstractIterator<Node<U>> {
        private final Queue<Node<U>> levels;

        public BreadthFirstIterator(final Node<U> root) {
            levels = new FIFOQueue<>();
            levels.offer(root);
        }

        @Override
        public boolean hasNext() {
            return levels.peek().isPresent();
        }

        @Override
        public Node<U> next() {
            var next = levels.poll().get();
            if (next.left != null) {
                levels.offer(next.left);
            }
            if (next.right != null) {
                levels.offer(next.right);
            }

            return next;
        }
    }
}
