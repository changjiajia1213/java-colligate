package com.changjiajia.zookeeper.zkLock;

import com.changjiajia.redis.redisLock.LockDao;
import com.changjiajia.entity.Stock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-17 16:36
 * 描述: zookeeper实现分布式锁
 **/
@Component
public class ZkLockService {

    @Autowired
    private LockDao lockDao;

    @Autowired
    private CuratorFramework curatorFramework;

    public void lockStock(Long goodsId) throws Exception {
        // 重试策略：每隔1秒重试一次，重试3次  -->优化放入config
        //RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        // 创建客户端  -->优化放入config
        //CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        // 开启客户端
        //curatorFramework.start();

        // 获取锁对象
        // InterProcessSemaphoreMutex 也可以，但是性能不如InterProcessMutex
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/LOCK");
        try {
            // 获取锁 这个方法不要参数是获取不到锁会一直获取
            interProcessMutex.acquire();
            // 执行业务
            // 根据商品ID获取库存
            Stock stockByGoodsId = lockDao.getStockByGoodsId(goodsId);
            if (stockByGoodsId.getStock() >= 1) {
                // 操作库存减一
                stockByGoodsId.setStock(stockByGoodsId.getStock() - 1);
                // 更新数据库库存
                lockDao.updateStock(stockByGoodsId);
                System.out.println("======锁定库存====剩余库存:" + stockByGoodsId.getStock());
            }
        } finally {
            // 释放锁
            interProcessMutex.release();
        }

    }

}
