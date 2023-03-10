package com.clevertec.clevertectesttaskrest.cache;

import com.clevertec.clevertectesttaskrest.cache.lfu.LFUCache;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public class CacheFactory {
    public <K, V> Cache<K, V> getCache(String evictStrategy, int capacity) {
        if (evictStrategy.equals(EvictStrategy.LRU.getName())) {
            return new LFUCache<>(capacity);
        } else if (evictStrategy.equals(EvictStrategy.LFU.getName())) {
            return new LFUCache<>(capacity);
        }
        throw new IllegalArgumentException("Unknown evict strategy: " + evictStrategy);
    }
}
