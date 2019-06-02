package hu.nagyf.algorithms.datastructures.heap;

import java.util.Comparator;

public class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap() {
        super(HeapType.MIN);
    }

    public MinHeap(final int capacity) {
        super(HeapType.MIN, capacity);
    }

    public MinHeap(final int capacity, final Comparator<T> comparator) {
        super(HeapType.MIN, capacity, comparator);
    }
}
