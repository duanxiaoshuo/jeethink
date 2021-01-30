package com.jeethink.crm.service.wms.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.WmsStockMapper;
import com.jeethink.crm.domain.WmsStock;
import com.jeethink.crm.service.wms.IWmsStockService;
import com.jeethink.common.core.text.Convert;

/**
 * 库存管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-15
 */
@Service
public class WmsStockServiceImpl implements IWmsStockService 
{
    @Autowired
    private WmsStockMapper wmsStockMapper;

    /**
     * 查询库存管理
     * 
     * @param stockId 库存管理ID
     * @return 库存管理
     */
    @Override
    public WmsStock selectWmsStockById(Long stockId)
    {
        return wmsStockMapper.selectWmsStockById(stockId);
    }

    /**
     * 查询库存管理列表
     * 
     * @param wmsStock 库存管理
     * @return 库存管理
     */
    @Override
    public List<WmsStock> selectWmsStockList(WmsStock wmsStock)
    {
        return wmsStockMapper.selectWmsStockList(wmsStock);
    }

    /**
     * 新增库存管理
     * 
     * @param wmsStock 库存管理
     * @return 结果
     */
    @Override
    public int insertWmsStock(WmsStock wmsStock)
    {
    	wmsStock.setDelFlag("0");
        wmsStock.setCreateTime(DateUtils.getNowDate());
        return wmsStockMapper.insertWmsStock(wmsStock);
    }

    /**
     * 修改库存管理
     * 
     * @param wmsStock 库存管理
     * @return 结果
     */
    @Override
    public int updateWmsStock(WmsStock wmsStock)
    {
        wmsStock.setUpdateTime(DateUtils.getNowDate());
        return wmsStockMapper.updateWmsStock(wmsStock);
    }

    /**
     * 删除库存管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockByIds(String ids)
    {
        return wmsStockMapper.deleteWmsStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存管理信息
     * 
     * @param stockId 库存管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockById(Long stockId)
    {
        return wmsStockMapper.deleteWmsStockById(stockId);
    }
    
    /**
     * 查询库存管理
     * 
     * @param storageId 仓库id
     * @param productId 产品id
     * @return 库存管理
     */
    public WmsStock selectWmsStock(Long storageId,Long productId)
    {
    	return wmsStockMapper.selectWmsStock(storageId,productId);
    }
}
