package com.changjiajia.java.设计模式.责任链模式.MyFilter;

import java.util.Vector;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-06-30 08:57
 * 描述:
 **/
public class MyFilterChainImpl implements MyFilterChain {

    private Vector<MyFilter> vector = new Vector<>();

    public void register(MyFilter myFilter) {
        vector.add(myFilter);
    }

    private int nextChain = 0;

    @Override
    public void chain() {
        if (nextChain < vector.size()) {
            MyFilter myFilter = vector.get(nextChain);
            nextChain++;
            myFilter.doFilter(this);
        }
        // 重新置为0 方便下一个线程执行还是0
        nextChain = 0;
    }
}
