package com.changjiajia.java.xiancheng;

import java.util.List;

/**
 * @program: java-colligate
 * @description: synchronized 代码块的使用 来完成同步操作
 * @author: ChangJiaJia
 * @create: 2020-06-23 21:36
 **/
public class RunableA implements Runnable {

    private List<Integer> list;

    public RunableA(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            try {
                System.out.println("========== add A ========");
                synchronized (RunableA.class){
                    list.add(i);
                }
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
