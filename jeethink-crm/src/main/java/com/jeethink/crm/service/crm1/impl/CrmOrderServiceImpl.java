package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeethink.crm.mapper.CrmOrderMapper;
import com.jeethink.crm.domain.CrmOrder;
import com.jeethink.crm.domain.CrmOrderProduct;
import com.jeethink.crm.service.crm1.ICrmOrderProductService;
import com.jeethink.crm.service.crm1.ICrmOrderService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;

/**
 * 客户订单（报价单）Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-12
 */
@Service
public class CrmOrderServiceImpl implements ICrmOrderService 
{
    @Autowired
    private CrmOrderMapper crmOrderMapper;
    
    @Autowired
    private ICrmOrderProductService crmOrderProductService;
    

    /**
     * 查询客户订单（报价单）
     * 
     * @param orderId 客户订单（报价单）ID
     * @return 客户订单（报价单）
     */
    @Override
    public CrmOrder selectCrmOrderById(Long orderId)
    {
        return crmOrderMapper.selectCrmOrderById(orderId);
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
        return crmOrderMapper.selectCrmOrderList(crmOrder);
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
        return crmOrderMapper.insertCrmOrder(crmOrder);
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
        crmOrderProductService.updateOrderProductStatus(crmOrder.getOrderId());
        return crmOrderMapper.updateCrmOrder(crmOrder);
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
        	List<CrmOrderProduct> list = crmOrderProductService.selectCrmOrderProductList(crmOrderProduct);
        	if(list.size()>0)
        	{
        		throw new BusinessException(String.format("订单号%1$s存在关联的产品,不能删除", orderId));
        	}
    	}
    	//crmOrderProductMapper.deleteCrmOrderProductByOrderIds(Convert.toStrArray(ids));
        return crmOrderMapper.deleteCrmOrderByIds(Convert.toStrArray(ids));
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
        return crmOrderMapper.deleteCrmOrderById(orderId);
    }
}
