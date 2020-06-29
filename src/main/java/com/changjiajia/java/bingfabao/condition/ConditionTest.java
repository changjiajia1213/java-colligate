package com.changjiajia.java.bingfabao.condition;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 19:29
 * 描述: Condition并发包
 **/
public class ConditionTest {

    public static void main(String[] args) throws InterruptedException {
        ConditionUtilsTest conditionUtilsTest = new ConditionUtilsTest();

        ThreadA threadA = new ThreadA(conditionUtilsTest);
        threadA.start();

        ThreadB threadB = new ThreadB(conditionUtilsTest);
        threadB.start();

        Thread.sleep(2000);

        // 通知A运行
        conditionUtilsTest.singleA();
        conditionUtilsTest.singleB();
    }

}
