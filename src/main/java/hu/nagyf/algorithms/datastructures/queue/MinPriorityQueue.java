package hu.nagyf.algorithms.datastructures.queue;

import java.util.Optional;

import hu.nagyf.algorithms.datastructures.heap.MinHeap;

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

    public static class Item<U extends Comparable<U>> implements Comparable<Item<U>> {
        public int priority = 0;
        public U value;

        public Item(U value) {
            this.value = value;
        }

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
