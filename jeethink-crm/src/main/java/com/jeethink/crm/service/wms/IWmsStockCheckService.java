package com.jeethink.crm.service.wms;

import java.util.List;
import com.jeethink.crm.domain.WmsStockCheck;

/**
 * 库存盘点Service接口
 * 
 * @author jeethink
 * @date 2020-08-14
 */
public interface IWmsStockCheckService 
{
    /**
     * 查询库存盘点
     * 
     * @param checkId 库存盘点ID
     * @return 库存盘点
     */
    public WmsStockCheck selectWmsStockCheckById(Long checkId);

    /**
     * 查询库存盘点列表
     * 
     * @param wmsStockCheck 库存盘点
     * @return 库存盘点集合
     */
    public List<WmsStockCheck> selectWmsStockCheckList(WmsStockCheck wmsStockCheck);

    /**
     * 新增库存盘点
     * 
     * @param wmsStockCheck 库存盘点
     * @return 结果
     */
    public int insertWmsStockCheck(WmsStockCheck wmsStockCheck);

    /**
     * 修改库存盘点
     * 
     * @param wmsStockCheck 库存盘点
     * @return 结果
     */
    public int updateWmsStockCheck(WmsStockCheck wmsStockCheck);

    /**
     * 批量删除库存盘点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsStockCheckByIds(String ids);

    /**
     * 删除库存盘点信息
     * 
     * @param checkId 库存盘点ID
     * @return 结果
     */
    public int deleteWmsStockCheckById(Long checkId);    
    
    /**
     * 审核库存盘点 -通过
     * 
     * @param wmsStockCheck 库存盘点
     * @return 结果
     */
    public int auditOkWmsStockCheck(WmsStockCheck wmsStockCheck);
    
    /**
     * 审核库存盘点-拒绝
     * 
     * @param wmsStockCheck 库存盘点
     * @return 结果
     */
    public int auditNoWmsStockCheck(WmsStockCheck wmsStockCheck);
}
