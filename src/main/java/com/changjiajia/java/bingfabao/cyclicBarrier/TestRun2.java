package com.changjiajia.java.bingfabao.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 17:53
 * 描述: 模拟跑步比赛运动员进场 -> 起跑
 **/
public class TestRun2 implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private long time;

    public TestRun2(CyclicBarrier cyclicBarrier, long time) {
        this.cyclicBarrier = cyclicBarrier;
        this.time = time;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
            System.out.println(Thread.currentThread().getName() + ":到达起跑线，准备就绪！");
            cyclicBarrier.await();
            System.out.println("所有运动员准备就绪，开跑！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
