package com.changjiajia.java.bingfabao.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 17:52
 * 描述: CyclicBarrier使用测试 -> 可重用
 **/
public class CyclicBarrierTest {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        test(cyclicBarrier);
        // 重用
        //cyclicBarrier.reset();
        //test(cyclicBarrier);
    }

    public static void test(CyclicBarrier cyclicBarrier) {
        TestRun2 testRun1 = new TestRun2(cyclicBarrier, 3000);
        TestRun2 testRun2 = new TestRun2(cyclicBarrier, 5000);
        TestRun2 testRun3 = new TestRun2(cyclicBarrier, 2000);
        TestRun2 testRun4 = new TestRun2(cyclicBarrier, 4000);

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
