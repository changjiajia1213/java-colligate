package com.changjiajia.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-09 17:23
 * 描述: 创建不同的Redis序列化方式实例
 **/
@Configuration
public class SerializerTestConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * Jackson2JsonRedisSerializer 序列化方式
     * @return
     */
    @Bean("redisTemplate1")
    public RedisTemplate<String, Object> getRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.setStringSerializer(new StringRedisSerializer());

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * GenericJackson2JsonRedisSerializer 序列化方式
     * @return
     */
    @Bean("redisTemplate2")
    public RedisTemplate<String,Object> getRedisTemplate2(){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setStringSerializer(new StringRedisSerializer());

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     *  JDK序列化方式 默认使用
     * @return
     */
    @Bean("redisTemplate3")
    public RedisTemplate<String,Object> getRedisTemplate3(){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}
