package com.changjiajia.leguan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-17 20:45
 * 描述:
 **/
@Component
public class OptimisticLockListener implements MessageListener {

    @Autowired
    private OptimisticLockService optimisticLockService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String goodsId = new String(message.getBody());

        System.out.println("====进入Redis队列====");

        optimisticLockService.lockStock(Long.valueOf(goodsId));
    }
}
