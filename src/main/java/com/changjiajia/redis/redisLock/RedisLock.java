package com.changjiajia.redis.redisLock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 17:26
 * 描述: Redis分布式锁
 **/
public class RedisLock {

    /**
     * 获取锁
     *
     * @param redisTemplate
     * @param goodsId       商品ID
     * @param userId        用户ID
     * @param expire        指定时间的时候根据自己的业务执行时间去制定
     * @return
     */
    public static boolean getLock(RedisTemplate<String, String> redisTemplate, String goodsId, String userId, String expire) {

        String lua = "if redis.call('setNx',KEYS[1],ARGV[1])==1 then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end";
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setScriptText(lua);
        defaultRedisScript.setResultType(Long.class);

        // 执行lua脚本
        Long execute = redisTemplate.execute(defaultRedisScript, Collections.singletonList(goodsId), userId, expire);
        if (execute == 1) {
            // 获取锁成功
            return true;
        } else {
            // 获取锁失败
            return false;
        }

    }


    /**
     * 释放锁
     *
     * @param redisTemplate
     * @param goodsId
     * @param userId 释放锁的时候要使用原来加锁的USERID 保证谁的锁谁释放
     * @return
     */
    public static boolean unLock(RedisTemplate<String, String> redisTemplate, String goodsId, String userId) {

        String unlock = "if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setScriptText(unlock);
        defaultRedisScript.setResultType(Long.class);

        // 释放锁
        Long execute = redisTemplate.execute(defaultRedisScript, Collections.singletonList(goodsId), userId);
        if (execute == 1) {
            // 释放锁成功
            return true;
        } else {
            // 释放锁失败
            return false;
        }

    }
}
