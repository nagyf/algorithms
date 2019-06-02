package hu.nagyf.algorithms.datastructures.heap;

import java.util.Comparator;
import java.util.Optional;

import hu.nagyf.algorithms.datastructures.Array;

/**
 * A generic Heap implementation that can act as bot a minimum and a maximum heap.
 * A Heap is essentially an almost complete tree that satisfies the heap property:
 * in a max heap, for any given node C, if P is a parent node of C, then the key (the value) of P is greater than or equal to the key of C.
 * In a min heap, the key of P is less than or equal to the key of C.
 *
 * @param <T> the type of values to store in the heap. Must implement the {@link Comparable} interface
 */
public class Heap<T extends Comparable<T>> {

    /**
     * The heap is represented with a dynamic Array.
     */
    private final Array<T> heap;

    /**
     * The comparator to use when comparing values in the heap.
     * By default the heap uses natural ordering.
     */
    private final Comparator<T> comparator;

    /**
     * The type of the heap.
     */
    private final HeapType type;

    /**
     * The current size of the heap.
     */
    private int size;

    /**
     * Constructs a new heap with the specified type.
     */
    public Heap(final HeapType type) {
        this(type, 10, Comparator.naturalOrder());
    }

    /**
     * Constructs a new heap with the specified type and starting capacity.
     */
    public Heap(final HeapType type, final int capacity) {
        this(type, capacity, Comparator.naturalOrder());
    }

    /**
     * Constructs a new heap with the specified type, starting capacity and a comparator.
     */
    public Heap(final HeapType type, final int capacity, final Comparator<T> comparator) {
        this.type = type;
        assert capacity > 0;

        this.comparator = comparator;
        heap = new Array<>(capacity);
        size = 0;
    }

    /**
     * Inserts a new value into the heap, and makes sure to maintain the heap property.
     * this means the heap may have to be reorganized after the insert.
     *
     * @param key the new value
     */
    public void insert(final T key) {
        ++size;

        var insertAt = size - 1;
        heap.set(insertAt, key);
        heapifyUp(insertAt);
    }

    /**
     * Returns the current size of the heap
     *
     * @return size of the heap, 0 if the heap is empty
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root value of the heap if it is present. Does not remove the value.
     * In case of a Min. heap the root is always the smallest value,
     * in case of a Max. heap the root is always the biggest value.
     *
     * @return the value, empty if the heap is empty.
     */
    public Optional<T> peek() {
        if (size == 0) {
            return Optional.empty();
        } else {
            return Optional.of(heap.get(0));
        }
    }

    /**
     * Removes the root value of the heap if it is present.
     * In case of a Min. heap the root is always the smallest value,
     * in case of a Max. heap the root is always the biggest value.
     * Because the value must be removed, the heap may have to be reorganized that is done automatically to maintain the heap property.
     *
     * @return the value, empty if the heap is empty.
     */
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

    /**
     * Maintain the heap property by moving the value at the specified index UP, if necessary.
     */
    private void heapifyUp(final int idx) {
        var key = heap.get(idx);
        var it = idx;
        while(it > 0 && heapCompare(heap.get(parentIdx(it)), key)) {
            heap.swap(it, parentIdx(it));
            it = parentIdx(it);
        }
    }

    /**
     * Maintain the heap property by moving the value at the specified index DOWN, if necessary.
     */
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

    /**
     * Compare the two values by taking into account the type of the heap.
     * In case of Min. Heap it returns true when a is greater than b.
     * In case of Max. Heap it returns true when a is smaller than b.
     */
    private boolean heapCompare(final T a, final T b) {
        if (type == HeapType.MIN) {
            return comparator.compare(a, b) > 0;
        } else {
            return comparator.compare(a, b) < 0;
        }
    }

    /**
     * Returns the index of the parent node for the node at index.
     */
    private int parentIdx(final int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the index of the left child node for the node at index.
     */
    private int leftChildIdx(final int index) {
        return 2 * index + 1;
    }

    /**
     * Returns the index of the right child node for the node at index.
     */
    private int rightChildIdx(final int index) {
        return 2 * index + 2;
    }

    /**
     * The type of the Heap.
     */
    public enum HeapType {
        MIN,
        MAX
    }
}
