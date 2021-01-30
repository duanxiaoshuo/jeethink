package com.jeethink.crm.service.finace.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.FinancePayPlanMapper;
import com.jeethink.crm.domain.FinancePayPlan;
import com.jeethink.crm.service.finace.IFinancePayPlanService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;

/**
 * 回款计划Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-13
 */
@Service
public class FinancePayPlanServiceImpl implements IFinancePayPlanService 
{
    @Autowired
    private FinancePayPlanMapper financePayPlanMapper;

    /**
     * 查询回款计划
     * 
     * @param planId 回款计划ID
     * @return 回款计划
     */
    @Override
    public FinancePayPlan selectFinancePayPlanById(Long planId)
    {
        return financePayPlanMapper.selectFinancePayPlanById(planId);
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
        return financePayPlanMapper.selectFinancePayPlanList(financePayPlan);
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
        return financePayPlanMapper.insertFinancePayPlan(financePayPlan);
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
        return financePayPlanMapper.updateFinancePayPlan(financePayPlan);
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
        return financePayPlanMapper.deleteFinancePayPlanByIds(Convert.toStrArray(ids));
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
        return financePayPlanMapper.deleteFinancePayPlanById(planId);
    }
}
