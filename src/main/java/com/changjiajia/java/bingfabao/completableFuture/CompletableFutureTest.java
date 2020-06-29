package com.changjiajia.java.bingfabao.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 21:17
 * 描述:
 **/
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 让两个线程一前一后的运行并且后者依赖前者的运行结果
        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("执行的任务1");
            return "OK1";
        }).thenAccept(x -> {
            System.out.println("执行的任务2");
            System.out.println(x + "----->OK2");
        });*/


        // 多个任务的合并
        /*CompletableFuture.supplyAsync(()->{
            return "OK1";
        }).thenCombineAsync(CompletableFuture.supplyAsync(()->{
            return "---OK2---";
        }),(x1,x2)->{
            return x1+x2;
        }).thenCombineAsync(CompletableFuture.supplyAsync(()->{
            return "OK3";
        }),(x2,x3)->{
            return x2+x3;
        }).thenAccept((x)->{
            System.out.println(x);
        });*/



        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "OK1";
        });

        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "OK2";
        });

        CompletableFuture<String> stringCompletableFuture3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "OK3";
        });

        // 返回一个运行快的线程结果
        /*CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(stringCompletableFuture, stringCompletableFuture2, stringCompletableFuture3);
        // 调用get方法会阻塞到这里，运行完才会继续运行下面的代码
        System.out.println(objectCompletableFuture.get());

        System.out.println("===========结束=========");*/

        // 当所有的任务全部完成后使用的方法
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(stringCompletableFuture, stringCompletableFuture2, stringCompletableFuture3);
        voidCompletableFuture.get();

        System.out.println("===" + stringCompletableFuture.get() + "===" + stringCompletableFuture2.get() + "===" + stringCompletableFuture3.get());

    }

}
