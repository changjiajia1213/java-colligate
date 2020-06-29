package com.changjiajia.java.xiancheng;

import java.util.List;

/**
 * @program: java-colligate
 * @description: synchronized 加在普通方法和静态方法上的区别
 * @author: ChangJiaJia
 * @create: 2020-06-23 21:36
 **/
public class RunableB implements Runnable {

    private List<Integer> list;

    public RunableB(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            try {
                System.out.println("========== add B ========");
                this.add01(i);
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized 在普通方法上 只能锁住一个类的同一实例对象
     * @param integer
     */
    public synchronized void add01(Integer integer){

        list.add(integer);

    }

    /**
     * synchronized 在静态方法上 可以锁住一个类类
     * @param integer
     */
    public static synchronized void add02(Integer integer){

        //list.add(integer);

    }
}
