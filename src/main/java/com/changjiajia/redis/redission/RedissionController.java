package com.changjiajia.redis.redission;

import com.changjiajia.redis.redisLock.StockService;
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
public class RedissionController {

    @Autowired
    private StockRedisdionService stockRedisdionService;

    /**
     * 锁库存
     * 使用Redission做分布式锁测试
     *
     * @return
     */
    @RequestMapping("lock2")
    public String lockStock2() {
        String userId = UID.getUUID16();
        stockRedisdionService.updateStock(100L, userId);
        return "OK";
    }

}
