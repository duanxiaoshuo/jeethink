package com.jeethink.crm.mapper;

import com.jeethink.crm.domain.CrmOrder;

import java.util.List;

/**
 * 客户订单（报价单）Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-12
 */
public interface Crm2OrderMapper
{
    /**
     * 查询客户订单（报价单）
     * 
     * @param orderId 客户订单（报价单）ID
     * @return 客户订单（报价单）
     */
    public CrmOrder selectCrmOrderById(Long orderId);

    /**
     * 查询客户订单（报价单）列表
     * 
     * @param crmOrder 客户订单（报价单）
     * @return 客户订单（报价单）集合
     */
    public List<CrmOrder> selectCrmOrderList(CrmOrder crmOrder);

    /**
     * 新增客户订单（报价单）
     * 
     * @param crmOrder 客户订单（报价单）
     * @return 结果
     */
    public int insertCrmOrder(CrmOrder crmOrder);

    /**
     * 修改客户订单（报价单）
     * 
     * @param crmOrder 客户订单（报价单）
     * @return 结果
     */
    public int updateCrmOrder(CrmOrder crmOrder);

    /**
     * 删除客户订单（报价单）
     * 
     * @param orderId 客户订单（报价单）ID
     * @return 结果
     */
    public int deleteCrmOrderById(Long orderId);

    /**
     * 批量删除客户订单（报价单）
     * 
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmOrderByIds(String[] orderIds);
    
}
