package com.changjiajia.zookeeper.zkLock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-17 19:39
 * 描述: zookeeper实现分布式锁优化
 **/
@Configuration
public class ZkConfig {

    @Bean
    public RetryPolicy getRetryPolicy() {
        // 重试策略：每隔1秒重试一次，重试3次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return retryPolicy;
    }

    @Bean
    public CuratorFramework getCuratorFramework(RetryPolicy retryPolicy) {
        // 创建客户端
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        // 开启客户端
        curatorFramework.start();
        return curatorFramework;
    }

}
