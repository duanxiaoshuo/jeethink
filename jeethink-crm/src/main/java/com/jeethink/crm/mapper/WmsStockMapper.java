package com.jeethink.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeethink.crm.domain.WmsStock;

/**
 * 库存管理Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-15
 */
public interface WmsStockMapper 
{
    /**
     * 查询库存管理
     * 
     * @param stockId 库存管理ID
     * @return 库存管理
     */
    public WmsStock selectWmsStockById(Long stockId);

    /**
     * 查询库存管理列表
     * 
     * @param wmsStock 库存管理
     * @return 库存管理集合
     */
    public List<WmsStock> selectWmsStockList(WmsStock wmsStock);

    /**
     * 新增库存管理
     * 
     * @param wmsStock 库存管理
     * @return 结果
     */
    public int insertWmsStock(WmsStock wmsStock);

    /**
     * 修改库存管理
     * 
     * @param wmsStock 库存管理
     * @return 结果
     */
    public int updateWmsStock(WmsStock wmsStock);

    /**
     * 删除库存管理
     * 
     * @param stockId 库存管理ID
     * @return 结果
     */
    public int deleteWmsStockById(Long stockId);

    /**
     * 批量删除库存管理
     * 
     * @param stockIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsStockByIds(String[] stockIds);
    
    /**
     * 查询库存管理
     * 
     * @param storageId 仓库id
     * @param productId 产品id
     * @return 库存管理
     */
    public WmsStock selectWmsStock(@Param("storageId") Long storageId,@Param("productId") Long productId);
}
