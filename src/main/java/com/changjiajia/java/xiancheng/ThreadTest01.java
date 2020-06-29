package com.changjiajia.java.xiancheng;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: java-colligate
 * @description: 测试线程01
 * @author: ChangJiaJia
 * @create: 2020-06-23 20:39
 **/
public class ThreadTest01 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*// 创建线程实例
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("=========1====执行自己的任务");
            }
        });
        // 启动线程
        thread.start();*/


        // RunableA的测试
        /*List<Integer> list=new ArrayList<>();

        RunableA runableA = new RunableA(list);
        Thread threadA = new Thread(runableA);

        threadA.start();

        Thread threadB = new Thread(runableA);

        threadB.start();

        // 将线程加入到主线程
        threadA.join();
        threadB.join();

        System.out.println(list.size());*/


        // RunableB的测试
        List<Integer> list=new ArrayList<>();

        RunableB runableB1 = new RunableB(list);
        RunableB runableB2 = new RunableB(list);
        Thread threadA = new Thread(runableB1);

        threadA.start();

        Thread threadB = new Thread(runableB2);

        threadB.start();

        // 将线程加入到主线程
        threadA.join();
        threadB.join();

        System.out.println(list.size());

        // 需要线程带返回值

        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());

        Thread thread2 = new Thread(futureTask);

        thread2.start();

        FutureTask<String> futureTask2 = new FutureTask<>(new MyCallable());

        Thread thread3 = new Thread(futureTask2);

        thread3.start();

        thread2.join();
        thread3.join();

        String returnVal1 = futureTask.get();
        String returnVal2 = futureTask2.get();

        System.out.println(returnVal1+"======="+returnVal2);
    }

}
