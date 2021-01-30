package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.CrmContractInvoiceMapper;
import com.jeethink.crm.domain.CrmContractInvoice;
import com.jeethink.crm.service.crm1.ICrmContractInvoiceService;
import com.jeethink.common.core.text.Convert;

/**
 * 合同发票管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-25
 */
@Service
public class CrmContractInvoiceServiceImpl implements ICrmContractInvoiceService 
{
    @Autowired
    private CrmContractInvoiceMapper crmContractInvoiceMapper;

    /**
     * 查询合同发票管理
     * 
     * @param invoiceId 合同发票管理ID
     * @return 合同发票管理
     */
    @Override
    public CrmContractInvoice selectCrmContractInvoiceById(Long invoiceId)
    {
        return crmContractInvoiceMapper.selectCrmContractInvoiceById(invoiceId);
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
        return crmContractInvoiceMapper.selectCrmContractInvoiceList(crmContractInvoice);
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
        return crmContractInvoiceMapper.insertCrmContractInvoice(crmContractInvoice);
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
        return crmContractInvoiceMapper.updateCrmContractInvoice(crmContractInvoice);
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
        return crmContractInvoiceMapper.deleteCrmContractInvoiceByIds(Convert.toStrArray(ids));
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
        return crmContractInvoiceMapper.deleteCrmContractInvoiceById(invoiceId);
    }
}
