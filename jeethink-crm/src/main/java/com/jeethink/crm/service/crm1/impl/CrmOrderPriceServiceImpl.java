package com.jeethink.crm.service.crm1.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeethink.crm.mapper.CrmOrderPriceMapper;
import com.jeethink.crm.domain.CrmOrder;
import com.jeethink.crm.domain.CrmOrderPrice;
import com.jeethink.crm.domain.CrmOrderPriceProduct;
import com.jeethink.crm.domain.CrmOrderProduct;
import com.jeethink.crm.service.crm1.ICrmOrderPriceProductService;
import com.jeethink.crm.service.crm1.ICrmOrderPriceService;
import com.jeethink.crm.service.crm1.ICrmOrderProductService;
import com.jeethink.crm.service.crm1.ICrmOrderService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;

/**
 * 客户报价单Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-03
 */
@Service
public class CrmOrderPriceServiceImpl implements ICrmOrderPriceService 
{
    @Autowired
    private CrmOrderPriceMapper crmOrderPriceMapper;

    @Autowired
    private ICrmOrderPriceProductService crmOrderPriceProductService;
    
    @Autowired
    private ICrmOrderService crmOrderService;
    
    @Autowired
    private ICrmOrderProductService crmOrderProductService;
    
    /**
     * 查询客户报价单
     * 
     * @param orderId 客户报价单ID
     * @return 客户报价单
     */
    @Override
    public CrmOrderPrice selectCrmOrderPriceById(Long orderId)
    {
        return crmOrderPriceMapper.selectCrmOrderPriceById(orderId);
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
        return crmOrderPriceMapper.selectCrmOrderPriceList(crmOrderPrice);
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
        return crmOrderPriceMapper.insertCrmOrderPrice(crmOrderPrice);
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
        crmOrderPriceProductService.updateOrderPriceProductStatus(crmOrderPrice.getOrderId());
        return crmOrderPriceMapper.updateCrmOrderPrice(crmOrderPrice);
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
        return crmOrderPriceMapper.deleteCrmOrderPriceByIds(Convert.toStrArray(ids));
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
        return crmOrderPriceMapper.deleteCrmOrderPriceById(orderId);
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
    	List<CrmOrderPriceProduct> listOrderPriceProduct=crmOrderPriceProductService.selectCrmOrderPriceProductList(crmOrderPriceProduct);
    	if(listOrderPriceProduct.size()==0) {
    		throw new BusinessException("报价单没有关联的产品信息，请核查！");
    	}
    	
    	Date nowDate=DateUtils.getNowDate();    
    	//更新报价单
    	CrmOrderPrice crmOrderPrice=crmOrderPriceMapper.selectCrmOrderPriceById(orderId);
    	crmOrderPrice.setOrderStatus("6");
    	crmOrderPrice.setOrderDate(nowDate);
    	crmOrderPrice.setUpdateBy(loginName);
    	crmOrderPrice.setUpdateTime(nowDate);
    	crmOrderPriceMapper.updateCrmOrderPrice(crmOrderPrice);
    	
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
    	crmOrderService.insertCrmOrder(crmOrder);
    	
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
    	return crmOrderProductService.batchOrderProduct(listOrderProduct);
    }
}
