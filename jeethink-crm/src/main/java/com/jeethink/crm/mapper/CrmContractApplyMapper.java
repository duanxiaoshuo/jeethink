package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.CrmContractApply;

/**
 * 合同申请Mapper接口
 * 
 * @author jeethink
 * @date 2020-04-10
 */
public interface CrmContractApplyMapper 
{
    /**
     * 查询合同申请
     * 
     * @param contractId 合同申请ID
     * @return 合同申请
     */
    public CrmContractApply selectCrmContractApplyById(Long contractId);

    /**
     * 查询合同申请列表
     * 
     * @param crmContractApply 合同申请
     * @return 合同申请集合
     */
    public List<CrmContractApply> selectCrmContractApplyList(CrmContractApply crmContractApply);

    /**
     * 新增合同申请
     * 
     * @param crmContractApply 合同申请
     * @return 结果
     */
    public int insertCrmContractApply(CrmContractApply crmContractApply);

    /**
     * 修改合同申请
     * 
     * @param crmContractApply 合同申请
     * @return 结果
     */
    public int updateCrmContractApply(CrmContractApply crmContractApply);

    /**
     * 删除合同申请
     * 
     * @param contractId 合同申请ID
     * @return 结果
     */
    public int deleteCrmContractApplyById(Long contractId);

    /**
     * 批量删除合同申请
     * 
     * @param contractIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmContractApplyByIds(String[] contractIds);
}
