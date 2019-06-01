package hu.nagyf.algorithms.datastructures;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.previous = head;
    }

    public boolean isEmpty() {
        return head.next == tail;
    }

    public void insert(final T value) {
        var node = new Node<>(value);

        node.next = head.next;
        node.previous = head;

        head.next.previous = node;
        head.next = node;
        ++size;
    }

    public void append(final T value) {
        var node = new Node<>(value);

        node.next = tail;
        node.previous = tail.previous;

        tail.previous.next = node;
        tail.previous = node;
        ++size;
    }

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

    public Optional<T> first() {
        return Optional.ofNullable(head.next.value);
    }

    public Optional<T> last() {
        return Optional.ofNullable(tail.previous.value);
    }

    public int size() {
        return size;
    }

    public Optional<Integer> findIndex(final T value) {
        var node = head;
        var idx = 0;
        while(node.next != tail) {
            node = node.next;
            if (value.equals(node.value)) {
                return Optional.of(idx);
            }

            idx++;
        }

        return Optional.empty();
    }

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

    private Node<T> nodeAt(int index) {
        var node = head.next;

        while(index > 0) {
            node = node.next;
            --index;
        }

        return node;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(head, tail);
    }

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

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
