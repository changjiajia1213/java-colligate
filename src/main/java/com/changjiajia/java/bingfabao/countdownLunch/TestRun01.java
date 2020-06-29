package com.changjiajia.java.bingfabao.countdownLunch;

import java.util.concurrent.CountDownLatch;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 17:25
 * 描述: 模拟上课：当4名同学都明白后，老师才能开始下一个知识点的讲解
 **/
public class TestRun01 implements Runnable {

    private CountDownLatch countDownLatch;

    public TestRun01(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"说：我明白了！");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
