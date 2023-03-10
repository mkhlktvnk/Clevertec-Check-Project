package com.clevertec.clevertectesttaskrest.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CacheProperties implements EnvironmentAware {
    private String evictStrategy;
    private Integer cacheCapacity;

    @Override
    public void setEnvironment(Environment environment) {
        this.evictStrategy = environment.getProperty("cache.evict-strategy");
        this.cacheCapacity = Integer.valueOf(environment.getProperty("cache.capacity"));
    }
}
