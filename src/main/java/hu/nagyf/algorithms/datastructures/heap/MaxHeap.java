package hu.nagyf.algorithms.datastructures.heap;

import java.util.Comparator;

public class MaxHeap<T extends Comparable<T>> extends Heap<T> {

    public MaxHeap() {
        super(HeapType.MAX);
    }

    public MaxHeap(final int capacity) {
        super(HeapType.MAX, capacity);
    }

    public MaxHeap(final int capacity, final Comparator<T> comparator) {
        super(HeapType.MAX, capacity, comparator);
    }
}
