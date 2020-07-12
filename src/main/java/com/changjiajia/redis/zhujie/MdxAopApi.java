package com.changjiajia.redis.zhujie;

import java.lang.annotation.*;

/**
 * @author jiajia
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 16:39
 * 描述: AOP 幂等性校验控制注解
 **/

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MdxAopApi {

}
