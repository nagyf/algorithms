package hu.nagyf.algorithms.datastructures.queue;

import java.util.Optional;

/**
 * Generic interface for priority queues.
 * A Priority Queue is a special type of a Queue, in which each value has a priority.
 * The Queue takes the priority into account when determining the next value to return.
 *
 * @param <T> the type of the values to store in the Queue
 */
public interface PriorityQueue<T> {

    /**
     * Add a new value to the queue
     *
     * @param value the value to add
     * @param priority the priority of the value
     */
    void offer(final T value, final int priority);

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
