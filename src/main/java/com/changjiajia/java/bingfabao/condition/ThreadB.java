package com.changjiajia.java.bingfabao.condition;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 19:39
 * 描述:
 **/
public class ThreadB extends Thread {

    private ConditionUtilsTest conditionUtilsTest;

    public ThreadB(ConditionUtilsTest conditionUtilsTest) {
        this.conditionUtilsTest = conditionUtilsTest;
    }

    @Override
    public void run() {
        conditionUtilsTest.waitB();
        System.out.println("============B获得了通知 RUN==========");
    }
}
