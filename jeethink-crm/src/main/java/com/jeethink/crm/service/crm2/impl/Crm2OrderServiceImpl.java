package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.CrmOrder;
import com.jeethink.crm.domain.CrmOrderProduct;
import com.jeethink.crm.mapper.Crm2OrderMapper;
import com.jeethink.crm.service.crm2.ICrm2OrderProductService;
import com.jeethink.crm.service.crm2.ICrm2OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客户订单（报价单）Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-12
 */
@Service
public class Crm2OrderServiceImpl implements ICrm2OrderService
{
    @Autowired
    private Crm2OrderMapper crm2OrderMapper;
    
    @Autowired
    private ICrm2OrderProductService crm2OrderProductService;
    

    /**
     * 查询客户订单（报价单）
     * 
     * @param orderId 客户订单（报价单）ID
     * @return 客户订单（报价单）
     */
    @Override
    public CrmOrder selectCrmOrderById(Long orderId)
    {
        return crm2OrderMapper.selectCrmOrderById(orderId);
    }

    /**
     * 查询客户订单（报价单）列表
     * 
     * @param crmOrder 客户订单（报价单）
     * @return 客户订单（报价单）
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CrmOrder> selectCrmOrderList(CrmOrder crmOrder)
    {
        return crm2OrderMapper.selectCrmOrderList(crmOrder);
    }

    /**
     * 新增客户订单（报价单）
     * 
     * @param crmOrder 客户订单（报价单）
     * @return 结果
     */
    @Override
    public int insertCrmOrder(CrmOrder crmOrder)
    {
    	crmOrder.setDelFlag("0");
        crmOrder.setCreateTime(DateUtils.getNowDate());
        return crm2OrderMapper.insertCrmOrder(crmOrder);
    }

    /**
     * 修改客户订单（报价单）
     * 
     * @param crmOrder 客户订单（报价单）
     * @return 结果
     */
    @Override
    @Transactional
    public int updateCrmOrder(CrmOrder crmOrder)
    {
        crmOrder.setUpdateTime(DateUtils.getNowDate());
        crm2OrderProductService.updateOrderProductStatus(crmOrder.getOrderId());
        return crm2OrderMapper.updateCrmOrder(crmOrder);
    }

    /**
     * 删除客户订单（报价单）对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmOrderByIds(String ids)
    {
    	Long[] orderIds = Convert.toLongArray(ids);
    	for(int j=0;j<orderIds.length;j++){    
    		Long orderId=orderIds[j];
    		CrmOrderProduct crmOrderProduct=new CrmOrderProduct();
        	crmOrderProduct.setOrderId(orderId);
        	crmOrderProduct.setDelFlag("0");
        	List<CrmOrderProduct> list = crm2OrderProductService.selectCrmOrderProductList(crmOrderProduct);
        	if(list.size()>0)
        	{
        		throw new BusinessException(String.format("订单号%1$s存在关联的产品,不能删除", orderId));
        	}
    	}
    	//crmOrderProductMapper.deleteCrmOrderProductByOrderIds(Convert.toStrArray(ids));
        return crm2OrderMapper.deleteCrmOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户订单（报价单）信息
     * 
     * @param orderId 客户订单（报价单）ID
     * @return 结果
     */
    @Override
    public int deleteCrmOrderById(Long orderId)
    {
        return crm2OrderMapper.deleteCrmOrderById(orderId);
    }
}
