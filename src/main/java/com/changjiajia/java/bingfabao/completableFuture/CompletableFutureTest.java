package com.changjiajia.java.bingfabao.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 21:17
 * 描述:
 **/
public class CompletableFutureTest {

    public static void main(String[] args) {
        // 让两个线程一前一后的运行并且后者依赖前者的运行结果
        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("执行的任务1");
            return "OK1";
        }).thenAccept(x -> {
            System.out.println("执行的任务2");
            System.out.println(x + "----->OK2");
        });*/

        CompletableFuture.supplyAsync(()->{
            return "OK1";
        });
    }

}
