package com.jeethink.crm.service.crm2;

import com.jeethink.crm.domain.CrmOrderPriceProduct;

import java.util.List;

/**
 * 报价单关联产品信息Service接口
 * 
 * @author jeethink
 * @date 2020-03-13
 */
public interface ICrm2OrderPriceProductService
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
     * @param crmOrderProduct 客户订单、报价单关联产品信息
     * @return 客户订单、报价单关联产品信息集合
     */
    public List<CrmOrderPriceProduct> selectCrmOrderPriceProductList(CrmOrderPriceProduct crmOrderPriceProduct);

    /**
     * 新增客户订单、报价单关联产品信息
     * 
     * @param crmOrderProduct 客户订单、报价单关联产品信息
     * @return 结果
     */
    public int insertCrmOrderPriceProduct(CrmOrderPriceProduct crmOrderPriceProduct);

    /**
     * 修改客户订单、报价单关联产品信息
     * 
     * @param crmOrderProduct 客户订单、报价单关联产品信息
     * @return 结果
     */
    public int updateCrmOrderPriceProduct(CrmOrderPriceProduct crmOrderPriceProduct);

    /**
     * 批量删除客户订单、报价单关联产品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmOrderPriceProductByIds(String ids);

    /**
     * 删除客户订单、报价单关联产品信息信息
     * 
     * @param orderProductId 客户订单、报价单关联产品信息ID
     * @return 结果
     */
    public int deleteCrmOrderPriceProductById(Long orderProductId);
    
    /**
     * 批量选择产品给订单
     * 
     * @param loginName 用户名
     * @param orderId 订单ID
     * @param productIds 产品id集合
     * @param productPrices 产品价格集合
     * @param orderType 订单类型  0报价单 1订单
     * @return 结果
     */
    public int insertOrderPriceProducts(String loginName,Long orderId, String productIds, String productPrices, String orderType);
    
    /**
     * 批量更新产品状态为已保存
     *  @param orderId 订单号
     *  @return 结果
     */
    public int  updateOrderPriceProductStatus(Long orderId);
    
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
