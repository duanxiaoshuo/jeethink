package com.jeethink.crm.service.crm1;

import java.util.List;
import com.jeethink.crm.domain.CrmOrder;

/**
 * 客户订单（报价单）Service接口
 * 
 * @author jeethink
 * @date 2020-03-12
 */
public interface ICrmOrderService 
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
     * 批量删除客户订单（报价单）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmOrderByIds(String ids);

    /**
     * 删除客户订单（报价单）信息
     * 
     * @param orderId 客户订单（报价单）ID
     * @return 结果
     */
    public int deleteCrmOrderById(Long orderId);
    
}
