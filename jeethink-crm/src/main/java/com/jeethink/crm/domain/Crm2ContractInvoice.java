package com.jeethink.crm.domain;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 合同发票对象 crm2_contract_invoice
 * 
 * @author jeethink
 * @date 2021-01-24
 */
public class Crm2ContractInvoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 发票ID */
    private Long invoiceId;

    /** 合同ID */
    @Excel(name = "合同ID")
    private Long contractId;

    /** 发票号码 */
    @Excel(name = "发票号码")
    private String invoiceNo;

    /** 发票金额 */
    @Excel(name = "发票金额")
    private BigDecimal invoiceMoney;

    /** 发票类型（增值税普票、增值税专票） */
    @Excel(name = "发票类型", readConverterExp = "增=值税普票、增值税专票")
    private String invoiceType;

    /** 发票税率/% */
    @Excel(name = "发票税率/%")
    private Long invoiceFate;

    /** 发票日期 */
    @Excel(name = "发票日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date invoiceDate;

    /** 状态(已保存、已提交、已审核、已驳回、已开票等) */
    @Excel(name = "状态(已保存、已提交、已审核、已驳回、已开票等)")
    private String invoiceStatus;

    /** 删除标识 */
    private String delFlag;

    public void setInvoiceId(Long invoiceId) 
    {
        this.invoiceId = invoiceId;
    }

    public Long getInvoiceId() 
    {
        return invoiceId;
    }
    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public void setInvoiceNo(String invoiceNo) 
    {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceNo() 
    {
        return invoiceNo;
    }
    public void setInvoiceMoney(BigDecimal invoiceMoney) 
    {
        this.invoiceMoney = invoiceMoney;
    }

    public BigDecimal getInvoiceMoney() 
    {
        return invoiceMoney;
    }
    public void setInvoiceType(String invoiceType) 
    {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceType() 
    {
        return invoiceType;
    }
    public void setInvoiceFate(Long invoiceFate) 
    {
        this.invoiceFate = invoiceFate;
    }

    public Long getInvoiceFate() 
    {
        return invoiceFate;
    }
    public void setInvoiceDate(Date invoiceDate) 
    {
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() 
    {
        return invoiceDate;
    }
    public void setInvoiceStatus(String invoiceStatus) 
    {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceStatus() 
    {
        return invoiceStatus;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("invoiceId", getInvoiceId())
            .append("contractId", getContractId())
            .append("invoiceNo", getInvoiceNo())
            .append("invoiceMoney", getInvoiceMoney())
            .append("invoiceType", getInvoiceType())
            .append("invoiceFate", getInvoiceFate())
            .append("invoiceDate", getInvoiceDate())
            .append("invoiceStatus", getInvoiceStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
