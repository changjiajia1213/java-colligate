package com.changjiajia.leguan;

import com.changjiajia.entity.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @program: java-colligate
 * 作者: ChangJiaJia
 * 日期: 2020-07-12 22:00
 * 描述: 使用乐观锁
 **/
@Mapper
public interface OptimisticLockDao {

    /**
     * 获取库存
     *
     * @param goodsId
     * @return
     */
    @Select("select id,goods_id as goodsId,stock,version from base_stock where goods_id=#{goodsId}")
    public Stock getStockByGoodsId(long goodsId);


    /**
     * 更新库存
     *
     * @param stock
     * @return
     */
    @Update("update base_stock set stock=#{stock},version=version+1 where goods_id=#{goodsId} and version=#{version}")
    public int updateStockOptimistic(Stock stock);

}
