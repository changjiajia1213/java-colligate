package com.changjiajia.java.包装类;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-01 19:58
 * 描述:
 **/
public class BaoZhuangLeiTest {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        // JDK1.5低版本包装类使用写法
        Integer integer = new Integer(15);

        // 自动装箱在代码编译阶段发生 ---> Integer num = Integer.valueOf(15);
        Integer num = 15;
        // 自动拆箱在代码编译阶段发生 ----> int num2 = num。intValue();
        int num2 = num;
        // 自动装箱 ---> Boolean flag = valueOf(true);
        Boolean flag = true;
        // 自动拆箱 ---> boolean flag2=flag.booleanValue();
        boolean flag2 = flag;

        // 面试:1：integer1.equals(integer2) 为什么返回true？
        // 因为底层重写了equals方法，比较的是value
        //   public boolean equals(Object obj) {
        //        if (obj instanceof Integer) {
        //            return value == ((Integer)obj).intValue();
        //        }
        //        return false;
        //    }
        Integer integer1 = new Integer(15);
        Integer integer2 = new Integer(15);
        System.out.println(integer1.equals(integer2));

        // 面试题2：integer3.equals(integer4) 为什么返回true？
        // 因为底层重写了equals方法，比较的是value（同上）
        Integer integer3 = 5;
        Integer integer4 = 5;
        System.out.println(integer3.equals(integer4));

        // 面试题3：integer5 == integer6 为什么返回true？
        //         integer7 == integer8 为什么返回false？
        // 如果值小于128会直接从IntegerCache缓存里去取，IntegerCache中维持了一个Integer的cache[],
        // 取出来的是同一个对象，所以integer5 == integer6返回true
        // 如果值大于等于128，那么cache失效，会直接new Integer(i),这就是产生了一个新的对象
        // 所以integer7 == integer8返回false
        //    public static Integer valueOf(int i) {
        //        if (i >= IntegerCache.low && i <= IntegerCache.high)
        //            return IntegerCache.cache[i + (-IntegerCache.low)];
        //        return new Integer(i);
        //    }
        Integer integer5 = 127;
        Integer integer6 = 127;
        Integer integer7 = 129;
        Integer integer8 = 129;
        System.out.println(integer5 == integer6);
        System.out.println(integer7 == integer8);


        /**
         *   ‘==’和‘equals’的区别
         *   默认情况下，Object.equals和‘==’是没有区别的，因为equals默认还是用‘==’去比较
         *
         *   public boolean equals(Object obj) {
         *       return (this == obj);
         *   }
         *
         *   如果重写了equals方法，两者就有区别了，比如Integer
         *   默认比较的是value数值的大小
         *
         *   public boolean equals(Object obj) {
         *          if (obj instanceof Integer) {
         *              return value == ((Integer)obj).intValue();
         *          }
         *          return false;
         *   }
         */

    }

}
