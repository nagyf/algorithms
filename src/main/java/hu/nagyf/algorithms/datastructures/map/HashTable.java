package hu.nagyf.algorithms.datastructures.map;

import java.util.Objects;
import java.util.Optional;

import hu.nagyf.algorithms.datastructures.Array;
import hu.nagyf.algorithms.datastructures.LinkedList;

public class HashTable<K, V> {
    private static final int DEFAULT_SIZE = 128;

    private Array<LinkedList<Item<K, V>>> table;
    private int tableSize;

    public HashTable(final int tableSize) {
        this.table = new Array<>(tableSize);
        this.tableSize = tableSize;
    }

    public HashTable() {
        this(DEFAULT_SIZE);
    }

    public void put(final K key, final V value) {
        if (key == null) {
            throw new IllegalArgumentException("Hashtable key cannot be null");
        }

        final var hash = hash(key);
        final var item = new Item<>(key, value);
        if (table.get(hash) == null) {
            table.set(hash, new LinkedList<>());
        }

        table.get(hash).append(item);
    }

    public Optional<V> get(final K key) {
        var hash = hash(key);
        if (table.get(hash) == null || table.get(hash).isEmpty()) {
            return Optional.empty();
        }

        return table.get(hash).stream()
                .filter((item) -> item.key.equals(key))
                .findFirst()
                .map((item) -> item.value);
    }

    int hash(final K key) {
        return key.hashCode() % tableSize;
    }

    protected static class Item<K, V> {
        public K key;
        public V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Item<?, ?> item = (Item<?, ?>)o;
            return key.equals(item.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
