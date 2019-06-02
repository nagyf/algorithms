package hu.nagyf.algorithms.datastructures;

import java.util.Optional;

/**
 * Stack implementation. It is using a linked list under the hood.
 *
 * @param <T> the type of the values to store in the stack
 */
public class Stack<T> {

    private LinkedList<T> stack;

    public Stack() {
        this.stack = new LinkedList<>();
    }

    /**
     * Pushes a new value onto the stack.
     *
     * @param item the new value
     */
    public void push(final T item) {
        stack.append(item);
    }

    /**
     * Removes the top-most value from the stack and returns it.
     *
     * @return optional value of T, it is empty if the stack is empty
     */
    public Optional<T> pop() {
        return stack.removeLast();
    }

    /**
     * Returns the top-most value of the stack, but does not remove it.
     *
     * @return optional value of T, it is empty if the stack is empty
     */
    public Optional<T> peek() {
        return stack.last();
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
