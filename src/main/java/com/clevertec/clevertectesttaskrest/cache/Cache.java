package com.clevertec.clevertectesttaskrest.cache;

import java.util.Optional;

public interface Cache<K, V> {
    boolean put(K key, V value);

    Optional<V> get(K key);

    boolean remove(K key);
}
