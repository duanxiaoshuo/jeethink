package com.jeethink.crm.service.wms.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.WmsStockProductMapper;
import com.jeethink.crm.domain.WmsStockProduct;
import com.jeethink.crm.service.wms.IWmsStockProductService;
import com.jeethink.common.core.text.Convert;

/**
 * 库存操作和产品对应Service业务层处理
 * 
 * @author jeethink
 * @date 2020-08-21
 */
@Service
public class WmsStockProductServiceImpl implements IWmsStockProductService 
{
    @Autowired
    private WmsStockProductMapper wmsStockProductMapper;

    /**
     * 查询库存操作和产品对应
     * 
     * @param stockProductId 库存操作和产品对应ID
     * @return 库存操作和产品对应
     */
    @Override
    public WmsStockProduct selectWmsStockProductById(Long stockProductId)
    {
        return wmsStockProductMapper.selectWmsStockProductById(stockProductId);
    }

    /**
     * 查询库存操作和产品对应列表
     * 
     * @param wmsStockProduct 库存操作和产品对应
     * @return 库存操作和产品对应
     */
    @Override
    public List<WmsStockProduct> selectWmsStockProductList(WmsStockProduct wmsStockProduct)
    {
        return wmsStockProductMapper.selectWmsStockProductList(wmsStockProduct);
    }

    /**
     * 新增库存操作和产品对应
     * 
     * @param wmsStockProduct 库存操作和产品对应
     * @return 结果
     */
    @Override
    public int insertWmsStockProduct(WmsStockProduct wmsStockProduct)
    {
        wmsStockProduct.setCreateTime(DateUtils.getNowDate());
        return wmsStockProductMapper.insertWmsStockProduct(wmsStockProduct);
    }

    /**
     * 修改库存操作和产品对应
     * 
     * @param wmsStockProduct 库存操作和产品对应
     * @return 结果
     */
    @Override
    public int updateWmsStockProduct(WmsStockProduct wmsStockProduct)
    {
        wmsStockProduct.setUpdateTime(DateUtils.getNowDate());
        return wmsStockProductMapper.updateWmsStockProduct(wmsStockProduct);
    }

    /**
     * 删除库存操作和产品对应对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockProductByIds(String ids)
    {
        return wmsStockProductMapper.deleteWmsStockProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存操作和产品对应信息
     * 
     * @param stockProductId 库存操作和产品对应ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockProductById(Long stockProductId)
    {
        return wmsStockProductMapper.deleteWmsStockProductById(stockProductId);
    }
    
    /**
     * 根据操作ID和类型 批量删除
     * 
     * @param opId 操作id
     * @param type 类型
     * @return 结果
     */
    @Override
    public int deleteWmsStockProductByOpId(Long opId,String type) {
    	return wmsStockProductMapper.deleteWmsStockProductByOpId(opId, type);
    }
}
