package com.clevertec.clevertectesttaskrest.cache.lru;

import com.clevertec.clevertectesttaskrest.cache.Cache;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    @Test
    void checkPutShouldEvictValue() {
        Cache<Long, String> cache = new LRUCache<>(2);
        cache.put(1L, "Some-value-1");
        cache.put(2L, "Some-value-2");
        cache.put(3L, "Some-value-3");

        assertEquals(Optional.empty(), cache.get(1L));
    }

    @Test
    void checkPutShouldReturnFalseWhenCapacityIsZero() {
        Cache<Long, String> cache = new LRUCache<>(0);

        assertFalse(cache.put(1L, "Some-value"));
    }

    @Test
    void checkPutShouldUpdateWhenEntryIsAlreadyPresent() {
        Cache<Long, String> cache = new LRUCache<>(2);
        cache.put(1L, "Some-value-1");
        cache.put(2L, "Some-value-2");
        cache.put(2L, "New-value-2");

        assertEquals("New-value-2", cache.get(2L).get());
    }

    @Test
    void checkRemoveShouldRemoveEntry() {
        Cache<Long, String> cache = new LRUCache<>(2);
        cache.put(1L, "Some-value-1");
        cache.put(2L, "Some-value-2");

        cache.remove(2L);
        assertEquals(Optional.empty(), cache.get(2L));
    }

    @Test
    void checkRemoveShouldReturnFalseWhenKeyIsNotPresent() {
        Cache<Long, String> cache = new LRUCache<>(2);
        cache.put(1L, "Some-value-1");
        cache.put(2L, "Some-value-2");

        assertFalse(cache.remove(10L));
    }
}