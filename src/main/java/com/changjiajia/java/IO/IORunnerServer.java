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
public class IORunnerServer implements Runnable {

    private ServerSocket serverSocket;

    public IORunnerServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            //ServerSocket serverSocket = new ServerSocket(9998);
            while (true) {
                // 等待接收信息 接收不到信息会阻塞到这里
                Socket accept = serverSocket.accept();
                // 处理信息
                InputStream inputStream = accept.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String readLine = bufferedReader.readLine();
                System.out.println("===server 接收data--->" + readLine);

                // 给客户端响应数据

                String substring = readLine.substring(0, 1);

                OutputStream outputStream = accept.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println("====server send to client--->" + Thread.currentThread().getName() + "==client-->" + substring);

                printWriter.flush();

                // 关闭流
                printWriter.close();
                outputStream.close();
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
