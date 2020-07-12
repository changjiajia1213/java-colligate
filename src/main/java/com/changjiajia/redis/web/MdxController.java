package com.changjiajia.redis.web;

import com.changjiajia.redis.utils.FreemarkerUtils;
import com.changjiajia.redis.utils.UID;
import com.changjiajia.redis.zhujie.MdxApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 15:14
 * 描述: 幂等性校验测试使用拦截器 案例
 **/

@RestController
public class MdxController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 提交form表单的接口
     *
     * @return
     */
    @RequestMapping("addObject")
    @MdxApi
    public String addObject(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("===============" + request.getParameter("code"));
        return request.getParameter("code");
    }

    /**
     * 通过该方法到达我们的测试页面
     */
    @RequestMapping("toIndex")
    public void toIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        HashMap<String, Object> hashMap = new HashMap<>();
        FreemarkerUtils.getStaticHtml(MdxController.class
                , "/template/", "test.html", hashMap, writer);
    }

    /**
     * 生成code的接口
     *
     * @return
     */
    @RequestMapping("/getCode")
    public String getCode() {
        // 生成code
        String uuid16 = UID.getUUID16();
        redisTemplate.opsForValue().set(uuid16, "", 1, TimeUnit.HOURS);

        return uuid16;
    }


}
