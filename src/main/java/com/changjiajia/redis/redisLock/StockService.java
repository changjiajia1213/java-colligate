package com.changjiajia.redis.redisLock;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 22:03
 * 描述:
 **/
@Component
public class StockService {

    @Autowired
    private LockDao lockDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void updateStock(Long goodsId, String userId) {

        // 加锁获取库存
        String gid = goodsId.toString();
        boolean lock = RedisLock.getLock(redisTemplate, gid, userId, "50");
        // 获取锁成功
        if (lock) {

            try {
                // 查询库存
                Stock stockByGoodsId = lockDao.getStockByGoodsId(goodsId);
                // 做减库存的处理
                stockByGoodsId.setStock(stockByGoodsId.getStock() - 1);
                // 更新库存
                lockDao.updateStock(stockByGoodsId);

                System.out.println("********锁库存成功******剩余库存：" + stockByGoodsId.getStock());
            } finally {
                // 释放锁
                RedisLock.unLock(redisTemplate, gid, userId);
            }

        } else {
            Stock stock = new Stock();
            stock.setGoodsId(goodsId);
            stock.setUserId(userId);
            // 失败的话，将获取锁失败的请求加入消息队列通过消息队列继续尝试获取锁
            redisTemplate.convertAndSend("LOCK", JSON.toJSONString(stock));
        }

    }


}
