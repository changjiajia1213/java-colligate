package com.changjiajia.redis.zhujie;

import java.lang.annotation.*;

/**
 * @author jiajia
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 15:37
 * 描述: 自定义幂等性校验注解
 **/

@Target(ElementType.METHOD) // 目标对象
@Inherited // 表示可以被继承，不加这个注解表示不可以被继承
@Retention(RetentionPolicy.RUNTIME) // 运行范围(运行阶段)
public @interface MdxApi {



}
