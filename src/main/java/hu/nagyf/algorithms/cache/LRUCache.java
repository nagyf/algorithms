package hu.nagyf.algorithms.cache;

import java.util.Optional;

import hu.nagyf.algorithms.datastructures.LinkedList;
import hu.nagyf.algorithms.datastructures.map.HashTable;

/**
 * Least Recently Used cache: a fix sized cache that removes items from the cache if it runs out of space.
 * Always the least recently used (i.e. the oldest) value will be removed.
 *
 * @param <K> type of the key
 * @param <V> type of the value
 */
public class LRUCache<K, V> implements Cache<K, V> {

    /**
     * This is the data structure of the cache.
     */
    private HashTable<K, V> values;

    /**
     * Used to store the keys that are currently in the cache. Only {@link #capacity} number of keys are allowed.
     */
    private LinkedList<K> keys;
    private int capacity;

    /**
     * Initialize the cache with a capacity.
     *
     * @param capacity must be greater than 0.
     */
    public LRUCache(final int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The cache must be greater than 0");
        }

        this.capacity = capacity;
        values = new HashTable<>();
        keys = new LinkedList<>();
    }

    @Override
    public Optional<V> get(final K key) {
        var value = values.get(key);
        if (value.isPresent()) {
            bringToFront(key);
        }
        return value;
    }

    @Override
    public void put(final K key, final V value) {
        if (keys.size() + 1 <= capacity) {
            keys.insert(key);
            values.put(key, value);
        } else if (keys.findFirstIndex(key).isPresent()) {
            // The key is already present, so we just need to bring it to the front
            bringToFront(key);
        } else {
            // Remove the oldest key and value
            var lastKey = keys.removeLast();
            values.remove(lastKey.get());

            // Insert the new key
            keys.insert(key);
            values.put(key, value);
        }
    }

    private void bringToFront(K key) {
        var index = keys.findFirstIndex(key);
        if (index.isPresent()) {
            keys.removeAt(index.get());
            keys.insert(key);
        }
    }
}
