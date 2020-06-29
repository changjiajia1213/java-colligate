package com.changjiajia.java.xianchengchi;

import com.changjiajia.java.xiancheng.MyCallable;

import java.util.concurrent.*;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 15:29
 * 描述:
 **/
public class XianChengChiTest01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 使用LinkedBlockingQueue队列
        /*LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4 // 核心线程数
                , 4 // 最大线程数
                , 60 // 线程存活时间
                , TimeUnit.SECONDS // 线程存活时间参数
                , linkedBlockingQueue // 工作队列
        );

        // 执行任务
        threadPoolExecutor.execute(new RunableExec());

        // 关闭线程池 -> 不再接收任务并将已有任务队列完成后关闭
        threadPoolExecutor.shutdown();

        // 关闭线程池 -> 执行完现在执行的任务直接关闭，其他未执行的任务不再执行
        //threadPoolExecutor.shutdownNow();*/


        // 使用ArrayBlockingQueue队列
        /*ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(2);

        LinkedBlockingQueue<Runnable> linkedBlockingQueue2 = new LinkedBlockingQueue<>();

        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, arrayBlockingQueue, new MyRejectImpl(linkedBlockingQueue2));

        for (int i = 0; i < 5; i++) {
            threadPoolExecutor2.execute(new RunableExec2(i));
        }

        threadPoolExecutor2.shutdown();*/

        // 使用定长的线程池
        /*ExecutorService executorService = Executors.newFixedThreadPool(1);
        // 带返回值
        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());

        executorService.submit(futureTask);

        String returnVal = futureTask.get();

        System.out.println(returnVal);*/


        // 使用定时的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // 按照固定频率调用 设置中间间隔2秒执行一次 ->上个任务执行完才会执行下一个
        scheduledExecutorService.scheduleAtFixedRate(new RunableExec2(1), 0, 2, TimeUnit.SECONDS);
        // 按照固定频率调用 设置中间间隔2秒执行一次 ->不管上个任务执行完没有2秒都会执行下一个
        scheduledExecutorService.scheduleWithFixedDelay(new RunableExec2(1), 0, 2, TimeUnit.SECONDS);
        // 关闭线程池
        scheduledExecutorService.shutdown();

    }

}
