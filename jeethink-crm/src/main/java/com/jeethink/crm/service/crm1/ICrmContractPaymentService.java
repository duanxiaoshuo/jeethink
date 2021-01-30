package com.jeethink.crm.service.crm1;

import java.util.List;
import com.jeethink.crm.domain.CrmContractPayment;

/**
 * 合同付款管理Service接口
 * 
 * @author jeethink
 * @date 2020-03-22
 */
public interface ICrmContractPaymentService 
{
    /**
     * 查询合同付款管理
     * 
     * @param paymentId 合同付款管理ID
     * @return 合同付款管理
     */
    public CrmContractPayment selectCrmContractPaymentById(Long paymentId);

    /**
     * 查询合同付款管理列表
     * 
     * @param crmContractPayment 合同付款管理
     * @return 合同付款管理集合
     */
    public List<CrmContractPayment> selectCrmContractPaymentList(CrmContractPayment crmContractPayment);

    /**
     * 新增合同付款管理
     * 
     * @param crmContractPayment 合同付款管理
     * @return 结果
     */
    public int insertCrmContractPayment(CrmContractPayment crmContractPayment);

    /**
     * 修改合同付款管理
     * 
     * @param crmContractPayment 合同付款管理
     * @return 结果
     */
    public int updateCrmContractPayment(CrmContractPayment crmContractPayment);

    /**
     * 批量删除合同付款管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmContractPaymentByIds(String ids);

    /**
     * 删除合同付款管理信息
     * 
     * @param paymentId 合同付款管理ID
     * @return 结果
     */
    public int deleteCrmContractPaymentById(Long paymentId);
    
    
    /**
     * 合同付款管理 审核通过
     * 
     * @param crmContractPayment 合同付款管理
     * @return 结果
     */
    public int auditCrmContractPayment(CrmContractPayment crmContractPayment);
}
