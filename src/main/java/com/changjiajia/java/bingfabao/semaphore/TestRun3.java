package com.changjiajia.java.bingfabao.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 18:10
 * 描述:
 **/
public class TestRun3 implements Runnable {

    private Semaphore semaphore;
    private long time;

    public TestRun3(Semaphore semaphore, long time) {
        this.semaphore = semaphore;
        this.time = time;
    }

    @Override
    public void run() {
        try {
            // 尝试获取资源 如果获取不到则阻塞到当前位置
            semaphore.acquire();
            // 执行自己的业务操作
            Thread.sleep(time);
            System.out.println(Thread.currentThread().getName()+":执行业务操作！");

            // 释放资源
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
