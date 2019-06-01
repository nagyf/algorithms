package hu.nagyf.algorithms.datastructures;

import java.util.Optional;

public class Stack<T> {

    private LinkedList<T> stack;

    public Stack() {
        this.stack = new LinkedList<>();
    }

    public void push(final T item) {
        stack.append(item);
    }

    public Optional<T> pop() {
        return stack.removeLast();
    }

    public Optional<T> peek() {
        return stack.last();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
