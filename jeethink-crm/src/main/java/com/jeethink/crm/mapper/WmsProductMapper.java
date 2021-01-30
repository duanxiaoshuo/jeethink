package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.WmsProduct;

/**
 * 产品Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-12
 */
public interface WmsProductMapper 
{
    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    public WmsProduct selectWmsProductById(Long productId);

    /**
     * 查询产品列表
     * 
     * @param wmsProduct 产品
     * @return 产品集合
     */
    public List<WmsProduct> selectWmsProductList(WmsProduct wmsProduct);

    /**
     * 新增产品
     * 
     * @param wmsProduct 产品
     * @return 结果
     */
    public int insertWmsProduct(WmsProduct wmsProduct);

    /**
     * 修改产品
     * 
     * @param wmsProduct 产品
     * @return 结果
     */
    public int updateWmsProduct(WmsProduct wmsProduct);

    /**
     * 删除产品
     * 
     * @param productId 产品ID
     * @return 结果
     */
    public int deleteWmsProductById(Long productId);

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsProductByIds(String[] productIds);
    
    /**
     * 查询产品列表  报价单
     * 
     * @param wmsProduct 产品
     * @return 产品集合
     */
    public List<WmsProduct> selectOrderPriceWmsProductList(WmsProduct wmsProduct);
    
    /**
     * 查询产品列表  订单
     * 
     * @param wmsProduct 产品
     * @return 产品集合
     */
    public List<WmsProduct> selectOrderWmsProductList(WmsProduct wmsProduct);
}
