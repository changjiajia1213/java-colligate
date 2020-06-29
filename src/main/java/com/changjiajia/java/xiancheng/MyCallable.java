package com.changjiajia.java.xiancheng;

import java.util.concurrent.Callable;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 16:38
 * 描述: 实现线程返回值
 **/
public class MyCallable implements Callable {


    @Override
    public Object call() throws Exception {

        System.out.println("=========执行线程=======");
        return "OK";
    }

}
