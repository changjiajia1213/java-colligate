package com.changjiajia.java.bingfabao.countdownLunch;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 17:34
 * 描述: 开发一个网页模拟老师上课
 **/
@RestController
public class TestWeb {

    private static CountDownLatch countDownLatch = new CountDownLatch(4);

    @RequestMapping("student/{param}")
    public String student(@PathVariable("param") String param) {
        System.out.println(param + "说：我明白了！");
        countDownLatch.countDown();
        return param + "说：我明白了！";
    }


    @RequestMapping("teacher")
    public String teacher() throws InterruptedException {
        countDownLatch.await();
        System.out.println("那我们开始下一个知识点讲解！");

        // 优化 -> 老师平台刷新后仍可以继续使用，重新使用
        countDownLatch=new CountDownLatch(4);

        return "那我们开始下一个知识点讲解！";
    }
}
