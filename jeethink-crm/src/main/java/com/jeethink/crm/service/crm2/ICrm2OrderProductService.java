package com.jeethink.crm.service.crm2;

import com.jeethink.crm.domain.CrmOrderProduct;

import java.util.List;

/**
 * 客户订单关联产品信息Service接口
 * 
 * @author jeethink
 * @date 2020-03-13
 */
public interface ICrm2OrderProductService
{
    /**
     * 查询客户订单、报价单关联产品信息
     * 
     * @param orderProductId 客户订单、报价单关联产品信息ID
     * @return 客户订单、报价单关联产品信息
     */
    public CrmOrderProduct selectCrmOrderProductById(Long orderProductId);

    /**
     * 查询客户订单、报价单关联产品信息列表
     * 
     * @param crmOrderProduct 客户订单、报价单关联产品信息
     * @return 客户订单、报价单关联产品信息集合
     */
    public List<CrmOrderProduct> selectCrmOrderProductList(CrmOrderProduct crmOrderProduct);

    /**
     * 新增客户订单、报价单关联产品信息
     * 
     * @param crmOrderProduct 客户订单、报价单关联产品信息
     * @return 结果
     */
    public int insertCrmOrderProduct(CrmOrderProduct crmOrderProduct);

    /**
     * 修改客户订单、报价单关联产品信息
     * 
     * @param crmOrderProduct 客户订单、报价单关联产品信息
     * @return 结果
     */
    public int updateCrmOrderProduct(CrmOrderProduct crmOrderProduct);

    /**
     * 批量删除客户订单、报价单关联产品信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmOrderProductByIds(String ids);

    /**
     * 删除客户订单、报价单关联产品信息信息
     * 
     * @param orderProductId 客户订单、报价单关联产品信息ID
     * @return 结果
     */
    public int deleteCrmOrderProductById(Long orderProductId);
    
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
    public int insertOrderProducts(String loginName,Long orderId, String productIds, String productPrices, String orderType);
    
    /**
     * 批量更新产品状态为已保存
     *  @param orderId 订单号
     *  @return 结果
     */
    public int  updateOrderProductStatus(Long orderId);
    
    /**
     * 批量删除关联ordeID的产品列表
     * @param orderIds 需要删除的数据oderId
     * @return 结果
     */
    public int  deleteCrmOrderProductByOrderIds(String[] orderIds);
    
    /**
     * 查询订单关联产品 总价
     * 
     * @param orderPricetId 报价单ID
     * @return 报价单关联产品总价
     */
    public Double selectOrderTotalPrice(Long orderId);
    
    /**
     * 查询订单关联产品 总成本
     * 
     * @param orderPricetId 报价单ID
     * @return 报价单关联产品总价
     */
    public Double selectOrderCostPrice(Long orderId);
    
    /**
     * 批量新增信息
     * 
     * @param crmOrderProductList 订单产品 列表
     * @return 结果
     */
    public int batchOrderProduct(List<CrmOrderProduct> crmOrderProductList);
}
