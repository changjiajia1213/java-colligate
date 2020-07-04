package com.changjiajia.java.IO;

import java.io.*;
import java.net.Socket;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-03 15:18
 * 描述:
 **/
public class IORunnerClient implements Runnable {


    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", 9998);
            // 获取一个输出流 向外界发消息
            OutputStream outputStream = socket.getOutputStream();
            // 向外界发消息
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(Thread.currentThread().getName() + "====client send to server====");
            // 刷新数据
            printWriter.flush();

            // 获取一个输入流 接收外界传来的消息
            InputStream inputStream = socket.getInputStream();
            // 接收外界传来的消息
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // 字符流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String readLine = bufferedReader.readLine();
            System.out.println("===client接收server data--->" + readLine);

            // 关闭流
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
