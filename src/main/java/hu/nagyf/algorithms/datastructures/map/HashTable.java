package hu.nagyf.algorithms.datastructures.map;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Optional;

import hu.nagyf.algorithms.datastructures.LinkedList;

public class HashTable<K, V> {
    private static final int DEFAULT_SIZE = 128;

    private LinkedList<Item<K, V>>[] table;
    private int tableSize;

    @SuppressWarnings("unchecked")
    public HashTable(final int tableSize) {
        this.table = (LinkedList<Item<K, V>>[])Array.newInstance(LinkedList.class, tableSize);
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
        if (table[hash] == null) {
            table[hash] = new LinkedList<>();
        }

        table[hash].append(item);
    }

    public Optional<V> get(final K key) {
        var hash = hash(key);
        if (table[hash] == null || table[hash].isEmpty()) {
            return Optional.empty();
        }

        return table[hash].stream()
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
