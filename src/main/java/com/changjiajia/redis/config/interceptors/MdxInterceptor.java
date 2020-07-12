package com.changjiajia.redis.config.interceptors;

import com.changjiajia.redis.zhujie.MdxApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 15:43
 * 描述: 幂等性校验拦截器
 * 拦截请求 检测是否有表示Code
 **/
@Component
public class MdxInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 辅助Method，表示被拦截的方法实例
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取幂等性方法的注解
        MdxApi methodAnnotation = handlerMethod.getMethodAnnotation(MdxApi.class);
        // 获取传过来的code参数
        String code = request.getParameter("code");

        // 说明不需要做幂等性校验直接放行
        if (methodAnnotation == null) {
            return super.preHandle(request, response, handler);
        } else {
            // 先判断code有效性
            // code有效通过幂等性校验，反之说明属于重复请求直接返回
            if (checkCode(code)) {
                return super.preHandle(request, response, handler);
            } else {
                // 返回给前端信息
                response.getOutputStream().print("{'msg':'error'}");
                return false;
            }
        }
    }

    /**
     * 进行幂等性校验
     *
     * @param code
     * @return
     */
    public boolean checkCode(String code) {

        // lua脚本，直接传入两个命令，保证原子性
        String lua = "if redis.call('get',KEYS[1]) then return redis.call('del',KEYS[1]) else return 0 end";

        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        // 要执行的脚本
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
