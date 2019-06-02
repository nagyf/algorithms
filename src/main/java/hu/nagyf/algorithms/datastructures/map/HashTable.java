package hu.nagyf.algorithms.datastructures.map;

import java.util.Objects;
import java.util.Optional;

import hu.nagyf.algorithms.datastructures.Array;
import hu.nagyf.algorithms.datastructures.LinkedList;

/**
 * A Hash Table implementation, i.e. a key-value store.
 * This implementation uses an {@link Array} as the hash table, and handles collisions with {@link LinkedList}s.
 *
 * @param <K> the type of the keys
 * @param <V> the type of the values
 */
public class HashTable<K, V> {
    /**
     * The default size of the hash table.
     */
    private static final int DEFAULT_SIZE = 128;

    /**
     * An array of linked lists. Each linked list will store those values for which the hash is the same.
     * Looking up a value requires 3 steps:
     * |    1. Hash the key
     * |    2. Get the linked list based on the hash
     * |    3. Do a linear search in the linked list and find the value
     */
    private Array<LinkedList<Item<K, V>>> table;

    /**
     * The size of the hash table.
     * This does not limit the number of the storable values in the table, only limits the number of
     * unique hashes to store.
     */
    private int tableSize;

    /**
     * Initializes the hash table with the specified size.
     * This does not mean that only tableSize number of items can be stored in the hash table.
     * The number of items can be stored has no limit. This number specifies only the number of unique hashes to be stored.
     * Smaller number means lower memory consumption but higher chance for hash collisions.
     * Bigger number means higher memory consumption but lower chance for hash collisions.
     *
     * @param tableSize the size of the hash table
     */
    public HashTable(final int tableSize) {
        this.table = new Array<>(tableSize);
        this.tableSize = tableSize;
    }

    public HashTable() {
        this(DEFAULT_SIZE);
    }

    /**
     * Puts a new value in the hash table.
     * Overwrites any existing values for the same key.
     *
     * @param key the key, cannot be null
     * @param value the value
     */
    public void put(final K key, final V value) {
        if (key == null) {
            throw new IllegalArgumentException("Hashtable key cannot be null");
        }

        final var hash = hash(key);
        final var item = new Item<>(key, value);
        if (table.get(hash) == null) {
            table.set(hash, new LinkedList<>());
        }

        var list = table.get(hash);
        list.findFirstIndex(i -> i.key.equals(key)).ifPresent(list::removeAt);
        list.append(item);
    }

    /**
     * Returns a value from the hash table with the corresponding key.
     *
     * @param key the key, cannot be null
     * @return the optional value, or empty if it cannot be found in the map
     */
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

    /**
     * Removes the specified key and the corresponding value from the hash table.
     *
     * @param key the key to remove
     */
    public void remove(final K key) {
        var hash = hash(key);

        if (table.get(hash) == null || table.get(hash).isEmpty()) {
            return;
        }

        final LinkedList<Item<K, V>> result = table.get(hash).stream()
                .filter(item -> item.key != key)
                .collect(LinkedList.collector());

        table.set(hash, result);
    }

    /**
     * Returns the hash for the key.
     * The implementation uses the {@link #hashCode()} method and restricts the returned value to the size of the hash table.
     *
     * @param key the key to hash
     * @return the hash
     */
    int hash(final K key) {
        return key.hashCode() % tableSize;
    }

    /**
     * A Key-Value pair used to store hash table values.
     *
     * @param <K> the type of the key
     * @param <V> the type of the value
     */
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
