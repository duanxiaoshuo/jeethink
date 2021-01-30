package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.CrmOrderPriceProduct;
import com.jeethink.crm.mapper.Crm2OrderPriceProductMapper;
import com.jeethink.crm.service.crm2.ICrm2OrderPriceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 报价单关联产品信息Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-13
 */
@Service
public class Crm2OrderPriceProductServiceImpl implements ICrm2OrderPriceProductService 
{
    @Autowired
    private Crm2OrderPriceProductMapper crm2OrderPriceProductMapper;

    /**
     * 查询报价单关联产品信息
     * 
     * @param orderProductId 报价单关联产品信息ID
     * @return 报价单关联产品信息
     */
    @Override
    public CrmOrderPriceProduct selectCrmOrderPriceProductById(Long orderProductId)
    {
        return crm2OrderPriceProductMapper.selectCrmOrderPriceProductById(orderProductId);
    }

    /**
     * 查询报价单关联产品信息列表
     * 
     * @param crmOrderProduct 报价单关联产品信息
     * @return 报价单关联产品信息
     */
    @Override
    public List<CrmOrderPriceProduct> selectCrmOrderPriceProductList(CrmOrderPriceProduct crmOrderPriceProduct)
    {
        return crm2OrderPriceProductMapper.selectCrmOrderPriceProductList(crmOrderPriceProduct);
    }

    /**
     * 新增报价单关联产品信息
     * 
     * @param crmOrderProduct 报价单关联产品信息
     * @return 结果
     */
    @Override
    public int insertCrmOrderPriceProduct(CrmOrderPriceProduct crmOrderPriceProduct)
    {
    	crmOrderPriceProduct.setDelFlag("0");
    	crmOrderPriceProduct.setCreateTime(DateUtils.getNowDate());
        return crm2OrderPriceProductMapper.insertCrmOrderPriceProduct(crmOrderPriceProduct);
    }

    /**
     * 修改报价单关联产品信息
     * 
     * @param crmOrderProduct 报价单关联产品信息
     * @return 结果
     */
    @Override
    public int updateCrmOrderPriceProduct(CrmOrderPriceProduct crmOrderPriceProduct)
    {
    	crmOrderPriceProduct.setUpdateTime(DateUtils.getNowDate());
        return crm2OrderPriceProductMapper.updateCrmOrderPriceProduct(crmOrderPriceProduct);
    }

    /**
     * 删除报价单关联产品信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmOrderPriceProductByIds(String ids)
    {
        return crm2OrderPriceProductMapper.deleteCrmOrderPriceProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报价单关联产品信息信息
     * 
     * @param orderProductId 报价单关联产品信息ID
     * @return 结果
     */
    @Override
    public int deleteCrmOrderPriceProductById(Long orderProductId)
    {
        return crm2OrderPriceProductMapper.deleteCrmOrderPriceProductById(orderProductId);
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
    public int insertOrderPriceProducts(String loginName,Long orderId, String productIds, String productPrices, String orderType)
    {
        Long[] products = Convert.toLongArray(productIds);
        Long[] prices = Convert.toLongArray(productPrices);
        
        // 新增订单与产品管理
        List<CrmOrderPriceProduct> list = new ArrayList<CrmOrderPriceProduct>();
        
        for(int j=0;j<products.length;j++){        	
        	Long productId=products[j];
        	Double price= Convert.toDouble(prices[j]);
        	CrmOrderPriceProduct op = new CrmOrderPriceProduct();
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
        return crm2OrderPriceProductMapper.batchOrderPriceProduct(list);
    }
    
    /**
     * 批量更新产品状态为已保存
     *  @param orderId 订单号
     *  @return 结果
     */
    public int  updateOrderPriceProductStatus(Long orderId)
    {
    	return crm2OrderPriceProductMapper.updateOrderPriceProductStatus(orderId);
    }
    
    /**
     * 批量删除关联ordeID的产品列表
     * @param orderIds 需要删除的数据oderId
     * @return 结果
     */
    public int  deleteCrmOrderPriceProductByOrderIds(String[] orderIds) {
    	return crm2OrderPriceProductMapper.deleteCrmOrderPriceProductByOrderIds(orderIds);
    }
    
    /**
     * 查询报价单关联产品 总价
     * 
     * @param orderPricetId 报价单ID
     * @return 报价单关联产品总价
     */
    @Override
    public Double selectOrderPriceTotalPrice(Long orderPricetId)
    {
        return crm2OrderPriceProductMapper.selectOrderPriceTotalPrice(orderPricetId);
    }
    
    /**
     * 查询报价单关联产品 总成本
     * 
     * @param orderPricetId 报价单ID
     * @return 报价单关联产品总价
     */
    @Override
    public Double selectOrderPriceCostPrice(Long orderPricetId)
    {
        return crm2OrderPriceProductMapper.selectOrderPriceCostPrice(orderPricetId);
    }
}
