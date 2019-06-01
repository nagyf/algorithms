package hu.nagyf.algorithms.datastructures.tree;

import hu.nagyf.algorithms.datastructures.AbstractIterator;
import hu.nagyf.algorithms.datastructures.Stack;
import hu.nagyf.algorithms.datastructures.queue.FIFOQueue;
import hu.nagyf.algorithms.datastructures.queue.Queue;

public class BinaryTree<T> {

    private Node<T> root;

    public BinaryTree(final T rootValue) {
        root = new Node<>(null, rootValue);
    }

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

    public static class Node<U> {
        public Node<U> parent;
        public Node<U> left;
        public Node<U> right;
        public U value;

        public Node(final Node<U> parent, final U value) {
            this.parent = parent;
            this.value = value;
        }

        public Node<U> insertLeft(final U value) {
            var newNode = new Node<>(this, value);
            if (left != null) {
                newNode.left = left;
                left.parent = newNode;
            }
            left = newNode;
            return newNode;
        }

        public Node<U> insertRight(final U value) {
            var newNode = new Node<>(this, value);
            if (right != null) {
                newNode.right = right;
                right.parent = newNode;
            }
            right = newNode;
            return newNode;
        }

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
