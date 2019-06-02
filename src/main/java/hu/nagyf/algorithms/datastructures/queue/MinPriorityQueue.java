package hu.nagyf.algorithms.datastructures.queue;

import java.util.Optional;

import hu.nagyf.algorithms.datastructures.heap.MinHeap;

/**
 * A Priority Queue implementation in which the smaller priority will be returned earlier.
 * This one uses a {@link MinHeap} to store the values.
 */
public class MinPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
    private MinHeap<Item<T>> minHeap;

    public MinPriorityQueue(final int size) {
        minHeap = new MinHeap<>(size);
    }

    @Override
    public void offer(final T value, final int priority) {
        minHeap.insert(new Item<>(priority, value));
    }

    @Override
    public Optional<T> poll() {
        return minHeap.pop().map((item) -> item.value);
    }

    @Override
    public Optional<T> peek() {
        return minHeap.peek().map((item) -> item.value);
    }

    /**
     * The Item class is used to wrap the U values before putting them in the queue.
     * This wrapper class ensures that every value has a corresponding priority.
     */
    public static class Item<U extends Comparable<U>> implements Comparable<Item<U>> {
        public int priority;
        public U value;

        public Item(int priority, U value) {
            this.priority = priority;
            this.value = value;
        }

        @Override
        public int compareTo(Item<U> o) {
            return Integer.compare(priority, o.priority);
        }
    }
}
