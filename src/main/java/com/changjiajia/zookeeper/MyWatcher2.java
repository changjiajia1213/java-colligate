package com.changjiajia.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-16 21:30
 * 描述:
 **/
public class MyWatcher2 implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("=========MyWatcher2========");
        if (watchedEvent.getType().getIntValue() == Event.EventType.NodeDeleted.getIntValue()) {
            // 节点删除
            System.out.println("===2====节点删除事件======");
        } else if (watchedEvent.getType().getIntValue() == Event.EventType.NodeChildrenChanged.getIntValue()) {
            // 孩子节点发生变更
            System.out.println("====2======孩子节点发生变更=========");
        } else if (watchedEvent.getType().getIntValue() == Event.EventType.NodeDataChanged.getIntValue()) {
            // 节点数据发生变更
            System.out.println("=====2=======节点数据发生变更===========");
        } else if (watchedEvent.getType().getIntValue() == Event.EventType.NodeCreated.getIntValue()) {
            // 创建节点
            System.out.println("=====2=======创建节点事件==========");
        } else if (watchedEvent.getType().getIntValue() == Event.EventType.None.getIntValue()) {
            // 节点事件
            System.out.println("===2===节点事件=======");
        }
    }
}
