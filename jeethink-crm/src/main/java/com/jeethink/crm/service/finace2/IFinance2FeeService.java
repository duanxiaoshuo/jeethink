package com.jeethink.crm.service.finace2;

import com.jeethink.crm.domain.FinanceFee;

import java.util.List;

/**
 * 费用收支Service接口
 * 
 * @author jeethink
 * @date 2020-04-15
 */
public interface IFinance2FeeService
{
    /**
     * 查询费用收支
     * 
     * @param feeId 费用收支ID
     * @return 费用收支
     */
    public FinanceFee selectFinanceFeeById(Long feeId);

    /**
     * 查询费用收支列表
     * 
     * @param financeFee 费用收支
     * @return 费用收支集合
     */
    public List<FinanceFee> selectFinanceFeeList(FinanceFee financeFee);

    /**
     * 新增费用收支
     * 
     * @param financeFee 费用收支
     * @return 结果
     */
    public int insertFinanceFee(FinanceFee financeFee);

    /**
     * 修改费用收支
     * 
     * @param financeFee 费用收支
     * @return 结果
     */
    public int updateFinanceFee(FinanceFee financeFee);

    /**
     * 批量删除费用收支
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceFeeByIds(String ids);

    /**
     * 删除费用收支信息
     * 
     * @param feeId 费用收支ID
     * @return 结果
     */
    public int deleteFinanceFeeById(Long feeId);
}
