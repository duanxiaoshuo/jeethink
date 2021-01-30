package com.jeethink.crm.service.finace2.impl;

import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.FinancePayPlan;
import com.jeethink.crm.mapper.Finance2PayPlanMapper;
import com.jeethink.crm.service.finace2.IFinance2PayPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回款计划Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-13
 */
@Service
public class Finance2PayPlanServiceImpl implements IFinance2PayPlanService
{
    @Autowired
    private Finance2PayPlanMapper finance2PayPlanMapper;

    /**
     * 查询回款计划
     * 
     * @param planId 回款计划ID
     * @return 回款计划
     */
    @Override
    public FinancePayPlan selectFinancePayPlanById(Long planId)
    {
        return finance2PayPlanMapper.selectFinancePayPlanById(planId);
    }

    /**
     * 查询回款计划列表
     * 
     * @param financePayPlan 回款计划
     * @return 回款计划
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<FinancePayPlan> selectFinancePayPlanList(FinancePayPlan financePayPlan)
    {
        return finance2PayPlanMapper.selectFinancePayPlanList(financePayPlan);
    }

    /**
     * 新增回款计划
     * 
     * @param financePayPlan 回款计划
     * @return 结果
     */
    @Override
    public int insertFinancePayPlan(FinancePayPlan financePayPlan)
    {
        financePayPlan.setCreateTime(DateUtils.getNowDate());
        return finance2PayPlanMapper.insertFinancePayPlan(financePayPlan);
    }

    /**
     * 修改回款计划
     * 
     * @param financePayPlan 回款计划
     * @return 结果
     */
    @Override
    public int updateFinancePayPlan(FinancePayPlan financePayPlan)
    {
        financePayPlan.setUpdateTime(DateUtils.getNowDate());
        return finance2PayPlanMapper.updateFinancePayPlan(financePayPlan);
    }

    /**
     * 删除回款计划对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFinancePayPlanByIds(String ids)
    {
        return finance2PayPlanMapper.deleteFinancePayPlanByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除回款计划信息
     * 
     * @param planId 回款计划ID
     * @return 结果
     */
    @Override
    public int deleteFinancePayPlanById(Long planId)
    {
        return finance2PayPlanMapper.deleteFinancePayPlanById(planId);
    }
}
