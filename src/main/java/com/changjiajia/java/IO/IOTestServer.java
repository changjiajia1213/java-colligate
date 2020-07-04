package com.changjiajia.java.IO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-02 18:16
 * 描述: Socket服务端（单个）
 **/
public class IOTestServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9998);
        while (true) {
            // 等待接收信息 接收不到信息会阻塞到这里
            Socket accept = serverSocket.accept();
            // 处理信息
            InputStream inputStream = accept.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String readLine = bufferedReader.readLine();
            System.out.println("=======server 接收data======" + readLine);

            // 给客户端响应数据
            OutputStream outputStream = accept.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println("========server send to client=======" + Thread.currentThread().getName());

            printWriter.flush();

            // 关闭流
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        }

    }

}
