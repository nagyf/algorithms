package hu.nagyf.algorithms.datastructures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Array<T> implements Iterable<T> {
    private int size;
    private Object[] array;

    public Array(final int initialCapacity) {
        size = initialCapacity;
        array = new Object[initialCapacity];
    }

    public Array() {
        this(10);
    }

    /**
     * Gets size
     *
     * @return the size of the array
     */
    public int getSize() {
        return size;
    }

    public void set(final int idx, final T item) {
        ensureSize(idx);
        array[idx] = item;
    }

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

    private void checkIndex(final int idx) {
        if (idx < 0 || idx > size - 1) {
            throw new IndexOutOfBoundsException("Array index is out of bounds");
        }
    }

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
