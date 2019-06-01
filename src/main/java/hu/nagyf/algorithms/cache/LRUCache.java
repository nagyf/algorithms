package hu.nagyf.algorithms.cache;

import java.util.HashMap;
import java.util.Optional;

import hu.nagyf.algorithms.datastructures.LinkedList;

/**
 * Least Recently Used cache.
 *
 * @param <K> type of the key
 * @param <V> type of the value
 */
public class LRUCache<K, V> implements Cache<K, V> {

    private HashMap<K, V> values;
    private LinkedList<K> keys;
    private int capacity;

    public LRUCache(final int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The cache must be greater than 0");
        }

        this.capacity = capacity;
        values = new HashMap<>();
        keys = new LinkedList<>();
    }

    @Override
    public Optional<V> get(final K key) {
        var value = Optional.ofNullable(values.get(key));
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
        } else if (keys.findIndex(key).isPresent()) {
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
        var index = keys.findIndex(key);
        if (index.isPresent()) {
            keys.removeAt(index.get());
            keys.insert(key);
        }
    }
}
