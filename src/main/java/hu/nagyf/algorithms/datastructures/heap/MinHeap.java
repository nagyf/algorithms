package hu.nagyf.algorithms.datastructures.heap;

import java.util.Comparator;
import java.util.Optional;

import hu.nagyf.algorithms.datastructures.Array;

public class MinHeap<T extends Comparable<T>> {
    private Array<T> heap;
    private int size;
    private int capacity;
    private Comparator<T> comparator;

    public MinHeap(final int capacity) {
        this(capacity, Comparator.naturalOrder());
    }

    public MinHeap(final int capacity, final Comparator<T> comparator) {
        this.capacity = capacity;
        assert capacity > 0;

        this.comparator = comparator;
        heap = new Array<>(capacity);
        size = 0;
    }

    public void insert(final T key) {
        if (size >= capacity) {
            throw new RuntimeException("The heap is full, cannot insert any more elements");
        }
        ++size;

        var insertAt = size - 1;
        heap.set(insertAt, key);
        minHeapifyUp(insertAt);
    }

    public int getSize() {
        return size;
    }

    public Optional<T> peek() {
        if (size == 0) {
            return Optional.empty();
        } else {
            return Optional.of(heap.get(0));
        }
    }

    public Optional<T> pop() {
        if (size == 0) {
            return Optional.empty();
        } else if (size == 1) {
            var min = heap.get(0);
            heap.set(0, null);
            --size;
            return Optional.of(min);
        } else {
            var min = heap.get(0);
            heap.swap(0, size - 1);
            heap.set(size - 1, null);
            --size;
            minHeapifyDown(0);

            return Optional.of(min);
        }
    }

    private void minHeapifyUp(final int idx) {
        var key = heap.get(idx);
        var it = idx;
        while(it > 0 && comparator.compare(heap.get(parentIdx(it)), key) > 0) {
            heap.swap(it, parentIdx(it));
            it = parentIdx(it);
        }
    }

    private void minHeapifyDown(final int idx) {
        var it = idx;
        var min = -1;

        while(it != min) {
            min = it;
            var left = leftChildIdx(it);
            var right = rightChildIdx(it);

            if (left < size && comparator.compare(heap.get(idx), heap.get(left)) > 0) {
                min = left;
            }

            if (right < size && comparator.compare(heap.get(idx), heap.get(right)) > 0) {
                min = right;
            }

            if (min != it) {
                heap.swap(it, min);
            }
        }
    }

    private int parentIdx(final int index) {
        return (index - 1) / 2;
    }

    private int leftChildIdx(final int index) {
        return 2 * index + 1;
    }

    private int rightChildIdx(final int index) {
        return 2 * index + 2;
    }
}
