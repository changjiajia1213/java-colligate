package com.changjiajia.java.bingfabao.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 18:09
 * 描述: Semaphore使用测试
 **/
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        TestRun3 testRun1 = new TestRun3(semaphore, 2000);
        TestRun3 testRun2 = new TestRun3(semaphore, 2000);
        TestRun3 testRun3 = new TestRun3(semaphore, 2000);
        TestRun3 testRun4 = new TestRun3(semaphore, 2000);

        Thread thread1 = new Thread(testRun1, "运动员A");
        Thread thread2 = new Thread(testRun2, "运动员B");
        Thread thread3 = new Thread(testRun3, "运动员C");
        Thread thread4 = new Thread(testRun4, "运动员D");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

}
