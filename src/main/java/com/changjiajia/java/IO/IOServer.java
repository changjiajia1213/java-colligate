package com.changjiajia.java.IO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-03 15:17
 * 描述:
 **/
public class IOServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9998);

        IORunnerServer ioRunnerServer = new IORunnerServer(serverSocket);
        Thread thread1 = new Thread(ioRunnerServer, "T1");
        Thread thread2 = new Thread(ioRunnerServer, "T2");
        Thread thread3 = new Thread(ioRunnerServer, "T3");

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
