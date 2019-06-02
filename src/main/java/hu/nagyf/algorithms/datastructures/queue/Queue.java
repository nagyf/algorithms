package hu.nagyf.algorithms.datastructures.queue;

import java.util.Optional;

/**
 * A generic interface for Queue implementations.
 *
 * @param <T> the type of the values to store in the Queue
 */
public interface Queue<T> {

    /**
     * Add a new value to the queue
     *
     * @param value the value to add
     */
    void offer(final T value);

    /**
     * Remove the next value from the queue and return it.
     *
     * @return the optional next value from the queue, it is empty if there are no values in the queue
     */
    Optional<T> poll();

    /**
     * Returns the next value from the queue but does not remove it.
     *
     * @return the optional next value from the queue, it is empty if there are no values in the queue
     */
    Optional<T> peek();
}
