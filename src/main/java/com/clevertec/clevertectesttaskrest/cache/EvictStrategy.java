package com.clevertec.clevertectesttaskrest.cache;

public enum EvictStrategy {
    LFU("LFU"), LRU("LRU");

    private final String name;

    EvictStrategy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
