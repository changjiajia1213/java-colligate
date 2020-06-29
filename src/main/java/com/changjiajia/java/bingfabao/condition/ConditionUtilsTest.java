package com.changjiajia.java.bingfabao.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 19:30
 * 描述:
 **/
public class ConditionUtilsTest {

    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();

    private Condition conditionB = lock.newCondition();

    public void waitA() {
        // conditionA.await(); // 相当于object wait()
        // conditionA.signal(); // 相当于object notify
        // 获取锁
        lock.lock();
        try {
            System.out.println("==========A wait=====");
            conditionA.await();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void waitB() {
        // 获取锁
        lock.lock();
        try {
            System.out.println("==========B wait=====");
            conditionB.await();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void singleA() {
        // conditionA.await(); // 相当于object wait()
        // conditionA.signal(); // 相当于object notify
        // 获取锁
        lock.lock();
        try {
            System.out.println("==========A single=====");
            conditionA.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void singleB() {
        // 获取锁
        lock.lock();
        try {
            System.out.println("==========B single=====");
            conditionB.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
