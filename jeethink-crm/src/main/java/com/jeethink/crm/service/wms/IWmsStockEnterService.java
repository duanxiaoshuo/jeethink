package com.jeethink.crm.service.wms;

import java.util.List;
import com.jeethink.crm.domain.WmsStockEnter;

/**
 * 入库管理Service接口
 * 
 * @author jeethink
 * @date 2020-03-16
 */
public interface IWmsStockEnterService 
{
    /**
     * 查询入库管理
     * 
     * @param enterId 入库管理ID
     * @return 入库管理
     */
    public WmsStockEnter selectWmsStockEnterById(Long enterId);

    /**
     * 查询入库管理列表
     * 
     * @param wmsStockEnter 入库管理
     * @return 入库管理集合
     */
    public List<WmsStockEnter> selectWmsStockEnterList(WmsStockEnter wmsStockEnter);

    /**
     * 新增入库管理
     * 
     * @param wmsStockEnter 入库管理
     * @return 结果
     */
    public int insertWmsStockEnter(WmsStockEnter wmsStockEnter);

    /**
     * 修改入库管理
     * 
     * @param wmsStockEnter 入库管理
     * @return 结果
     */
    public int updateWmsStockEnter(WmsStockEnter wmsStockEnter);

    /**
     * 批量删除入库管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsStockEnterByIds(String ids);

    /**
     * 删除入库管理信息
     * 
     * @param enterId 入库管理ID
     * @return 结果
     */
    public int deleteWmsStockEnterById(Long enterId);
    
    /**
     * 审核入库 通过
     * 
     * @param wmsStockEnter 入库管理
     * @return 结果
     */
    public int auditOkWmsStockEnter(WmsStockEnter wmsStockEnter);
    
    /**
     * 审核入库 拒绝
     * 
     * @param wmsStockEnter 入库管理
     * @return 结果
     */
    public int auditNoWmsStockEnter(WmsStockEnter wmsStockEnter);
}
