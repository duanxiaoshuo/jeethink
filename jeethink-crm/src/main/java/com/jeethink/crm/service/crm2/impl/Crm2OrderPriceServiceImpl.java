package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.CrmOrder;
import com.jeethink.crm.domain.CrmOrderPrice;
import com.jeethink.crm.domain.CrmOrderPriceProduct;
import com.jeethink.crm.domain.CrmOrderProduct;
import com.jeethink.crm.mapper.Crm2OrderPriceMapper;
import com.jeethink.crm.service.crm2.ICrm2OrderPriceProductService;
import com.jeethink.crm.service.crm2.ICrm2OrderPriceService;
import com.jeethink.crm.service.crm2.ICrm2OrderProductService;
import com.jeethink.crm.service.crm2.ICrm2OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 客户报价单Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-03
 */
@Service
public class Crm2OrderPriceServiceImpl implements ICrm2OrderPriceService
{
    @Autowired
    private Crm2OrderPriceMapper crm2OrderPriceMapper;

    @Autowired
    private ICrm2OrderPriceProductService crm2OrderPriceProductService;
    
    @Autowired
    private ICrm2OrderService crm2OrderService;
    
    @Autowired
    private ICrm2OrderProductService crm2OrderProductService;
    
    /**
     * 查询客户报价单
     * 
     * @param orderId 客户报价单ID
     * @return 客户报价单
     */
    @Override
    public CrmOrderPrice selectCrmOrderPriceById(Long orderId)
    {
        return crm2OrderPriceMapper.selectCrmOrderPriceById(orderId);
    }

    /**
     * 查询客户报价单列表
     * 
     * @param crmOrderPrice 客户报价单
     * @return 客户报价单
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CrmOrderPrice> selectCrmOrderPriceList(CrmOrderPrice crmOrderPrice)
    {
        return crm2OrderPriceMapper.selectCrmOrderPriceList(crmOrderPrice);
    }

    /**
     * 新增客户报价单
     * 
     * @param crmOrderPrice 客户报价单
     * @return 结果
     */
    @Override
    public int insertCrmOrderPrice(CrmOrderPrice crmOrderPrice)
    {
    	crmOrderPrice.setDelFlag("0");
        crmOrderPrice.setCreateTime(DateUtils.getNowDate());
        return crm2OrderPriceMapper.insertCrmOrderPrice(crmOrderPrice);
    }

    /**
     * 修改客户报价单
     * 
     * @param crmOrderPrice 客户报价单
     * @return 结果
     */
    @Override
    @Transactional
    public int updateCrmOrderPrice(CrmOrderPrice crmOrderPrice)
    {
        crmOrderPrice.setUpdateTime(DateUtils.getNowDate());
        crm2OrderPriceProductService.updateOrderPriceProductStatus(crmOrderPrice.getOrderId());
        return crm2OrderPriceMapper.updateCrmOrderPrice(crmOrderPrice);
    }

    /**
     * 删除客户报价单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmOrderPriceByIds(String ids)
    {
        return crm2OrderPriceMapper.deleteCrmOrderPriceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户报价单信息
     * 
     * @param orderId 客户报价单ID
     * @return 结果
     */
    @Override
    public int deleteCrmOrderPriceById(Long orderId)
    {
        return crm2OrderPriceMapper.deleteCrmOrderPriceById(orderId);
    }
    
    /**
     * 转成订单
     * 
     * @param orderId 报价单id
     * @return 结果
     */
    @Override
    @Transactional
    public int convertCrmOrderPriceById(Long orderId,String loginName)
    {
    	CrmOrderPriceProduct crmOrderPriceProduct=new CrmOrderPriceProduct();
    	crmOrderPriceProduct.setOrderId(orderId);
    	List<CrmOrderPriceProduct> listOrderPriceProduct=crm2OrderPriceProductService.selectCrmOrderPriceProductList(crmOrderPriceProduct);
    	if(listOrderPriceProduct.size()==0) {
    		throw new BusinessException("报价单没有关联的产品信息，请核查！");
    	}
    	
    	Date nowDate=DateUtils.getNowDate();    
    	//更新报价单
    	CrmOrderPrice crmOrderPrice=crm2OrderPriceMapper.selectCrmOrderPriceById(orderId);
    	crmOrderPrice.setOrderStatus("6");
    	crmOrderPrice.setOrderDate(nowDate);
    	crmOrderPrice.setUpdateBy(loginName);
    	crmOrderPrice.setUpdateTime(nowDate);
    	crm2OrderPriceMapper.updateCrmOrderPrice(crmOrderPrice);
    	
    	//新增订单
    	CrmOrder crmOrder=new CrmOrder();
    	crmOrder.setCustomerId(crmOrderPrice.getCustomerId());
    	crmOrder.setOrderName(crmOrderPrice.getOrderName());
    	crmOrder.setTotalPrice(crmOrderPrice.getTotalPrice());
    	crmOrder.setCostPrice(crmOrderPrice.getCostPrice());
    	crmOrder.setOrderDate(nowDate);
    	crmOrder.setDelFlag("0");
    	crmOrder.setCreateBy(loginName);
    	crmOrder.setCreateTime(nowDate);
    	crm2OrderService.insertCrmOrder(crmOrder);
    	
    	Long newOrderId=crmOrder.getOrderId();
    	
    	// 新增订单与产品关联
        List<CrmOrderProduct> listOrderProduct = new ArrayList<CrmOrderProduct>();
    	for(int j=0;j<listOrderPriceProduct.size();j++){        	
    		CrmOrderPriceProduct orp=listOrderPriceProduct.get(j);//报价单产品
        	
        	CrmOrderProduct op = new CrmOrderProduct();
        	op.setOrderId(newOrderId);
        	op.setProductId(orp.getProductId());
        	op.setProductCount(orp.getProductCount());
        	op.setSellPrice(orp.getSellPrice());
        	op.setDiscount(orp.getDiscount());
        	op.setRealPrice(orp.getRealPrice());
        	op.setCreateBy(loginName);
        	op.setCreateTime(nowDate);
        	listOrderProduct.add(op);        	
        }
    	return crm2OrderProductService.batchOrderProduct(listOrderProduct);
    }
}
