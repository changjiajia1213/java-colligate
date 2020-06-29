package com.changjiajia.java.xianchengchi.anli;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 17:11
 * 描述:
 **/
public class LianJieStarTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new LianJieStar(scheduledExecutorService),0,3, TimeUnit.SECONDS);
    }

}
