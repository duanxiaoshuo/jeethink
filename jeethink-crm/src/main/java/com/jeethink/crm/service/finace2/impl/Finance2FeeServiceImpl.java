package com.jeethink.crm.service.finace2.impl;

import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.FinanceFee;
import com.jeethink.crm.mapper.Finance2FeeMapper;
import com.jeethink.crm.service.finace2.IFinance2FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 费用收支Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-15
 */
@Service
public class Finance2FeeServiceImpl implements IFinance2FeeService
{
    @Autowired
    private Finance2FeeMapper finance2FeeMapper;

    /**
     * 查询费用收支
     * 
     * @param feeId 费用收支ID
     * @return 费用收支
     */
    @Override
    public FinanceFee selectFinanceFeeById(Long feeId)
    {
        return finance2FeeMapper.selectFinanceFeeById(feeId);
    }

    /**
     * 查询费用收支列表
     * 
     * @param financeFee 费用收支
     * @return 费用收支
     */
    @Override
    public List<FinanceFee> selectFinanceFeeList(FinanceFee financeFee)
    {
        return finance2FeeMapper.selectFinanceFeeList(financeFee);
    }

    /**
     * 新增费用收支
     * 
     * @param financeFee 费用收支
     * @return 结果
     */
    @Override
    public int insertFinanceFee(FinanceFee financeFee)
    {
        financeFee.setCreateTime(DateUtils.getNowDate());
        return finance2FeeMapper.insertFinanceFee(financeFee);
    }

    /**
     * 修改费用收支
     * 
     * @param financeFee 费用收支
     * @return 结果
     */
    @Override
    public int updateFinanceFee(FinanceFee financeFee)
    {
        financeFee.setUpdateTime(DateUtils.getNowDate());
        return finance2FeeMapper.updateFinanceFee(financeFee);
    }

    /**
     * 删除费用收支对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFinanceFeeByIds(String ids)
    {
        return finance2FeeMapper.deleteFinanceFeeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除费用收支信息
     * 
     * @param feeId 费用收支ID
     * @return 结果
     */
    @Override
    public int deleteFinanceFeeById(Long feeId)
    {
        return finance2FeeMapper.deleteFinanceFeeById(feeId);
    }
}
