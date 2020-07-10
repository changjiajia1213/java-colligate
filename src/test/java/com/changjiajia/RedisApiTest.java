package com.changjiajia;

import com.changjiajia.entity.TestUU;
import com.changjiajia.redis.utils.ProtoStuffUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-02 09:13
 * 描述: Redis测试
 **/

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisApiTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 对String类型的操作
     */
    @Test
    public void test01() {

        redisTemplate.opsForValue().set("str1", "123456");

        // 如果不存在的时候添加对应的key
        redisTemplate.opsForValue().setIfAbsent("str1", "888888888888");
        // 如果key存在则添加，相当于更新
        redisTemplate.opsForValue().setIfPresent("str1", "9999999999999999");

        // 设置过期时间
        // 添加数据时直接设置过期时间
        redisTemplate.opsForValue().set("str2", "555555", 1000, TimeUnit.SECONDS);
        // 对已添加完数据设置过期时间
        redisTemplate.expire("", 1000, TimeUnit.SECONDS);
    }

    /**
     * list
     */
    @Test
    public void test02() {
        // 存入数据
        //redisTemplate.opsForList().leftPush("list1", "5555555");
        //redisTemplate.opsForList().leftPush("list1", "6666666");

        // 从第一个集合右侧删除一个插入到第二个集合的左侧，并返回删除数据
        //String s = redisTemplate.opsForList().rightPopAndLeftPush("list1", "list2");

        redisTemplate.opsForList().trim("list1", 0, 5);


    }


    /**
     * set集合
     */
    @Test
    public void test03() {
        // 求取redis中存在的两个集合中的交集
        Set<String> intersect = redisTemplate.opsForSet().intersect("set1", "set2");
        // 求取redis中存在的两个集合中的差集
        Set<String> difference = redisTemplate.opsForSet().difference("set1", "set2");
        // 求取redis中存在的集合和给定集合的差集 注意类型要一致
        Set<String> set3 = redisTemplate.opsForSet().difference("set3", new HashSet<String>());
        // 求取redis中存在的两个集合中的并集
        Set<String> union = redisTemplate.opsForSet().union("set1", "set2");
        // 随机获取集合中的一个成员
        String randomMember = redisTemplate.opsForSet().randomMember("set1");
    }

    /**
     * hash
     */
    @Test
    public void test04() {

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("key1", "1111111");
        hashMap.put("key2", "22222222");
        hashMap.put("key3", "333333333");
        hashMap.put("key4", "44444444");
        // 存入一个map
        //redisTemplate.opsForHash().putAll("hash1",hashMap);

        // 获取集合中的一个值
        Object key = redisTemplate.opsForHash().get("hash1", "key1");
        System.out.println(key);

        // 判断某一个key是否存在
        Boolean hash1 = redisTemplate.opsForHash().hasKey("hash1", "key1");
        System.out.println(hash1);

        // increment操作hash中value（数字类型Integer），对其进行增加操作 可以做计数器去用
        redisTemplate.opsForHash().increment("hash1", "key1", 111);


    }

    /**
     * zset
     */
    @Test
    public void test05() {
        // 添加操作 返回一个布尔类型
        //redisTemplate.opsForZSet().add("zset1","1",95);
        // 对集合中key值对应的value值进行添加操作 增加分值
        //redisTemplate.opsForZSet().incrementScore("zset1", "1", 2);

        for (int i = 0; i < 10; i++) {
            //redisTemplate.opsForZSet().add("zset1", "" + (1 + i), 90 + i);
        }

        // 分数段统计 根据分值范围和行的范围取数据
        Set<String> zset1 = redisTemplate.opsForZSet().rangeByScore("zset1", 90, 95, 0, 10);
        System.out.println(zset1);

        // 集合中包含的元素个数
        Long zCard = redisTemplate.opsForZSet().zCard("zset1");
        System.out.println(zCard);

        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = redisTemplate.opsForZSet().rangeWithScores("zset1", 2, 3);
        rangeWithScores.forEach(ttt -> {
            System.out.println(ttt.getScore() + "--->" + ttt.getValue());
        });

    }

    /**
     * 事务
     */
    @Test
    public void test06() {
        // 设置redis支持数据库的事务
        redisTemplate.setEnableTransactionSupport(true);

        // 打开事务
        redisTemplate.multi();

        // 写对redis的操作
        redisTemplate.opsForValue().set("lisi1", "111111111");
        //redisTemplate.opsForList().leftPush("lisi1","44444");
        redisTemplate.opsForValue().set("lisi2", "222222222");

        // 提交事务返回执行的结果
        List<Object> exec = redisTemplate.exec();

        System.out.println(exec);
    }

    /**
     * 管道批量添加操作
     */
    @Test
    public void test07() {

        // 获取开始执行时间
        long start = System.currentTimeMillis();

        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                // 打开管道
                redisConnection.openPipeline();

                // 单个添加
                /*for (int i = 0; i < 500000; i++) {
                    redisConnection.set(("str" + i).getBytes(), ("" + i).getBytes());
                }*/

                // 优化->使用map添加
                Map<byte[], byte[]> map = new HashMap();
                //执行我们的批量操作
                for (int i = 0; i < 500000; i++) {
                    map.put(("str" + i).getBytes(), ("" + i).getBytes());
                }
                redisConnection.mSet(map);

                // 关闭管道
                redisConnection.closePipeline();
                return null;
            }
        });

        // 获取结束执行时间
        long end = System.currentTimeMillis();

        System.out.println("管道批量添加50万数据所耗时长（秒）：" + (end - start) / 1000.0);
        // 管道批量添加50万数据所耗时长（秒）：8.837  -->单个添加
        // 管道批量添加50万数据所耗时长（秒）：2.689  -->优化使用map添加
    }

    /**
     * 非管道批量添加数据操作所需时间
     */
    @Test
    public void test08() {

        // 获取开始执行时间
        long start = System.currentTimeMillis();

        // 单个添加
        /*for (int i = 0; i < 500000; i++) {
            redisTemplate.opsForValue().set("str" + i, "" + i);
        }*/

        // 优化map添加
        Map<String, String> map = new HashMap();
        for (int i = 0; i < 500000; i++) {
            map.put("str" + i, "" + i);
        }
        // multiSet方法底层仍然使用的是管道 所以执行速度快
        redisTemplate.opsForValue().multiSet(map);

        // 获取结束执行时间
        long end = System.currentTimeMillis();

        System.out.println("非管道批量添加50万数据所耗时长（秒）：" + (end - start) / 1000.0);
        // 非管道批量添加50万数据所耗时长（秒）：46.709  -->单个添加
        // 非管道批量添加50万数据所耗时长（秒）：3.046   -->优化使用map添加
    }

    /**
     * 默认使用JDK序列化方式
     * <p>
     * key->String value->String
     * 测试使用String数据结构存储10万条数据所占内存
     * 一个key对应一个value
     */
    @Test
    public void test09() {

        long start = System.currentTimeMillis();
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= 100000; i++) {
            TestUU testUU = new TestUU();
            testUU.setUserName("username" + i);
            testUU.setPassword("password" + i);
            map.put("key" + i, testUU.toString());
        }
        long end = System.currentTimeMillis();
        redisTemplate.opsForValue().multiSet(map);

        System.out.println("===>" + (end - start) / 1000.0);
        // 测试占用内存---->5838KB
    }

    /**
     * 默认使用JDK序列化方式
     * <p>
     * 控制键数量降低内寸使用 使用hash数据结构存储
     */
    @Test
    public void test10() {
        long start = System.currentTimeMillis();
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= 100000; i++) {
            TestUU testUU = new TestUU();
            testUU.setUserName("userName" + i); // 字母的大小写也会影响内存占用
            testUU.setPassword("password" + i);
            map.put("key" + i, testUU.toString());
            // 每10万条存储一次
            if (i % 10000 == 0) {
                redisTemplate.opsForHash().putAll("KEY" + i, map);
                map.clear();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("===>" + (end - start) / 1000.0);
        // 测试占用内存----> username-->5740KB
        // 测试占用内存----> userName-->5350KB
    }


    /**
     * 实验目标：测试三中序列化方式哪一种存储相同数据所占内存最小
     * 实验结论：使用 Jackson2JsonRedisSerializer 序列化方式存储相同数据是三种序列化方式中所占内存最小
     * 注：所用时长会根据每次运行而改变
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate1;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate2;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate3;

    /**
     * 使用 Jackson2JsonRedisSerializer 序列化方式测试存储10万条所用内存
     */
    @Test
    public void test11() {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i <= 100000; i++) {
            TestUU testUU = new TestUU();
            testUU.setUserName("username" + i);
            testUU.setPassword("password" + i);
            map.put("key" + i, testUU);
        }
        long end = System.currentTimeMillis();
        redisTemplate1.opsForValue().multiSet(map);

        System.out.println("使用 Jackson2JsonRedisSerializer 序列化方式测试存储10万条所用时长：" + (end - start) / 1000.0 + "s");
        // 使用 Jackson2JsonRedisSerializer 序列化方式测试存储10万条所用时长：0.056s
        // 测试占用内存---->5643KB
    }

    /**
     * 使用 GenericJackson2JsonRedisSerializer 序列化方式测试存储10万条所用内存
     */
    @Test
    public void test12() {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i <= 100000; i++) {
            TestUU testUU = new TestUU();
            testUU.setUserName("username" + i);
            testUU.setPassword("password" + i);
            map.put("key" + i, testUU);
        }
        long end = System.currentTimeMillis();
        redisTemplate2.opsForValue().multiSet(map);

        System.out.println("使用 GenericJackson2JsonRedisSerializer 序列化方式测试存储10万条所用时长：" + (end - start) / 1000.0 + "s");
        // 使用 GenericJackson2JsonRedisSerializer 序列化方式测试存储10万条所用时长：0.076s
        // 测试占用内存---->9842KB
    }

    /**
     * 使用 JDK 序列化方式测试存储10万条所用内存
     */
    @Test
    public void test13() {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i <= 100000; i++) {
            TestUU testUU = new TestUU();
            testUU.setUserName("username" + i);
            testUU.setPassword("password" + i);
            map.put("key" + i, testUU);
        }
        long end = System.currentTimeMillis();
        redisTemplate3.opsForValue().multiSet(map);

        System.out.println("使用 JDK 序列化方式测试存储10万条所用时长：" + (end - start) / 1000.0 + "s");
        // 使用 JDK 序列化方式测试存储10万条所用时长：0.054s
        // 测试占用内存---->14228KB
    }

    /**
     * redis配合高效序列化工具protostuff(谷歌)
     *
     * 实验目标：测试 高效序列化工具protostuff 与上述序列化方式存储相同数据所占内存哪种更小
     * 实验结论：通过结果我们可以发现使用 protostuff工具类 存储数据使用内存是最小的，
     *          但是由于使用的是自定义序列化方式，改变了序列化的算法，会降低存储速率，导致存储时长变长
     *          简单来说，存储占用内存最小，存储时间最长
     */
    @Test
    public void test14() {
        long start = System.currentTimeMillis();
        //List<TestUU> list = new ArrayList<>();

        // 采用回调方式
        redisTemplate.execute(new RedisCallback<Void>() {
            @Override
            public Void doInRedis(RedisConnection redisConnection) throws DataAccessException {
                // 因为改变了序列化方式 储存的是字节方便进行压缩
                Map<byte[], byte[]> map = new HashMap<>();
                for (int i = 1; i <= 100000; i++) {
                    TestUU testUU = new TestUU();
                    testUU.setUserName("username" + "i");
                    testUU.setPassword("password" + i);
                    // 将对象转为字节数组
                    map.put(("key0" + i).getBytes(), ProtoStuffUtil.serialize(testUU));
                }
                redisConnection.mSet(map);
                // 方便垃圾回收
                map = null;
                return null;
            }
        });

        // System.out.println(list.size());
        long end = System.currentTimeMillis();
        System.out.println("使用 高效序列化工具protostuff 方式测试存储10万条所用时长：" + (end - start) / 1000.0 + "s");
        // 使用 高效序列化工具protostuff 方式测试存储10万条所用时长：8.674s
        // 测试占用内存---->3690KB
    }

}
