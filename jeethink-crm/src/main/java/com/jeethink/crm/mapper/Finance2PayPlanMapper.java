package com.jeethink.crm.mapper;

import com.jeethink.crm.domain.FinancePayPlan;

import java.util.List;

/**
 * 回款计划Mapper接口
 * 
 * @author jeethink
 * @date 2020-04-13
 */
public interface Finance2PayPlanMapper
{
    /**
     * 查询回款计划
     * 
     * @param planId 回款计划ID
     * @return 回款计划
     */
    public FinancePayPlan selectFinancePayPlanById(Long planId);

    /**
     * 查询回款计划列表
     * 
     * @param financePayPlan 回款计划
     * @return 回款计划集合
     */
    public List<FinancePayPlan> selectFinancePayPlanList(FinancePayPlan financePayPlan);

    /**
     * 新增回款计划
     * 
     * @param financePayPlan 回款计划
     * @return 结果
     */
    public int insertFinancePayPlan(FinancePayPlan financePayPlan);

    /**
     * 修改回款计划
     * 
     * @param financePayPlan 回款计划
     * @return 结果
     */
    public int updateFinancePayPlan(FinancePayPlan financePayPlan);

    /**
     * 删除回款计划
     * 
     * @param planId 回款计划ID
     * @return 结果
     */
    public int deleteFinancePayPlanById(Long planId);

    /**
     * 批量删除回款计划
     * 
     * @param planIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinancePayPlanByIds(String[] planIds);
}
