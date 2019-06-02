package hu.nagyf.algorithms.datastructures.queue;

import java.util.Optional;

import hu.nagyf.algorithms.datastructures.LinkedList;

/**
 * A First-In-First-Out Queue implementation.
 * This one uses a simple linked list to store the values.
 * The values will be returned in the order they were put in the queue.
 */
public class FIFOQueue<T> implements Queue<T> {
    private LinkedList<T> queue;

    public FIFOQueue() {
        queue = new LinkedList<>();
    }

    @Override
    public void offer(T value) {
        queue.append(value);
    }

    @Override
    public Optional<T> poll() {
        return queue.removeFirst();
    }

    @Override
    public Optional<T> peek() {
        return queue.first();
    }
}
