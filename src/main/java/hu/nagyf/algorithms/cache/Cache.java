package hu.nagyf.algorithms.cache;

import java.util.Optional;

public interface Cache<K, V> {

    Optional<V> get(final K key);

    void put(final K key, final V value);
}
