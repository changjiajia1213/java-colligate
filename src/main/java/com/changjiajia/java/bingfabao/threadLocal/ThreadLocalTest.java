package com.changjiajia.java.bingfabao.threadLocal;

import com.changjiajia.java.bingfabao.condition.TTT.ThreadTestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-06-29 20:37
 * 描述:
 **/
@RestController
public class ThreadLocalTest {

    private ThreadLocalUtils threadLocalUtils = new ThreadLocalUtils();

    @RequestMapping("test1")
    public void test1() {
        YYYY yyyy = threadLocalUtils.getYYYY();
        System.out.println(Thread.currentThread().getName() + "========改变之前========" + yyyy.getUsername());
        yyyy.setUsername("uuuuuuuuuuuu");
        YYYY yyyy2 = threadLocalUtils.getYYYY();
        System.out.println(Thread.currentThread().getName() + "========改变之后========" + yyyy2.getUsername());

    }

    @RequestMapping("test2")
    public void test2() {
        YYYY yyyy = threadLocalUtils.getYYYY();
        System.out.println(Thread.currentThread().getName() + "========改变之前========" + yyyy.getUsername());
    }
}
