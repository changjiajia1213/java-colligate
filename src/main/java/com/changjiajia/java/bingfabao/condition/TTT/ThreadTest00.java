package com.changjiajia.java.bingfabao.condition.TTT;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 20:58
 * 描述:
 **/
public class ThreadTest00 {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        ThreadTestUtils threadTestUtils = new ThreadTestUtils(object);

        Thread01 thread01 = new Thread01(threadTestUtils);
        thread01.start();
        Thread02 thread02 = new Thread02(threadTestUtils);
        thread02.start();

        Thread.sleep(2000);

        threadTestUtils.notifyB();
    }

}
