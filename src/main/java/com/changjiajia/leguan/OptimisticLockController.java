package com.changjiajia.leguan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-17 20:45
 * 描述:
 **/
@RestController
public class OptimisticLockController {

    @Autowired
    private OptimisticLockService optimisticLockService;

    @RequestMapping("optimisticLock")
    public void optimisticLock() {
        optimisticLockService.lockStock(100L);
    }


    @RequestMapping("optimisticLock2")
    public void optimisticLock2() {
        optimisticLockService.lockStock2(100L);
    }


}
