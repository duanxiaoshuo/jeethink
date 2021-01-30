package com.jeethink.crm.service.wms.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.WmsStockHistoryMapper;
import com.jeethink.crm.domain.WmsStockHistory;
import com.jeethink.crm.service.wms.IWmsStockHistoryService;
import com.jeethink.common.core.text.Convert;

/**
 * 库存记录Service业务层处理
 * 
 * @author jeethink
 * @date 2020-08-13
 */
@Service
public class WmsStockHistoryServiceImpl implements IWmsStockHistoryService 
{
    @Autowired
    private WmsStockHistoryMapper wmsStockHistoryMapper;

    /**
     * 查询库存记录
     * 
     * @param historyId 库存记录ID
     * @return 库存记录
     */
    @Override
    public WmsStockHistory selectWmsStockHistoryById(Long historyId)
    {
        return wmsStockHistoryMapper.selectWmsStockHistoryById(historyId);
    }

    /**
     * 查询库存记录列表
     * 
     * @param wmsStockHistory 库存记录
     * @return 库存记录
     */
    @Override
    public List<WmsStockHistory> selectWmsStockHistoryList(WmsStockHistory wmsStockHistory)
    {
        return wmsStockHistoryMapper.selectWmsStockHistoryList(wmsStockHistory);
    }

    /**
     * 新增库存记录
     * 
     * @param wmsStockHistory 库存记录
     * @return 结果
     */
    @Override
    public int insertWmsStockHistory(WmsStockHistory wmsStockHistory)
    {
        wmsStockHistory.setCreateTime(DateUtils.getNowDate());
        return wmsStockHistoryMapper.insertWmsStockHistory(wmsStockHistory);
    }

    /**
     * 修改库存记录
     * 
     * @param wmsStockHistory 库存记录
     * @return 结果
     */
    @Override
    public int updateWmsStockHistory(WmsStockHistory wmsStockHistory)
    {
        wmsStockHistory.setUpdateTime(DateUtils.getNowDate());
        return wmsStockHistoryMapper.updateWmsStockHistory(wmsStockHistory);
    }

    /**
     * 删除库存记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockHistoryByIds(String ids)
    {
        return wmsStockHistoryMapper.deleteWmsStockHistoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存记录信息
     * 
     * @param historyId 库存记录ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockHistoryById(Long historyId)
    {
        return wmsStockHistoryMapper.deleteWmsStockHistoryById(historyId);
    }
}
