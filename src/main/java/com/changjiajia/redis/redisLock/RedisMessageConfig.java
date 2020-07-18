package com.changjiajia.redis.redisLock;

import com.changjiajia.leguan.OptimisticLockListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 22:15
 * 描述:
 **/
@Configuration
public class RedisMessageConfig {

    @Autowired
    private MyRedisMessageListener myRedisMessageListener;

    /**
     * 乐观锁消息监听
     */
    @Autowired
    private OptimisticLockListener optimisticLockListener;

    /**
     * 创建messageListenerContainer容器
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(myRedisMessageListener, new PatternTopic("LOCK"));

        redisMessageListenerContainer.addMessageListener(optimisticLockListener, new PatternTopic("LEGUAN"));

        return redisMessageListenerContainer;
    }

}
