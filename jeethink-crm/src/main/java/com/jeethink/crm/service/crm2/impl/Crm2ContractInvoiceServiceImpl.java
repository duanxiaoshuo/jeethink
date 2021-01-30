package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.CrmContractInvoice;
import com.jeethink.crm.mapper.Crm2ContractInvoiceMapper;
import com.jeethink.crm.service.crm2.ICrm2ContractInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同发票管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-25
 */
@Service
public class Crm2ContractInvoiceServiceImpl implements ICrm2ContractInvoiceService
{
    @Autowired
    private Crm2ContractInvoiceMapper crm2ContractInvoiceMapper;

    /**
     * 查询合同发票管理
     * 
     * @param invoiceId 合同发票管理ID
     * @return 合同发票管理
     */
    @Override
    public CrmContractInvoice selectCrmContractInvoiceById(Long invoiceId)
    {
        return crm2ContractInvoiceMapper.selectCrmContractInvoiceById(invoiceId);
    }

    /**
     * 查询合同发票管理列表
     * 
     * @param crmContractInvoice 合同发票管理
     * @return 合同发票管理
     */
    @Override
    public List<CrmContractInvoice> selectCrmContractInvoiceList(CrmContractInvoice crmContractInvoice)
    {
        return crm2ContractInvoiceMapper.selectCrmContractInvoiceList(crmContractInvoice);
    }

    /**
     * 新增合同发票管理
     * 
     * @param crmContractInvoice 合同发票管理
     * @return 结果
     */
    @Override
    public int insertCrmContractInvoice(CrmContractInvoice crmContractInvoice)
    {
    	crmContractInvoice.setDelFlag("0");
        crmContractInvoice.setCreateTime(DateUtils.getNowDate());
        return crm2ContractInvoiceMapper.insertCrmContractInvoice(crmContractInvoice);
    }

    /**
     * 修改合同发票管理
     * 
     * @param crmContractInvoice 合同发票管理
     * @return 结果
     */
    @Override
    public int updateCrmContractInvoice(CrmContractInvoice crmContractInvoice)
    {
        crmContractInvoice.setUpdateTime(DateUtils.getNowDate());
        return crm2ContractInvoiceMapper.updateCrmContractInvoice(crmContractInvoice);
    }

    /**
     * 删除合同发票管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmContractInvoiceByIds(String ids)
    {
        return crm2ContractInvoiceMapper.deleteCrmContractInvoiceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合同发票管理信息
     * 
     * @param invoiceId 合同发票管理ID
     * @return 结果
     */
    @Override
    public int deleteCrmContractInvoiceById(Long invoiceId)
    {
        return crm2ContractInvoiceMapper.deleteCrmContractInvoiceById(invoiceId);
    }
}
