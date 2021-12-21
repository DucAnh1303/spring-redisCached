package myredis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


import java.util.Arrays;
import java.util.List;


@Configuration
public class RedisConfig {
    private static final Logger LOGGER = LogManager.getLogger(RedisConfig.class);
    @Autowired
    Environment environment;
    @Value("${redis.maxIdle}")
    int maxIdle;

    @Value("${redis.minIdle}")
    int minIdle;

    @Value("${redis.testOnBorrow}")
    boolean testOnBorrow;

    @Value("${redis.testOnReturn}")
    boolean testOnReturn;

    @Value("${redis.testWhileIdle}")
    boolean testWhileIdle;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    long timeBetweenEvictionRunsMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    int numTestsPerEvictionRun;

    @Value("${redis.softMinEvictableIdleTimeMillis}")
    long softMinEvictableIdleTimeMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    long minEvictableIdleTimeMillis;

    @Bean
    StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }


    @Bean
    JedisPoolConfig redisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMinIdle(10);
        return jedisPoolConfig;
    }

    @Primary
    @Bean
    JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory(redisPoolConfig());
    }

    @Bean
    RedisTemplate redisUICluster() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(stringRedisSerializer());
        return redisTemplate;
    }

}
