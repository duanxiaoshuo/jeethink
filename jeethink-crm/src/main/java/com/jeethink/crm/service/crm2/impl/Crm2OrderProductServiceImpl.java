package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.CrmOrderProduct;
import com.jeethink.crm.mapper.Crm2OrderProductMapper;
import com.jeethink.crm.service.crm2.ICrm2OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户订单关联产品信息Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-13
 */
@Service
public class Crm2OrderProductServiceImpl implements ICrm2OrderProductService
{
    @Autowired
    private Crm2OrderProductMapper crmOrderProductMapper;

    /**
     * 查询客户订单、报价单关联产品信息
     * 
     * @param orderProductId 客户订单、报价单关联产品信息ID
     * @return 客户订单、报价单关联产品信息
     */
    @Override
    public CrmOrderProduct selectCrmOrderProductById(Long orderProductId)
    {
        return crmOrderProductMapper.selectCrmOrderProductById(orderProductId);
    }

    /**
     * 查询客户订单、报价单关联产品信息列表
     * 
     * @param crmOrderProduct 客户订单、报价单关联产品信息
     * @return 客户订单、报价单关联产品信息
     */
    @Override
    public List<CrmOrderProduct> selectCrmOrderProductList(CrmOrderProduct crmOrderProduct)
    {
        return crmOrderProductMapper.selectCrmOrderProductList(crmOrderProduct);
    }

    /**
     * 新增客户订单、报价单关联产品信息
     * 
     * @param crmOrderProduct 客户订单、报价单关联产品信息
     * @return 结果
     */
    @Override
    public int insertCrmOrderProduct(CrmOrderProduct crmOrderProduct)
    {
    	crmOrderProduct.setDelFlag("0");
        crmOrderProduct.setCreateTime(DateUtils.getNowDate());
        return crmOrderProductMapper.insertCrmOrderProduct(crmOrderProduct);
    }

    /**
     * 修改客户订单、报价单关联产品信息
     * 
     * @param crmOrderProduct 客户订单、报价单关联产品信息
     * @return 结果
     */
    @Override
    public int updateCrmOrderProduct(CrmOrderProduct crmOrderProduct)
    {
        crmOrderProduct.setUpdateTime(DateUtils.getNowDate());
        return crmOrderProductMapper.updateCrmOrderProduct(crmOrderProduct);
    }

    /**
     * 删除客户订单、报价单关联产品信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmOrderProductByIds(String ids)
    {
        return crmOrderProductMapper.deleteCrmOrderProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户订单、报价单关联产品信息信息
     * 
     * @param orderProductId 客户订单、报价单关联产品信息ID
     * @return 结果
     */
    @Override
    public int deleteCrmOrderProductById(Long orderProductId)
    {
        return crmOrderProductMapper.deleteCrmOrderProductById(orderProductId);
    }
    
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
    @Override
    @Transactional
    public int insertOrderProducts(String loginName,Long orderId, String productIds, String productPrices, String orderType)
    {
        Long[] products = Convert.toLongArray(productIds);
        Long[] prices = Convert.toLongArray(productPrices);
        
        // 新增订单与产品管理
        List<CrmOrderProduct> list = new ArrayList<CrmOrderProduct>();
        
        for(int j=0;j<products.length;j++){        	
        	Long productId=products[j];
        	Double price= Convert.toDouble(prices[j]);
        	CrmOrderProduct op = new CrmOrderProduct();
        	op.setOrderId(orderId);
        	op.setProductId(productId);
        	op.setProductCount(1L);
        	op.setSellPrice(price);
        	op.setDiscount(10.0);
        	op.setRealPrice(price);
        	op.setCreateBy(loginName);
        	op.setCreateTime(DateUtils.getNowDate());
            list.add(op);        	
        }

        return crmOrderProductMapper.batchOrderProduct(list);
    }
    
    /**
     * 批量更新产品状态为已保存
     *  @param orderId 订单号
     *  @return 结果
     */
    public int  updateOrderProductStatus(Long orderId)
    {
    	return crmOrderProductMapper.updateOrderProductStatus(orderId);
    }
    
    /**
     * 批量删除关联ordeID的产品列表
     * @param orderIds 需要删除的数据oderId
     * @return 结果
     */
    public int  deleteCrmOrderProductByOrderIds(String[] orderIds) {
    	return crmOrderProductMapper.deleteCrmOrderProductByOrderIds(orderIds);
    }
    
    /**
     * 查询订单关联产品 总价
     * 
     * @param orderPricetId 报价单ID
     * @return 报价单关联产品总价
     */
    @Override
    public Double selectOrderTotalPrice(Long orderId)
    {
        return crmOrderProductMapper.selectOrderTotalPrice(orderId);
    }
    
    /**
     * 查询订单关联产品 总成本
     * 
     * @param orderPricetId 报价单ID
     * @return 报价单关联产品总价
     */
    @Override
    public Double selectOrderCostPrice(Long orderId)
    {
        return crmOrderProductMapper.selectOrderCostPrice(orderId);
    }
    
    /**
     * 批量新增信息
     * 
     * @param crmOrderProductList 订单产品 列表
     * @return 结果
     */
    public int batchOrderProduct(List<CrmOrderProduct> crmOrderProductList) {
    	return crmOrderProductMapper.batchOrderProduct(crmOrderProductList);
    }
}
