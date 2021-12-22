package myredis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;



@Configuration
public class RedisConfig {
    private static final Logger LOGGER = LogManager.getLogger(RedisConfig.class);
    @Autowired
    Environment environment;


    @Bean
    StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }


    @Bean
    JedisPoolConfig redisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
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
