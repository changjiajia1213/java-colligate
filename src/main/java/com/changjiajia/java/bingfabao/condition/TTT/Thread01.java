package com.changjiajia.java.bingfabao.condition.TTT;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 21:05
 * 描述:
 **/
public class Thread01 extends Thread {

    private ThreadTestUtils threadTestUtils;

    public Thread01(ThreadTestUtils threadTestUtils) {
        this.threadTestUtils = threadTestUtils;
    }

    @Override
    public void run() {
        try {
            System.out.println("==========A-WAIT===");
            threadTestUtils.waitA();
            System.out.println("==========A-RUN===");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
