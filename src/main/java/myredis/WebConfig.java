package myredis;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class WebConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache deleteEmployee = new ConcurrentMapCache("deleteEmployee");
        Cache employeeId = new ConcurrentMapCache("employeeId");
        cacheManager.setCaches(Arrays.asList(deleteEmployee, employeeId));
        return cacheManager;
    }
}
