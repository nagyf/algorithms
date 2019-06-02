package hu.nagyf.algorithms.cache;

import java.util.Optional;

/**
 * Generic Cache interface for various cache implementations.
 *
 * @param <K> The type of the key
 * @param <V> The type of the value
 */
public interface Cache<K, V> {

    /**
     * Return a value from the cache.
     *
     * @param key the key to retrieve a value for
     * @return an Optional value of V
     */
    Optional<V> get(final K key);

    /**
     * Stores a new value in the cache.
     *
     * @param key the key for the new value
     * @param value the value itself
     */
    void put(final K key, final V value);
}
