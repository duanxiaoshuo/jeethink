package com.jeethink.crm.mapper;

import com.jeethink.crm.domain.CrmOrderPrice;

import java.util.List;

/**
 * 客户报价单Mapper接口
 * 
 * @author jeethink
 * @date 2020-04-03
 */
public interface Crm2OrderPriceMapper
{
    /**
     * 查询客户报价单
     * 
     * @param orderId 客户报价单ID
     * @return 客户报价单
     */
    public CrmOrderPrice selectCrmOrderPriceById(Long orderId);

    /**
     * 查询客户报价单列表
     * 
     * @param crmOrderPrice 客户报价单
     * @return 客户报价单集合
     */
    public List<CrmOrderPrice> selectCrmOrderPriceList(CrmOrderPrice crmOrderPrice);

    /**
     * 新增客户报价单
     * 
     * @param crmOrderPrice 客户报价单
     * @return 结果
     */
    public int insertCrmOrderPrice(CrmOrderPrice crmOrderPrice);

    /**
     * 修改客户报价单
     * 
     * @param crmOrderPrice 客户报价单
     * @return 结果
     */
    public int updateCrmOrderPrice(CrmOrderPrice crmOrderPrice);

    /**
     * 删除客户报价单
     * 
     * @param orderId 客户报价单ID
     * @return 结果
     */
    public int deleteCrmOrderPriceById(Long orderId);

    /**
     * 批量删除客户报价单
     * 
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmOrderPriceByIds(String[] orderIds);
}
