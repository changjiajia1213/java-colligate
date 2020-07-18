package com.changjiajia.redis.redission;

import com.changjiajia.redis.redisLock.LockDao;
import com.changjiajia.entity.Stock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 22:03
 * 描述:
 **/
@Component
public class StockRedisdionService {

    @Autowired
    private LockDao lockDao;
    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void updateStock(Long goodsId, String userId) {

        RLock lock = redissonClient.getLock(goodsId.toString());

        try {
            // 获取锁
            lock.lock(50, TimeUnit.SECONDS);

            // 查询库存
            Stock stockByGoodsId = lockDao.getStockByGoodsId(goodsId);
            // 做减库存的处理
            stockByGoodsId.setStock(stockByGoodsId.getStock() - 1);
            // 更新库存
            lockDao.updateStock(stockByGoodsId);

            System.out.println("********锁库存成功******剩余库存：" + stockByGoodsId.getStock());

        } finally {
            // 释放锁
            lock.unlock();
        }
    }


}
