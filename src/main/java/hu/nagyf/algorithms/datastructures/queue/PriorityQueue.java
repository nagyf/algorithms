package hu.nagyf.algorithms.datastructures.queue;

import java.util.Optional;

public interface PriorityQueue<T> {

    void offer(final T value, final int priority);

    Optional<T> poll();

    Optional<T> peek();
}
