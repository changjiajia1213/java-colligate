package com.changjiajia.java.设计模式.责任链模式.MyFilter;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-06-30 08:56
 * 描述:
 **/
public class MyFilterImpl implements MyFilter {

    private String name;

    public MyFilterImpl(String name) {
        this.name = name;
    }

    @Override
    public void doFilter(MyFilterChain myFilterChain) {
        System.out.println("=======处理自己的业务========" + name);

        myFilterChain.chain();
    }
}
