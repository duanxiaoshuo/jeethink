package com.jeethink.crm.service.wms;

import java.util.List;
import com.jeethink.crm.domain.WmsStockHistory;

/**
 * 库存记录Service接口
 * 
 * @author jeethink
 * @date 2020-08-13
 */
public interface IWmsStockHistoryService 
{
    /**
     * 查询库存记录
     * 
     * @param historyId 库存记录ID
     * @return 库存记录
     */
    public WmsStockHistory selectWmsStockHistoryById(Long historyId);

    /**
     * 查询库存记录列表
     * 
     * @param wmsStockHistory 库存记录
     * @return 库存记录集合
     */
    public List<WmsStockHistory> selectWmsStockHistoryList(WmsStockHistory wmsStockHistory);

    /**
     * 新增库存记录
     * 
     * @param wmsStockHistory 库存记录
     * @return 结果
     */
    public int insertWmsStockHistory(WmsStockHistory wmsStockHistory);

    /**
     * 修改库存记录
     * 
     * @param wmsStockHistory 库存记录
     * @return 结果
     */
    public int updateWmsStockHistory(WmsStockHistory wmsStockHistory);

    /**
     * 批量删除库存记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsStockHistoryByIds(String ids);

    /**
     * 删除库存记录信息
     * 
     * @param historyId 库存记录ID
     * @return 结果
     */
    public int deleteWmsStockHistoryById(Long historyId);
}
