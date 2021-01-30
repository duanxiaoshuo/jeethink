package com.jeethink.crm.service.finace.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.FinancePayMapper;
import com.jeethink.crm.domain.FinancePay;
import com.jeethink.crm.service.finace.IFinancePayService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;

/**
 * 回款管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-12
 */
@Service
public class FinancePayServiceImpl implements IFinancePayService 
{
    @Autowired
    private FinancePayMapper financePayMapper;

    /**
     * 查询回款管理
     * 
     * @param payId 回款管理ID
     * @return 回款管理
     */
    @Override
    public FinancePay selectFinancePayById(Long payId)
    {
        return financePayMapper.selectFinancePayById(payId);
    }

    /**
     * 查询回款管理列表
     * 
     * @param financePay 回款管理
     * @return 回款管理
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<FinancePay> selectFinancePayList(FinancePay financePay)
    {
        return financePayMapper.selectFinancePayList(financePay);
    }

    /**
     * 新增回款管理
     * 
     * @param financePay 回款管理
     * @return 结果
     */
    @Override
    public int insertFinancePay(FinancePay financePay)
    {
        financePay.setCreateTime(DateUtils.getNowDate());
        return financePayMapper.insertFinancePay(financePay);
    }

    /**
     * 修改回款管理
     * 
     * @param financePay 回款管理
     * @return 结果
     */
    @Override
    public int updateFinancePay(FinancePay financePay)
    {
        financePay.setUpdateTime(DateUtils.getNowDate());
        return financePayMapper.updateFinancePay(financePay);
    }

    /**
     * 删除回款管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFinancePayByIds(String ids)
    {
        return financePayMapper.deleteFinancePayByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除回款管理信息
     * 
     * @param payId 回款管理ID
     * @return 结果
     */
    @Override
    public int deleteFinancePayById(Long payId)
    {
        return financePayMapper.deleteFinancePayById(payId);
    }
}
