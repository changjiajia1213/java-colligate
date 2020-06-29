package com.changjiajia.java.xianchengchi;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 15:50
 * 描述:
 **/
public class RunableExec2 implements Runnable {

    private int i;

    public RunableExec2(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            System.out.println("======= RunableExec2 ======" + i);
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
