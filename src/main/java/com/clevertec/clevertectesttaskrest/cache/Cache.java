package com.clevertec.clevertectesttaskrest.cache;

import java.util.Optional;

/**
 * @param <K> - key for entry
 * @param <V> - value to store in cache
 */
public interface Cache<K, V> {
    /**
     * @param key - key for entry
     * @param value - value to save
     * @return true if value was added to cache, false if not
     */
    boolean put(K key, V value);

    /**
     * @param key - key to find entry in cache
     * @return object if it was found, Optional.empty() if not
     */
    Optional<V> get(K key);


    /**
     * @param key - key to delete entry from cache
     * @return true if value was removed, false if not
     */
    boolean remove(K key);
}
