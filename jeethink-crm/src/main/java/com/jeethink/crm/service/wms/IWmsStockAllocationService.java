package com.jeethink.crm.service.wms;

import java.util.List;
import com.jeethink.crm.domain.WmsStockAllocation;

/**
 * 调拨单Service接口
 * 
 * @author jeethink
 * @date 2020-08-12
 */
public interface IWmsStockAllocationService 
{
    /**
     * 查询调拨单
     * 
     * @param allocationId 调拨单ID
     * @return 调拨单
     */
    public WmsStockAllocation selectWmsStockAllocationById(Long allocationId);

    /**
     * 查询调拨单列表
     * 
     * @param wmsStockAllocation 调拨单
     * @return 调拨单集合
     */
    public List<WmsStockAllocation> selectWmsStockAllocationList(WmsStockAllocation wmsStockAllocation);

    /**
     * 新增调拨单
     * 
     * @param wmsStockAllocation 调拨单
     * @return 结果
     */
    public int insertWmsStockAllocation(WmsStockAllocation wmsStockAllocation);

    /**
     * 修改调拨单
     * 
     * @param wmsStockAllocation 调拨单
     * @return 结果
     */
    public int updateWmsStockAllocation(WmsStockAllocation wmsStockAllocation);

    /**
     * 批量删除调拨单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsStockAllocationByIds(String ids);

    /**
     * 删除调拨单信息
     * 
     * @param allocationId 调拨单ID
     * @return 结果
     */
    public int deleteWmsStockAllocationById(Long allocationId);
   
    /**
     * 审核调拨单-通过
     * 
     * @param wmsStockAllocation 调拨单
     * @return 结果
     */
    public int auditOkWmsStockAllocation(WmsStockAllocation wmsStockAllocation);
   
    /**
     * 审核调拨单  拒绝
     * 
     * @param wmsStockAllocation 调拨单
     * @return 结果
     */
    public int auditNoWmsStockAllocation(WmsStockAllocation wmsStockAllocation);
}
