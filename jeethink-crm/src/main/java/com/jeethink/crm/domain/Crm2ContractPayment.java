package com.jeethink.crm.domain;

import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 合同付款管理对象 crm_contract_payment
 * 
 * @author jeethink
 * @date 2020-03-22
 */
public class Crm2ContractPayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 付款ID */
    private Long paymentId;

    /** 关联合同id */
    @Excel(name = "关联合同id")
    private Long contractId;
    
    @Excel(name = "所属合同", targetAttr = "contractName", type = Type.EXPORT)
    private CrmContract contract;

    /** 金额 */
    @Excel(name = "金额")
    private Double payMoney;

    /** 付款日期 */
    @Excel(name = "付款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payDate;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String payType;

    /** 付款状态 */
    @Excel(name = "付款状态")
    private String payStatus;

    /** 是否需要发票(0:不需要  1:需要) */
    @Excel(name = "是否需要发票(0:不需要  1:需要)")
    private String isInvoice;

    /** 删除标识 */
    private String delFlag;

    public void setPaymentId(Long paymentId) 
    {
        this.paymentId = paymentId;
    }

    public Long getPaymentId() 
    {
        return paymentId;
    }
    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public CrmContract getContract() {
		return contract;
	}

	public void setContract(CrmContract contract) {
		this.contract = contract;
	}

	public void setPayMoney(Double payMoney) 
    {
        this.payMoney = payMoney;
    }

    public Double getPayMoney() 
    {
        return payMoney;
    }
    public void setPayDate(Date payDate) 
    {
        this.payDate = payDate;
    }

    public Date getPayDate() 
    {
        return payDate;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setPayStatus(String payStatus) 
    {
        this.payStatus = payStatus;
    }

    public String getPayStatus() 
    {
        return payStatus;
    }
    public void setIsInvoice(String isInvoice) 
    {
        this.isInvoice = isInvoice;
    }

    public String getIsInvoice() 
    {
        return isInvoice;
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
            .append("paymentId", getPaymentId())
            .append("contractId", getContractId())
            .append("payMoney", getPayMoney())
            .append("payDate", getPayDate())
            .append("payType", getPayType())
            .append("payStatus", getPayStatus())
            .append("isInvoice", getIsInvoice())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
