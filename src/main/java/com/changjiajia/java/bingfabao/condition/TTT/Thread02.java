package com.changjiajia.java.bingfabao.condition.TTT;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 21:05
 * 描述:
 **/
public class Thread02 extends Thread {

    private ThreadTestUtils threadTestUtils;

    public Thread02(ThreadTestUtils threadTestUtils) {
        this.threadTestUtils = threadTestUtils;
    }

    @Override
    public void run() {
        try {
            System.out.println("==========B-WAIT===");
            threadTestUtils.waitB();
            System.out.println("==========B-RUN===");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
