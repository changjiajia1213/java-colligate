package com.changjiajia.java.IO;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-03 15:17
 * 描述:
 **/
public class IOClient {

    public static void main(String[] args) {
        IORunnerClient ioRunnerClient = new IORunnerClient();

        Thread thread1 = new Thread(ioRunnerClient, "A");
        Thread thread2 = new Thread(ioRunnerClient, "B");
        Thread thread3 = new Thread(ioRunnerClient, "C");

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
