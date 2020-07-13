package com.changjiajia.redis.redisLock;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 22:00
 * 描述: dao层
 **/
@Mapper
public interface LockDao {

    /**
     * 获取库存
     *
     * @param goodsId
     * @return
     */
    @Select("select id,goods_id as goodsId,stock from base_stock where goods_id=#{goodsId}")
    public Stock getStockByGoodsId(long goodsId);


    /**
     * 更新库存
     *
     * @param stock
     * @return
     */
    @Update("update base_stock set stock=#{stock} where goods_id=#{goodsId}")
    public int updateStock(Stock stock);

}
