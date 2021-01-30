package com.jeethink.crm.service.wms.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.WmsProductMapper;
import com.jeethink.crm.domain.WmsProduct;
import com.jeethink.crm.service.wms.IWmsProductService;
import com.jeethink.common.core.text.Convert;

/**
 * 产品Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-12
 */
@Service
public class WmsProductServiceImpl implements IWmsProductService 
{
    @Autowired
    private WmsProductMapper wmsProductMapper;

    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    @Override
    public WmsProduct selectWmsProductById(Long productId)
    {
        return wmsProductMapper.selectWmsProductById(productId);
    }

    /**
     * 查询产品列表
     * 
     * @param wmsProduct 产品
     * @return 产品
     */
    @Override
    public List<WmsProduct> selectWmsProductList(WmsProduct wmsProduct)
    {
        return wmsProductMapper.selectWmsProductList(wmsProduct);
    }

    /**
     * 新增产品
     * 
     * @param wmsProduct 产品
     * @return 结果
     */
    @Override
    public int insertWmsProduct(WmsProduct wmsProduct)
    {
    	wmsProduct.setDelFlag("0");
        wmsProduct.setCreateTime(DateUtils.getNowDate());
        return wmsProductMapper.insertWmsProduct(wmsProduct);
    }

    /**
     * 修改产品
     * 
     * @param wmsProduct 产品
     * @return 结果
     */
    @Override
    public int updateWmsProduct(WmsProduct wmsProduct)
    {
        wmsProduct.setUpdateTime(DateUtils.getNowDate());
        return wmsProductMapper.updateWmsProduct(wmsProduct);
    }

    /**
     * 删除产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWmsProductByIds(String ids)
    {
        return wmsProductMapper.deleteWmsProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品信息
     * 
     * @param productId 产品ID
     * @return 结果
     */
    @Override
    public int deleteWmsProductById(Long productId)
    {
        return wmsProductMapper.deleteWmsProductById(productId);
    }
    
    /**
     * 查询产品列表  报价单
     * 
     * @param wmsProduct 产品
     * @return 产品集合
     */
    public List<WmsProduct> selectOrderPriceWmsProductList(WmsProduct wmsProduct){
    	return wmsProductMapper.selectOrderPriceWmsProductList(wmsProduct);
    }
    
    /**
     * 查询产品列表  订单
     * 
     * @param wmsProduct 产品
     * @return 产品集合
     */
    public List<WmsProduct> selectOrderWmsProductList(WmsProduct wmsProduct){
    	return wmsProductMapper.selectOrderWmsProductList(wmsProduct);
    }
}
