package com.jeethink.crm.service.wms;

import java.util.List;

import com.jeethink.crm.domain.WmsStock;

/**
 * 库存管理Service接口
 * 
 * @author jeethink
 * @date 2020-03-15
 */
public interface IWmsStockService 
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
     * 批量删除库存管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsStockByIds(String ids);

    /**
     * 删除库存管理信息
     * 
     * @param stockId 库存管理ID
     * @return 结果
     */
    public int deleteWmsStockById(Long stockId);
    
    /**
     * 查询库存管理
     * 
     * @param storageId 仓库id
     * @param productId 产品id
     * @return 库存管理
     */
    public WmsStock selectWmsStock(Long storageId,Long productId);
}
