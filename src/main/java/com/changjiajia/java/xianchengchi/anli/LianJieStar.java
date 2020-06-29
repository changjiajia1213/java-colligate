package com.changjiajia.java.xianchengchi.anli;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 17:04
 * 描述: 案例：重试连接3次卫星，3次未连接上则关闭线程池
 **/
public class LianJieStar implements Runnable {

    private static int num = 1;

    private ScheduledExecutorService scheduledExecutorService;

    public LianJieStar(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @Override
    public void run() {

        if (num > 3) {
            // 关闭线程池
            scheduledExecutorService.shutdown();
        } else {
            System.out.println("第" + num + "次======发送HTTP请求==尝试连接卫星========");
            num++;
        }

    }
}
