package com.changjiajia.redis.redisLock;

import com.changjiajia.redis.utils.UID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 22:18
 * 描述:
 **/
@RestController
public class RedisLockController {

    @Autowired
    private StockService stockService;

    /**
     * 锁库存
     *
     * @return
     */
    @RequestMapping("lock")
    public String lockStock() {
        String userId = UID.getUUID16();
        stockService.updateStock(100L, userId);
        return "OK";
    }

}
