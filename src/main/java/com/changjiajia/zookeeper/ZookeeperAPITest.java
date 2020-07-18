package com.changjiajia.zookeeper;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-16 17:05
 * 描述: zookeeper默认端口：2181
 * zookeeper基础api
 **/
public class ZookeeperAPITest {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // sessionTimeout是毫秒
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 50000, new MyWatcher());

        // 创建一个临时节点
        //zooKeeper.create("/TTT","test create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        // 创建一个临时时序节点
        //zooKeeper.create("/TTT","test create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        // 创建一个持久节点
        //zooKeeper.create("/TTT","test create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 创建一个持久时序节点
        //zooKeeper.create("/TTT","test create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        //Thread.sleep(20000);

        // 可以注册事件的地方
        //zooKeeper.getChildren();
        //zooKeeper.getData();
        //zooKeeper.exists();

        // 触发事件的一些操作
        //zooKeeper.delete();
        //zooKeeper.create();
        //zooKeeper.setData();

        // 获取子节点，并注册事件
        //zooKeeper.getChildren("/YYY",true);
        // 返回创建节点的path
        //String s = zooKeeper.create("/YYY/Y1", "测试数据".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //System.out.println("====返回创建节点的path====>" + s);

        // 获取数据
        //Stat stat = new Stat();
        //byte[] data = zooKeeper.getData("/YYY/Y1", new MyWatcher2(), stat);
        //System.out.println("获取节点数据（修改前）==>" + new String(data));
        // 更新数据触发 数据更新的事件
        //Stat stat2 = zooKeeper.setData("/YYY/Y1", "更新数据7886".getBytes(), stat.getVersion());
        //byte[] data2 = zooKeeper.getData("/YYY/Y1", new MyWatcher2(), null);
        //System.out.println("获取节点数据（修改后）==>" + new String(data2));
        //System.out.println("事务id：" + stat.getMzxid() + "--->" + stat2.getMzxid());
        //System.out.println("版本号：" + stat.getVersion() + "--->" + stat2.getVersion());


        // 判断节点是否存在
        //Stat exists = zooKeeper.exists("/YYY/Y1", new MyWatcher2());
        //System.out.println("是否存在："+JSON.toJSONString(exists));
        // 存在则删除
        //zooKeeper.delete("/YYY/Y1",-1);


        // 判断节点是否存在
        Stat exists = zooKeeper.exists("/YYY/Y1", true);
        System.out.println("是否存在：" + JSON.toJSONString(exists));
        // 不存在则创建并返回创建节点的path
        String s = zooKeeper.create("/YYY/Y1", "测试数据".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 当主节点同步数据到子节点，同步节点为n/2+1则返回给主节点说成功，继续对外提供服务。
        // 如果这时候有一个客户端在未同步完成的节点读数据，我们可以调用这个方法到主节点进行同步，读取最新数据。
        //zooKeeper.sync();

        // 关闭zookeeper
        //zooKeeper.close();

    }

}
