package com.changjiajia.java.bingfabao.threadLocal;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-06-29 20:40
 * 描述:
 **/
public class ThreadLocalUtils {

    private ThreadLocal<YYYY> threadLocal = new ThreadLocal<>();

    public YYYY getYYYY() {
        if (threadLocal.get() != null) {
            return threadLocal.get();
        } else {
            YYYY yyyy = new YYYY("hhh");
            threadLocal.set(yyyy);

            return threadLocal.get();
        }
    }

}
