package hu.nagyf.algorithms.datastructures.queue;

import java.util.Optional;

public interface Queue<T> {

    void offer(final T value);

    Optional<T> poll();

    Optional<T> peek();
}
