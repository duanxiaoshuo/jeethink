package com.jeethink.crm.service.crm2;

import com.jeethink.crm.domain.CrmContractInvoice;

import java.util.List;

/**
 * 合同发票管理Service接口
 * 
 * @author jeethink
 * @date 2020-03-25
 */
public interface ICrm2ContractInvoiceService
{
    /**
     * 查询合同发票管理
     * 
     * @param invoiceId 合同发票管理ID
     * @return 合同发票管理
     */
    public CrmContractInvoice selectCrmContractInvoiceById(Long invoiceId);

    /**
     * 查询合同发票管理列表
     * 
     * @param crmContractInvoice 合同发票管理
     * @return 合同发票管理集合
     */
    public List<CrmContractInvoice> selectCrmContractInvoiceList(CrmContractInvoice crmContractInvoice);

    /**
     * 新增合同发票管理
     * 
     * @param crmContractInvoice 合同发票管理
     * @return 结果
     */
    public int insertCrmContractInvoice(CrmContractInvoice crmContractInvoice);

    /**
     * 修改合同发票管理
     * 
     * @param crmContractInvoice 合同发票管理
     * @return 结果
     */
    public int updateCrmContractInvoice(CrmContractInvoice crmContractInvoice);

    /**
     * 批量删除合同发票管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmContractInvoiceByIds(String ids);

    /**
     * 删除合同发票管理信息
     * 
     * @param invoiceId 合同发票管理ID
     * @return 结果
     */
    public int deleteCrmContractInvoiceById(Long invoiceId);
}
