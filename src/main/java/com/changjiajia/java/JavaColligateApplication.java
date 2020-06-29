package com.changjiajia.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @program: java-colligate
 * <p>
 * 作者: ChangJiaJia
 * 日期: 2020-06-24 17:40
 * 描述:
 **/

@SpringBootApplication
@EnableAsync // 允许异步访问 从响应式变成反应式 增加程序的吞吐量
public class JavaColligateApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaColligateApplication.class);
    }

}
