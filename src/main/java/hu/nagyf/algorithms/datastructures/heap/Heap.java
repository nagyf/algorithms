package hu.nagyf.algorithms.datastructures.heap;

import java.util.Comparator;
import java.util.Optional;

import hu.nagyf.algorithms.datastructures.Array;

public class Heap<T extends Comparable<T>> {
    private final Array<T> heap;
    private final Comparator<T> comparator;
    private final HeapType type;
    private int size;

    public Heap(final HeapType type) {
        this(type, 10, Comparator.naturalOrder());
    }

    public Heap(final HeapType type, final int capacity) {
        this(type, capacity, Comparator.naturalOrder());
    }

    public Heap(final HeapType type, final int capacity, final Comparator<T> comparator) {
        this.type = type;
        assert capacity > 0;

        this.comparator = comparator;
        heap = new Array<>(capacity);
        size = 0;
    }

    public void insert(final T key) {
        ++size;

        var insertAt = size - 1;
        heap.set(insertAt, key);
        heapifyUp(insertAt);
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
            heapifyDown(0);

            return Optional.of(min);
        }
    }

    private void heapifyUp(final int idx) {
        var key = heap.get(idx);
        var it = idx;
        while(it > 0 && heapCompare(heap.get(parentIdx(it)), key)) {
            heap.swap(it, parentIdx(it));
            it = parentIdx(it);
        }
    }

    private void heapifyDown(final int idx) {
        var it = idx;
        var min = -1;

        while(it != min) {
            min = it;
            var left = leftChildIdx(it);
            var right = rightChildIdx(it);

            if (left < size && heapCompare(heap.get(idx), heap.get(left))) {
                min = left;
            }

            if (right < size && heapCompare(heap.get(idx), heap.get(right))) {
                min = right;
            }

            if (min != it) {
                heap.swap(it, min);
            }
        }
    }

    private boolean heapCompare(final T a, final T b) {
        if (type == HeapType.MIN) {
            return comparator.compare(a, b) > 0;
        } else {
            return comparator.compare(a, b) < 0;
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

    public static enum HeapType {
        MIN,
        MAX
    }
}
