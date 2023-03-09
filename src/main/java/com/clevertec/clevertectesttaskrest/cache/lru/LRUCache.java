package com.clevertec.clevertectesttaskrest.cache.lru;

import com.clevertec.clevertectesttaskrest.cache.Cache;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache<K, V> implements Cache<K, V> {
    private final int capacity;
    private final Map<K, V> keyValueMap;
    private final Set<K> keys;
    private final ReentrantReadWriteLock lock;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.keys = new HashSet<>();
        this.keyValueMap = new ConcurrentHashMap<>(capacity);
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public boolean put(K key, V value) {
        lock.writeLock().lock();
        try {
            if (this.capacity == 0) {
                return false;
            }
            if (keys.contains(key)) {
                keys.remove(key);
            } else if (keys.size() == capacity) {
                K keyToEvict = keys.iterator().next();
                keys.remove(keyToEvict);
                keyValueMap.remove(keyToEvict);
            }
            keys.add(key);
            keyValueMap.put(key, value);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<V> get(K key) {
        lock.readLock().lock();
        try {
            if (!keyValueMap.containsKey(key)) {
                return Optional.empty();
            }
            keys.remove(key);
            keys.add(key);
            return Optional.of(keyValueMap.get(key));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean remove(K key) {
        lock.writeLock().lock();
        try {
            if (!keys.contains(key)) {
                return false;
            }
            keys.remove(key);
            keyValueMap.remove(key);
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }
}
