package com.example.online_program.config;


import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Author: qfl
 * @Date: 19-1-13 上午11:49
 * @Description:
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public CacheManager mycacheManager(RedisConnectionFactory factory) {
        //设置缓存过期时间
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1)); // 设置缓存有效期一小时
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(factory);
//        return redisTemplate;
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {


        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();


        template.setConnectionFactory(redisConnectionFactory);
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        template.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class) );

        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.afterPropertiesSet();


        return template;
    }
//    @Bean
//    public RedisTemplate<String, Object> UserInforedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//
//
//        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//
//
//        template.setConnectionFactory(redisConnectionFactory);
//    //        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        template.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
//        template.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
//
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//
//        template.afterPropertiesSet();
//
//
//        return template;
//    }

}
