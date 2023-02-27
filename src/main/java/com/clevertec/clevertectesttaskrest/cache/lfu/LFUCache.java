package com.clevertec.clevertectesttaskrest.cache.lfu;

import com.clevertec.clevertectesttaskrest.cache.Cache;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LFUCache<K, V> implements Cache<K, V> {
    private final int capacity;
    private final Map<K, V> valueMap;
    private final Map<K, Long> countMap;
    private final Map<Long, LinkedHashSet<K>> frequencyMap;
    private long minUsed = -1;
    private final ReentrantReadWriteLock lock;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.valueMap = new ConcurrentHashMap<>();
        this.countMap = new ConcurrentHashMap<>();
        this.frequencyMap = new ConcurrentHashMap<>();
        this.frequencyMap.put(1L, new LinkedHashSet<>());
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public boolean put(K key, V value) {
        this.lock.writeLock().lock();
        try {
            if (capacity == 0) {
                return false;
            }
            if (valueMap.containsKey(key)) {
                valueMap.put(key, value);
                return true;
            }
            if (valueMap.size() >= capacity) {
                K keyToEvict = frequencyMap.get(minUsed).iterator().next();
                frequencyMap.get(minUsed).remove(keyToEvict);
                countMap.remove(keyToEvict);
                valueMap.remove(keyToEvict);
            }
            minUsed = 1;
            valueMap.put(key, value);
            countMap.put(key, 1L);
            frequencyMap.get(minUsed).add(key);
            return true;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<V> get(K key) {
        this.lock.readLock().lock();
        try {
            if (!valueMap.containsKey(key)) {
                return Optional.empty();
            }
            long count = countMap.get(key);
            countMap.put(key, count + 1);
            frequencyMap.get(count).remove(key);
            if (count == minUsed && frequencyMap.get(count).size() == 0) {
                minUsed++;
            }
            if (!frequencyMap.containsKey(count + 1)) {
                frequencyMap.put(count + 1, new LinkedHashSet<>());
            }
            frequencyMap.get(count + 1).add(key);
            return Optional.of(valueMap.get(key));
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public boolean remove(K key) {
        this.lock.writeLock().lock();
        try {
            if (!valueMap.containsKey(key)) {
                return false;
            }
            long count = countMap.get(key);
            frequencyMap.get(count).remove(key);
            if (count == minUsed && frequencyMap.get(minUsed).size() == 0) {
                frequencyMap.remove(countMap.get(key));
                minUsed = frequencyMap.keySet().stream().min(Long::compare).get();
            }
            countMap.remove(key);
            valueMap.remove(key);
            return true;
        } finally {
            this.lock.writeLock().unlock();
        }
    }
}
