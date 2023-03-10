package com.clevertec.clevertectesttaskrest.config;

import com.clevertec.clevertectesttaskrest.cache.Cache;
import com.clevertec.clevertectesttaskrest.cache.CacheFactory;
import com.clevertec.clevertectesttaskrest.cache.CacheProperties;
import com.clevertec.clevertectesttaskrest.domain.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CacheConfig {
    private final CacheProperties cacheProperties;

    @Bean
    public Cache<Long, Contact> getContactCache() {
        return CacheFactory.getInstance().getCache(cacheProperties.getEvictStrategy(),
                cacheProperties.getCacheCapacity());
    }
}
