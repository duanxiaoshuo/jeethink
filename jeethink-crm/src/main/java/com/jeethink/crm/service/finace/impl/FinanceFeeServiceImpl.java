package com.jeethink.crm.service.finace.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.FinanceFeeMapper;
import com.jeethink.crm.domain.FinanceFee;
import com.jeethink.crm.service.finace.IFinanceFeeService;
import com.jeethink.common.core.text.Convert;

/**
 * 费用收支Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-15
 */
@Service
public class FinanceFeeServiceImpl implements IFinanceFeeService 
{
    @Autowired
    private FinanceFeeMapper financeFeeMapper;

    /**
     * 查询费用收支
     * 
     * @param feeId 费用收支ID
     * @return 费用收支
     */
    @Override
    public FinanceFee selectFinanceFeeById(Long feeId)
    {
        return financeFeeMapper.selectFinanceFeeById(feeId);
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
        return financeFeeMapper.selectFinanceFeeList(financeFee);
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
        return financeFeeMapper.insertFinanceFee(financeFee);
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
        return financeFeeMapper.updateFinanceFee(financeFee);
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
        return financeFeeMapper.deleteFinanceFeeByIds(Convert.toStrArray(ids));
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
        return financeFeeMapper.deleteFinanceFeeById(feeId);
    }
}
