package com.changjiajia.redis.config;

import com.changjiajia.redis.config.interceptors.MdxInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 16:09
 * 描述: 幂等性校验的拦截器注册类
 **/

@Configuration
public class MdxConfig extends WebMvcConfigurationSupport {

    @Autowired
    private MdxInterceptor mdxInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器并且拦截所有的请求
        registry.addInterceptor(mdxInterceptor).addPathPatterns("/**");
    }
}
