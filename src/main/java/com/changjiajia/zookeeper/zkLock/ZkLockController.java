package com.changjiajia.zookeeper.zkLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-16 21:43
 * 描述:
 **/
@RestController
public class ZkLockController {

    @Autowired
    private ZkLockService zkLockService;

    @RequestMapping("/zkLock1")
    public void test() throws Exception {
        zkLockService.lockStock(100L);
    }

}
