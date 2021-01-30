package com.jeethink.crm.service.wms;

import java.util.List;

import com.jeethink.crm.domain.WmsStockOut;

/**
 * 出库管理Service接口
 * 
 * @author jeethink
 * @date 2020-03-16
 */
public interface IWmsStockOutService 
{
    /**
     * 查询出库管理
     * 
     * @param outId 出库管理ID
     * @return 出库管理
     */
    public WmsStockOut selectWmsStockOutById(Long outId);

    /**
     * 查询出库管理列表
     * 
     * @param wmsStockOut 出库管理
     * @return 出库管理集合
     */
    public List<WmsStockOut> selectWmsStockOutList(WmsStockOut wmsStockOut);

    /**
     * 新增出库管理
     * 
     * @param wmsStockOut 出库管理
     * @return 结果
     */
    public int insertWmsStockOut(WmsStockOut wmsStockOut);

    /**
     * 修改出库管理
     * 
     * @param wmsStockOut 出库管理
     * @return 结果
     */
    public int updateWmsStockOut(WmsStockOut wmsStockOut);

    /**
     * 批量删除出库管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsStockOutByIds(String ids);

    /**
     * 删除出库管理信息
     * 
     * @param outId 出库管理ID
     * @return 结果
     */
    public int deleteWmsStockOutById(Long outId);
    
    /**
     * 审核出库  通过
     * 
     * @param wmsStockOut 出库管理
     * @return 结果
     */
    public int auditOkWmsStockOut(WmsStockOut wmsStockOut);
    
    /**
     * 审核出库  拒绝
     * 
     * @param wmsStockOut 出库管理
     * @return 结果
     */
    public int auditNoWmsStockOut(WmsStockOut wmsStockOut);
}
