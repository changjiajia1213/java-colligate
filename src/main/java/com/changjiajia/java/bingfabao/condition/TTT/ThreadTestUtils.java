package com.changjiajia.java.bingfabao.condition.TTT;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 21:00
 * 描述:
 **/
public class ThreadTestUtils {

    private Object object;

    public ThreadTestUtils(Object object) {
        this.object = object;
    }

    public void waitA() throws InterruptedException {
        synchronized (object){
            object.wait();
        }
    }

    public void notifyA(){

        synchronized (object){
            object.notify();
        }

    }

    public void waitB() throws InterruptedException {
        synchronized (object){
            object.wait();
        }
    }

    public void notifyB(){

        synchronized (object){
            object.notify();
        }

    }

}
