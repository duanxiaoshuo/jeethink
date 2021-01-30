package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.CrmContract;

/**
 * 合同管理Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-18
 */
public interface CrmContractMapper 
{
    /**
     * 查询合同管理
     * 
     * @param contractId 合同管理ID
     * @return 合同管理
     */
    public CrmContract selectCrmContractById(Long contractId);

    /**
     * 查询合同管理列表
     * 
     * @param crmContract 合同管理
     * @return 合同管理集合
     */
    public List<CrmContract> selectCrmContractList(CrmContract crmContract);

    /**
     * 新增合同管理
     * 
     * @param crmContract 合同管理
     * @return 结果
     */
    public int insertCrmContract(CrmContract crmContract);

    /**
     * 修改合同管理
     * 
     * @param crmContract 合同管理
     * @return 结果
     */
    public int updateCrmContract(CrmContract crmContract);

    /**
     * 删除合同管理
     * 
     * @param contractId 合同管理ID
     * @return 结果
     */
    public int deleteCrmContractById(Long contractId);

    /**
     * 批量删除合同管理
     * 
     * @param contractIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmContractByIds(String[] contractIds);
}
