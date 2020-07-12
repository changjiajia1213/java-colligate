package com.changjiajia.redis.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 16:17
 * 描述: 幂等性校验使用Aop环绕通知实现
 **/
@Aspect
@Component
public class MdxAop {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 定义切面表达式
     */
    @Pointcut("execution(public * com.changjiajia.redis.web.*.*(..))")
    public void pointCut() {

    }

    /**
     * 配置环绕通知
     *
     * @return
     */
    @Around("pointCut() && @annotation(com.changjiajia.redis.zhujie.MdxAopApi)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 获取Servlet请求上下文的属性类
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        // 通过请求上下文的属性类来获取HttpServletRequest请求
        HttpServletRequest request = requestAttributes.getRequest();
        // 通过请求上下文的属性类获取HttpServletResponse请求
        HttpServletResponse response = requestAttributes.getResponse();

        String code = request.getParameter("code");

        if (checkCode(code)) {
            // 通过环绕通知
            return proceedingJoinPoint.proceed();
        } else {
            response.getOutputStream().print("{'msg':'aop error'}");
            return null;
        }
    }

    /**
     * 校测code的有效性
     *
     * @param code
     * @return
     */
    public boolean checkCode(String code) {
        // lua脚本
        String lua = "if redis.call('get',KEYS[1]) then return redis.call('del',KEYS[1]) else return 0 end";

        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript();
        // 设置要执行lua脚本
        defaultRedisScript.setScriptText(lua);
        // 返回的数据的类型
        defaultRedisScript.setResultType(Long.class);

        // 执行lua脚本
        Long execute = redisTemplate.execute(defaultRedisScript, Collections.singletonList(code));
        if (execute == 1) {
            return true;
        } else {
            return false;
        }
    }

}
