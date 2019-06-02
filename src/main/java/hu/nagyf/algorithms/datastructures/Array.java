package hu.nagyf.algorithms.datastructures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Array is a dynamic array implementation.
 *
 * Dynamic means the size of the array is not predetermined, will automatically resize itself if it runs out of space.
 *
 * @param <T> the type of the values stored in the array
 */
public class Array<T> implements Iterable<T> {
    private int size;
    private Object[] array;

    /**
     * Creates a new array by specifying the initial capacity.
     * Recommended if you know that you will need a bigger array than the default size,
     * so the performance will be better because less reallocation will be needed when filling the array.
     */
    public Array(final int initialCapacity) {
        size = initialCapacity;
        array = new Object[initialCapacity];
    }

    /**
     * Creates a new array with the default capacity of 10
     */
    public Array() {
        this(10);
    }

    /**
     * Returns the size of the array.
     *
     * @return the size of the array
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets a new value in the array. Overwrites existing values.
     *
     * Automatically extends the size of the array if necessary.
     *
     * @param idx the index of the new value
     * @param item the value to store in the array
     */
    public void set(final int idx, final T item) {
        ensureSize(idx);
        array[idx] = item;
    }

    /**
     * Returns a value from the array, or null.
     *
     * @param idx the index of the value
     * @return the value stored in the array in that index
     */
    @SuppressWarnings("unchecked")
    public T get(final int idx) {
        checkIndex(idx);
        return (T)array[idx];
    }

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(this);
    }

    public void swap(final int firstIdx, final int secondIdx) {
        checkIndex(firstIdx);
        checkIndex(secondIdx);

        var tmp = array[firstIdx];
        array[firstIdx] = array[secondIdx];
        array[secondIdx] = tmp;
    }

    /**
     * Validates the given index. It must be greater or equal than zero and smaller than the size of the array.
     * @param idx the index
     */
    private void checkIndex(final int idx) {
        if (idx < 0 || idx > size - 1) {
            throw new IndexOutOfBoundsException("Array index is out of bounds");
        }
    }

    /**
     * Checks if the size of the array is large enough to reference the idx element of the array.
     * If not, it extends the size of the array to be able to reference the idx index.
     *
     * @param idx the index in the array that we want to check
     */
    private void ensureSize(final int idx) {
        var newSize = size;
        while(newSize <= idx) {
            newSize = newSize << 1;
        }
        array = Arrays.copyOf(array, newSize);
        size = newSize;
    }

    public static class ArrayIterator<U> implements Iterator<U> {
        private int i = 0;
        private final Array<U> array;

        public ArrayIterator(final Array<U> array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return i < array.getSize();
        }

        @Override
        public U next() {
            var next = array.get(i);
            ++i;
            return next;
        }
    }
}
