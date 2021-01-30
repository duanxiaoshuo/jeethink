package com.jeethink.crm.mapper;

import com.jeethink.crm.domain.CrmOrderPriceProduct;

import java.util.List;

/**
 * 客户订单、报价单关联产品信息Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-13
 */
public interface Crm2OrderPriceProductMapper
{
    /**
     * 查询客户订单、报价单关联产品信息
     * 
     * @param orderProductId 客户订单、报价单关联产品信息ID
     * @return 客户订单、报价单关联产品信息
     */
    public CrmOrderPriceProduct selectCrmOrderPriceProductById(Long orderPriceProductId);

    /**
     * 查询客户订单、报价单关联产品信息列表
     * 
     * @param crmOrderPriceProduct 客户订单、报价单关联产品信息
     * @return 客户订单、报价单关联产品信息集合
     */
    public List<CrmOrderPriceProduct> selectCrmOrderPriceProductList(CrmOrderPriceProduct crmOrderPriceProduct);

    /**
     * 新增客户订单、报价单关联产品信息
     * 
     * @param crmOrderPriceProduct 客户订单、报价单关联产品信息
     * @return 结果
     */
    public int insertCrmOrderPriceProduct(CrmOrderPriceProduct crmOrderPriceProduct);

    /**
     * 修改客户订单、报价单关联产品信息
     * 
     * @param crmOrderPriceProduct 客户订单、报价单关联产品信息
     * @return 结果
     */
    public int updateCrmOrderPriceProduct(CrmOrderPriceProduct crmOrderPriceProduct);

    /**
     * 删除客户订单、报价单关联产品信息
     * 
     * @param orderProductId 客户订单、报价单关联产品信息ID
     * @return 结果
     */
    public int deleteCrmOrderPriceProductById(Long orderPriceProductId);

    /**
     * 批量删除客户订单、报价单关联产品信息
     * 
     * @param orderProductIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmOrderPriceProductByIds(String[] orderPriceProductIds);
    
    /**
     * 批量新增用户角色信息
     * 
     * @param crmOrderPriceProductList 订单产品 列表
     * @return 结果
     */
    public int batchOrderPriceProduct(List<CrmOrderPriceProduct> crmOrderPriceProductList);
    
    /**
     * 批量更新产品状态为已保存
     *  @param orderId 订单号
     *  @return 结果
     */
    public int updateOrderPriceProductStatus(Long orderId);
    
    /**
     * 批量删除关联ordeID的产品列表
     * @param orderIds 需要删除的数据oderId
     * @return 结果
     */
    public int  deleteCrmOrderPriceProductByOrderIds(String[] orderIds);
    
    /**
     * 查询报价单关联产品 总价
     * 
     * @param orderPricetId 报价单ID
     * @return 报价单关联产品总价
     */
    public Double selectOrderPriceTotalPrice(Long orderPricetId);
    
    /**
     * 查询报价单关联产品 总成本
     * 
     * @param orderPricetId 报价单ID
     * @return 报价单关联产品总价
     */
    public Double selectOrderPriceCostPrice(Long orderPricetId);
    
}
