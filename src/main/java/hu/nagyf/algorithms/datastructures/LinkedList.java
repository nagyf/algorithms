package hu.nagyf.algorithms.datastructures;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A doubly linked list implementation.
 *
 * @param <T> The type of values to store in the list
 */
public class LinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    /**
     * Creates an empty list
     */
    public LinkedList() {
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.previous = head;
    }

    /**
     * Checks whether the list is empty.
     *
     * @return true if it is empty, false if it contains values
     */
    public boolean isEmpty() {
        return head.next == tail;
    }

    /**
     * Inserts a new value in the list in the first position of the list.
     * If you want to append to the end of the list use {@link #append(T)}.
     *
     * @param value the new value to add to the list
     */
    public void insert(final T value) {
        var node = new Node<>(value);

        node.next = head.next;
        node.previous = head;

        head.next.previous = node;
        head.next = node;
        ++size;
    }

    /**
     * Append a new value to the end of the list.
     * If you want to insert to the beginning of the list use {@link #insert(T)}.
     *
     * @param value the new value to add to the list
     */
    public void append(final T value) {
        var node = new Node<>(value);

        node.next = tail;
        node.previous = tail.previous;

        tail.previous.next = node;
        tail.previous = node;
        ++size;
    }

    /**
     * Appends another list to the end of this list.
     * Modifies this list.
     *
     * @param otherList the other list to append to the end of this list
     * @return this list
     */
    public LinkedList<T> append(final LinkedList<T> otherList) {
        otherList.stream().forEach(this::append);
        return this;
    }

    /**
     * Removes the first element of this list and returns it. Modifies the list.
     *
     * @return an optional value of T, it is empty if the method is called on an empty list.
     */
    public Optional<T> removeFirst() {
        if (isEmpty()) {
            return Optional.empty();
        } else {
            T first = head.next.value;
            head.next = head.next.next;
            head.next.previous = head;
            --size;
            return Optional.of(first);
        }
    }

    /**
     * Removes the last element of this list and returns it. Modifies the list.
     *
     * @return an optional value of T, it is empty if the method is called on an empty list.
     */
    public Optional<T> removeLast() {
        if (isEmpty()) {
            return Optional.empty();
        } else {
            T last = tail.previous.value;
            tail.previous = tail.previous.previous;
            tail.previous.next = tail;
            --size;
            return Optional.of(last);
        }
    }

    /**
     * Returns the value from the beginning of the list. Does not remove it from the list.
     *
     * @return an optional value of T, it is empty is this list is empty
     */
    public Optional<T> first() {
        return Optional.ofNullable(head.next.value);
    }

    /**
     * Returns the value from the end of the list. Does not remove it from the list.
     *
     * @return an optional value of T, it is empty is this list is empty
     */
    public Optional<T> last() {
        return Optional.ofNullable(tail.previous.value);
    }

    /**
     * Returns the number of elements stored in the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Searches for the element in the list and returns its' index.
     *
     * @param value the value to look for
     * @return the optional index, it is empty if the item was not found in the list
     */
    public Optional<Integer> findFirstIndex(final T value) {
        return findFirstIndex((other) -> value.equals(other));
    }

    /**
     * Iterates the list and returns the first element for which the specified predicate returns true.
     *
     * @param pred the predicate to use to look for a value
     * @return the optional index, it is empty if the item was not found in the list
     */
    public Optional<Integer> findFirstIndex(final Function<T, Boolean> pred) {
        var node = head;
        var idx = 0;
        while(node.next != tail) {
            node = node.next;
            if (pred.apply(node.value)) {
                return Optional.of(idx);
            }

            idx++;
        }

        return Optional.empty();
    }

    /**
     * Returns the value at the specified index.
     *
     * @return an optional value, it is empty if the index is out of bounds or the list is empty
     */
    public Optional<T> at(int index) {
        if (isEmpty() || index > size || index < 0) {
            return Optional.empty();
        } else {
            var node = head.next;
            while(index > 0) {
                node = node.next;
                --index;
            }
            return Optional.of(node.value);
        }
    }

    /**
     * Removes the value at the specified index.
     *
     * @return the optional removed value, it is empty if the index is out of bounds or the list is empty
     */
    public Optional<T> removeAt(int index) {
        if (isEmpty() || index > size || index < 0) {
            return Optional.empty();
        } else {
            var nodeToRemove = nodeAt(index);
            nodeToRemove.previous.next = nodeToRemove.next;
            nodeToRemove.next.previous = nodeToRemove.previous;
            return Optional.of(nodeToRemove.value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(head, tail);
    }

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    public static <T> Collector<T, LinkedList<T>, LinkedList<T>> collector() {
        return Collector.of(
                LinkedList::new,
                LinkedList::append,
                LinkedList::append
        );
    }

    private Node<T> nodeAt(int index) {
        var node = head.next;

        while(index > 0) {
            node = node.next;
            --index;
        }

        return node;
    }

    /**
     * The linked list node. It has references for the predecessor and successor, and stores a value of U.
     *
     * @param <U>
     */
    class Node<U> {
        public Node<U> previous;
        public Node<U> next;
        public U value;

        public Node() {
        }

        public Node(U value) {
            this.value = value;
        }
    }

    class ListIterator<U> implements Iterator<U> {

        private final Node<U> tail;
        private Node<U> current;

        public ListIterator(final Node<U> head, final Node<U> tail) {
            this.tail = tail;
            this.current = head.next;
        }

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public U next() {
            var value = current.value;
            current = current.next;
            return value;
        }
    }
}
