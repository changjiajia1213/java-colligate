package com.changjiajia.entity;

import lombok.Data;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 21:58
 * 描述: 控制库存实体类
 **/
@Data
public class Stock {

    //数据的ID
    private long id;

    //库存
    private int stock;

    //商品ID
    private Long goodsId;

    //版本号
    private Long version;

    private String userId;

}
