package com.changjiajia.redis.redisLock;

import com.alibaba.fastjson.JSON;
import com.changjiajia.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 22:02
 * 描述:
 **/
@Component
public class MyRedisMessageListener implements MessageListener {

    @Autowired
    private StockService stockService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        // Redis的通道
        String channel = new String(message.getChannel());
        // Message数据
        String msg = new String(message.getBody());
        System.out.println("=======进入队列=======" + msg);
        // 解析数据
        Stock stock = JSON.parseObject(msg, Stock.class);

        if (channel.equals("LOCK")) {
            stockService.updateStock(stock.getGoodsId(), stock.getUserId());
        }
    }
}
