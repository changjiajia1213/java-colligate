package com.changjiajia.java.设计模式.责任链模式.MyFilter;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-06-30 09:09
 * 描述:
 **/
public class FilterChainTest {

    public static void main(String[] args) {
        MyFilterImpl myFilter = new MyFilterImpl("1");
        MyFilterImpl2 myFilter2 = new MyFilterImpl2("2");
        MyFilterImpl3 myFilter3 = new MyFilterImpl3("3");



    }

}
