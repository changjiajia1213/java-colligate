package com.changjiajia.java.bingfabao.condition;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 19:39
 * 描述:
 **/
public class ThreadA extends Thread {

    private ConditionUtilsTest conditionUtilsTest;

    public ThreadA(ConditionUtilsTest conditionUtilsTest) {
        this.conditionUtilsTest = conditionUtilsTest;
    }

    @Override
    public void run() {
        conditionUtilsTest.waitA();
        System.out.println("============A获得了通知 RUN==========");
    }
}
