package com.jeethink.crm.service.crm1;

import java.util.List;
import com.jeethink.crm.domain.CrmContract;

/**
 * 合同管理Service接口
 * 
 * @author jeethink
 * @date 2020-03-18
 */
public interface ICrmContractService 
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
     * 批量删除合同管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmContractByIds(String ids);

    /**
     * 删除合同管理信息
     * 
     * @param contractId 合同管理ID
     * @return 结果
     */
    public int deleteCrmContractById(Long contractId);
    
    /**
     * 更新合同的状态
     * 2  已审核
     * 4  执行中 在合同期内
     * 5  已完成 过期一个月内
     * 6  已失效 过期一个月外
    */
    public void updateContractStatus();
}
