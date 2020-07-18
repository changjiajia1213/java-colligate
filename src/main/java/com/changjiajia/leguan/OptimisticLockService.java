package com.changjiajia.leguan;

import com.changjiajia.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-17 20:43
 * 描述:
 **/
@Component
public class OptimisticLockService {

    @Autowired
    private OptimisticLockDao optimisticLockDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 处理获取不到锁更新库存失败的情况
     * 第一种方式：更新失败发送到消息队列（Redis队列）
     *
     * @param goodsId
     */
    public void lockStock(Long goodsId) {

        // 获取库存
        Stock stockByGoodsId = optimisticLockDao.getStockByGoodsId(goodsId);
        // 库存减一
        if (stockByGoodsId.getStock() >= 1) {
            stockByGoodsId.setStock(stockByGoodsId.getStock() - 1);
        }

        // 更新库存
        int i = optimisticLockDao.updateStockOptimistic(stockByGoodsId);

        // 如果更新失败
        if (i == 0) {
            // 第一种方式
            // 更新失败发送到消息队列
            redisTemplate.convertAndSend("LEGUAN", goodsId.toString());
        } else {
            System.out.println("================更新库存成功=======剩余量:" + stockByGoodsId.getStock());
            // help for GC
            stockByGoodsId = null;
        }

    }

    /**
     * 处理获取不到锁更新库存失败的情况
     * 第二种方式：使用并发包
     * 吞吐量少的原因是因为数据库配置了binlog（保证缓存一致性，会影响数据库的性能）
     *
     * @param goodsId
     */
    public void lockStock2(Long goodsId) {

        // 第二种方式：并发包
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // 获取不到锁进入自旋
        for (; ; ) {
            // 获取库存
            Stock stockByGoodsId = optimisticLockDao.getStockByGoodsId(goodsId);
            // 库存减一
            if (stockByGoodsId.getStock() >= 1) {
                stockByGoodsId.setStock(stockByGoodsId.getStock() - 1);
            } else {
                // 库存不足结束自旋
                countDownLatch.countDown();
                return;
            }

            // 更新库存
            int i = optimisticLockDao.updateStockOptimistic(stockByGoodsId);

            // 如果更新失败进入自旋状态，如果更新成功结束自旋，不结束自旋会产生死锁
            // 更新库存成功
            if (i == 1) {
                System.out.println("================更新库存成功=======剩余量:" + stockByGoodsId.getStock());
                // help for GC
                stockByGoodsId = null;
                countDownLatch.countDown();
                return;
            }
        }

    }

}
