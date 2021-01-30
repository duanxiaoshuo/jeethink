package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.CrmContractInvoice;

/**
 * 合同发票管理Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-25
 */
public interface CrmContractInvoiceMapper 
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
     * 删除合同发票管理
     * 
     * @param invoiceId 合同发票管理ID
     * @return 结果
     */
    public int deleteCrmContractInvoiceById(Long invoiceId);

    /**
     * 批量删除合同发票管理
     * 
     * @param invoiceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmContractInvoiceByIds(String[] invoiceIds);
}
