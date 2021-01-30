package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excels;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 回款管理对象 finance_pay
 * 
 * @author jeethink
 * @date 2020-04-12
 */
public class FinancePay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同付款ID */
    private Long payId;

    /** 客户id */
    private Long customerId;
    
    @Excel(name = "所属客户", targetAttr = "customerName", type = Type.EXPORT)
    private CrmCustomer customer;
    
    /** 关联订单ID */
    //@Excel(name = "关联订单ID")
    private Long orderId;
    
    /** 订单对象 */
    @Excel(name = "订单主题", targetAttr = "orderName", type = Type.EXPORT)
    private CrmOrder order;

    public CrmOrder getOrder() {
		return order;
	}

	public void setOrder(CrmOrder order) {
		this.order = order;
	}

	/** 付款金额 */
    @Excel(name = "付款金额")
    private Long payMoney;

    /** 付款日期 */
    @Excel(name = "付款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payDate;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String payType;

    /** 付款期次 */
    @Excel(name = "付款期次")
    private String payTimes;

    /** 付款状态 */
    @Excel(name = "付款状态")
    private String payStatus;

    /** 是否需要发票 */
    @Excel(name = "是否需要发票")
    private String isInvoice;
    
    /** 原负责人(login_name) */
    @Excel(name = "原负责人(login_name)")
    private String sourceBelongTo;

    /** 负责人(login_name) */
    @Excel(name = "负责人(login_name)")
    private String belongTo;

    /** 转交时间 */
    @Excel(name = "转交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trasferDate;

    /** 转交人(login_name) */
    @Excel(name = "转交人(login_name)")
    private String trasferTo;

    /** 删除标识 */
    private String delFlag;

    /** 扩展文本框 */
    private String extInput1;

    /** 扩展文本框 */
    private String extInput2;

    /** 扩展文本框 */
    private String extInput3;

    /** 扩展文本框 */
    private String extInput4;

    /** 扩展文本框 */
    private String extInput5;

    /** 扩展下拉框 */
    private String extFinancePaymentSelect1;

    /** 扩展下拉框 */
    private String extFinancePaymentSelect2;

    /** 扩展下拉框 */
    private String extFinancePaymentSelect3;

    /** 扩展下拉框 */
    private String extFinancePaymentSelect4;

    /** 扩展下拉框 */
    private String extFinancePaymentSelect5;

    /** 扩展数字框 */
    private Long extNumber1;

    /** 扩展数字框 */
    private Long extNumber2;

    /** 扩展数字框 */
    private Long extNumber3;

    /** 扩展数字框 */
    private Long extNumber4;

    /** 扩展数字框 */
    private Long extNumber5;

    /** 扩展日期 */
    private Date extDate1;

    /** 扩展日期 */
    private Date extDate2;

    /** 扩展日期 */
    private Date extDate3;

    /** 扩展日期 */
    private Date extDate4;

    /** 扩展日期 */
    private Date extDate5;

    /** 扩展备注 */
    private String extMemo1;

    public void setPayId(Long payId) 
    {
        this.payId = payId;
    }

    public Long getPayId() 
    {
        return payId;
    }
    
    public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public CrmCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}

	public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    

	public void setPayMoney(Long payMoney) 
    {
        this.payMoney = payMoney;
    }

    public Long getPayMoney() 
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
    public void setPayTimes(String payTimes) 
    {
        this.payTimes = payTimes;
    }

    public String getPayTimes() 
    {
        return payTimes;
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
    
    public String getSourceBelongTo() {
		return sourceBelongTo;
	}

	public void setSourceBelongTo(String sourceBelongTo) {
		this.sourceBelongTo = sourceBelongTo;
	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}

	public Date getTrasferDate() {
		return trasferDate;
	}

	public void setTrasferDate(Date trasferDate) {
		this.trasferDate = trasferDate;
	}

	public String getTrasferTo() {
		return trasferTo;
	}

	public void setTrasferTo(String trasferTo) {
		this.trasferTo = trasferTo;
	}

	public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setExtInput1(String extInput1) 
    {
        this.extInput1 = extInput1;
    }

    public String getExtInput1() 
    {
        return extInput1;
    }
    public void setExtInput2(String extInput2) 
    {
        this.extInput2 = extInput2;
    }

    public String getExtInput2() 
    {
        return extInput2;
    }
    public void setExtInput3(String extInput3) 
    {
        this.extInput3 = extInput3;
    }

    public String getExtInput3() 
    {
        return extInput3;
    }
    public void setExtInput4(String extInput4) 
    {
        this.extInput4 = extInput4;
    }

    public String getExtInput4() 
    {
        return extInput4;
    }
    public void setExtInput5(String extInput5) 
    {
        this.extInput5 = extInput5;
    }

    public String getExtInput5() 
    {
        return extInput5;
    }
    public void setExtFinancePaymentSelect1(String extFinancePaymentSelect1) 
    {
        this.extFinancePaymentSelect1 = extFinancePaymentSelect1;
    }

    public String getExtFinancePaymentSelect1() 
    {
        return extFinancePaymentSelect1;
    }
    public void setExtFinancePaymentSelect2(String extFinancePaymentSelect2) 
    {
        this.extFinancePaymentSelect2 = extFinancePaymentSelect2;
    }

    public String getExtFinancePaymentSelect2() 
    {
        return extFinancePaymentSelect2;
    }
    public void setExtFinancePaymentSelect3(String extFinancePaymentSelect3) 
    {
        this.extFinancePaymentSelect3 = extFinancePaymentSelect3;
    }

    public String getExtFinancePaymentSelect3() 
    {
        return extFinancePaymentSelect3;
    }
    public void setExtFinancePaymentSelect4(String extFinancePaymentSelect4) 
    {
        this.extFinancePaymentSelect4 = extFinancePaymentSelect4;
    }

    public String getExtFinancePaymentSelect4() 
    {
        return extFinancePaymentSelect4;
    }
    public void setExtFinancePaymentSelect5(String extFinancePaymentSelect5) 
    {
        this.extFinancePaymentSelect5 = extFinancePaymentSelect5;
    }

    public String getExtFinancePaymentSelect5() 
    {
        return extFinancePaymentSelect5;
    }
    public void setExtNumber1(Long extNumber1) 
    {
        this.extNumber1 = extNumber1;
    }

    public Long getExtNumber1() 
    {
        return extNumber1;
    }
    public void setExtNumber2(Long extNumber2) 
    {
        this.extNumber2 = extNumber2;
    }

    public Long getExtNumber2() 
    {
        return extNumber2;
    }
    public void setExtNumber3(Long extNumber3) 
    {
        this.extNumber3 = extNumber3;
    }

    public Long getExtNumber3() 
    {
        return extNumber3;
    }
    public void setExtNumber4(Long extNumber4) 
    {
        this.extNumber4 = extNumber4;
    }

    public Long getExtNumber4() 
    {
        return extNumber4;
    }
    public void setExtNumber5(Long extNumber5) 
    {
        this.extNumber5 = extNumber5;
    }

    public Long getExtNumber5() 
    {
        return extNumber5;
    }
    public void setExtDate1(Date extDate1) 
    {
        this.extDate1 = extDate1;
    }

    public Date getExtDate1() 
    {
        return extDate1;
    }
    public void setExtDate2(Date extDate2) 
    {
        this.extDate2 = extDate2;
    }

    public Date getExtDate2() 
    {
        return extDate2;
    }
    public void setExtDate3(Date extDate3) 
    {
        this.extDate3 = extDate3;
    }

    public Date getExtDate3() 
    {
        return extDate3;
    }
    public void setExtDate4(Date extDate4) 
    {
        this.extDate4 = extDate4;
    }

    public Date getExtDate4() 
    {
        return extDate4;
    }
    public void setExtDate5(Date extDate5) 
    {
        this.extDate5 = extDate5;
    }

    public Date getExtDate5() 
    {
        return extDate5;
    }
    public void setExtMemo1(String extMemo1) 
    {
        this.extMemo1 = extMemo1;
    }

    public String getExtMemo1() 
    {
        return extMemo1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("payId", getPayId())
            .append("customerId",getCustomerId())
            .append("orderId", getOrderId())
            .append("payMoney", getPayMoney())
            .append("payDate", getPayDate())
            .append("payType", getPayType())
            .append("payTimes", getPayTimes())
            .append("payStatus", getPayStatus())
            .append("isInvoice", getIsInvoice())
            .append("sourceBelongTo", getSourceBelongTo())
            .append("belongTo", getBelongTo())
            .append("trasferDate", getTrasferDate())
            .append("trasferTo", getTrasferTo())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("extInput1", getExtInput1())
            .append("extInput2", getExtInput2())
            .append("extInput3", getExtInput3())
            .append("extInput4", getExtInput4())
            .append("extInput5", getExtInput5())
            .append("extFinancePaymentSelect1", getExtFinancePaymentSelect1())
            .append("extFinancePaymentSelect2", getExtFinancePaymentSelect2())
            .append("extFinancePaymentSelect3", getExtFinancePaymentSelect3())
            .append("extFinancePaymentSelect4", getExtFinancePaymentSelect4())
            .append("extFinancePaymentSelect5", getExtFinancePaymentSelect5())
            .append("extNumber1", getExtNumber1())
            .append("extNumber2", getExtNumber2())
            .append("extNumber3", getExtNumber3())
            .append("extNumber4", getExtNumber4())
            .append("extNumber5", getExtNumber5())
            .append("extDate1", getExtDate1())
            .append("extDate2", getExtDate2())
            .append("extDate3", getExtDate3())
            .append("extDate4", getExtDate4())
            .append("extDate5", getExtDate5())
            .append("extMemo1", getExtMemo1())
            .toString();
    }
}
