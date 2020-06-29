package com.changjiajia.java.xianchengchi;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 16:03
 * 描述:
 **/
public class MyRejectImpl implements RejectedExecutionHandler {

    private LinkedBlockingQueue<Runnable> linkedBlockingQueue;


    public MyRejectImpl(LinkedBlockingQueue<Runnable> linkedBlockingQueue) {
        this.linkedBlockingQueue = linkedBlockingQueue;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        /*try {
            linkedBlockingQueue.put(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // 如果任务溢出则再次加入队列
        executor.execute(r);
    }
}
