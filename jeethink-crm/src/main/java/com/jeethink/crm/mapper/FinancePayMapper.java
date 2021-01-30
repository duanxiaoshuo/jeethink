package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.FinancePay;

/**
 * 回款管理Mapper接口
 * 
 * @author jeethink
 * @date 2020-04-12
 */
public interface FinancePayMapper 
{
    /**
     * 查询回款管理
     * 
     * @param payId 回款管理ID
     * @return 回款管理
     */
    public FinancePay selectFinancePayById(Long payId);

    /**
     * 查询回款管理列表
     * 
     * @param financePay 回款管理
     * @return 回款管理集合
     */
    public List<FinancePay> selectFinancePayList(FinancePay financePay);

    /**
     * 新增回款管理
     * 
     * @param financePay 回款管理
     * @return 结果
     */
    public int insertFinancePay(FinancePay financePay);

    /**
     * 修改回款管理
     * 
     * @param financePay 回款管理
     * @return 结果
     */
    public int updateFinancePay(FinancePay financePay);

    /**
     * 删除回款管理
     * 
     * @param payId 回款管理ID
     * @return 结果
     */
    public int deleteFinancePayById(Long payId);

    /**
     * 批量删除回款管理
     * 
     * @param payIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinancePayByIds(String[] payIds);
}
