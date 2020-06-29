package com.changjiajia.java.bingfabao.countdownLunch;

import java.util.concurrent.CountDownLatch;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 17:22
 * 描述: CountdownLunch并发包测试使用 CountdownLunch->一次性使用
 **/
public class CountdownLunchTest {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(4);

        TestRun01 testRun01 = new TestRun01(countDownLatch);

        Thread thread = new Thread(testRun01,"A同学");
        thread.start();

        Thread thread2 = new Thread(testRun01,"B同学");
        thread2.start();

        Thread thread3 = new Thread(testRun01,"C同学");
        thread3.start();

        Thread thread4 = new Thread(testRun01,"D同学");
        thread4.start();

        countDownLatch.await();

        System.out.println("老师：好，我们开始进行下一个知识点讲解！");
    }

}
