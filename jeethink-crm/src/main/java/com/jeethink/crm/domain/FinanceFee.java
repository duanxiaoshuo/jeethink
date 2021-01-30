package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excels;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 费用收支对象 finance_fee
 * 
 * @author jeethink
 * @date 2020-04-15
 */
public class FinanceFee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 费用收支ID */
    private Long feeId;

    /** 关联客户ID */
    @Excel(name = "客户ID")
    private Long customerId;    

    @Excel(name = "客户名称", targetAttr = "customerName", type = Type.EXPORT)
    private CrmCustomer customer;

    /** 关联订单ID，保留 */
    @Excel(name = "订单ID")
    private Long orderId;
    
    /** 订单对象 */
    @Excel(name = "订单主题", targetAttr = "orderName", type = Type.EXPORT)
    private CrmOrder order;

    /** 费用收支名称 */
    @Excel(name = "费用收支名称")
    private String feeName;

    /** 收支金额 */
    @Excel(name = "收支金额")
    private Double feeMoney;

    /** 收支日期 */
    @Excel(name = "收支日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feeDate;

    /** 收支类型 */
    @Excel(name = "收支类型")
    private String feeType;

    /** 收支状态 */
    @Excel(name = "收支状态")
    private String feeStatus;

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
    private String extFinanceFeeSelect1;

    /** 扩展下拉框 */
    private String extFinanceFeeSelect2;

    /** 扩展下拉框 */
    private String extFinanceFeeSelect3;

    /** 扩展下拉框 */
    private String extFinanceFeeSelect4;

    /** 扩展下拉框 */
    private String extFinanceFeeSelect5;

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

    public void setFeeId(Long feeId) 
    {
        this.feeId = feeId;
    }

    public Long getFeeId() 
    {
        return feeId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }    
    
    public CrmCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}

	public CrmOrder getOrder() {
		return order;
	}

	public void setOrder(CrmOrder order) {
		this.order = order;
	}

	public void setFeeName(String feeName) 
    {
        this.feeName = feeName;
    }

    public String getFeeName() 
    {
        return feeName;
    }
    public void setFeeMoney(Double feeMoney) 
    {
        this.feeMoney = feeMoney;
    }

    public Double getFeeMoney() 
    {
        return feeMoney;
    }
    public void setFeeDate(Date feeDate) 
    {
        this.feeDate = feeDate;
    }

    public Date getFeeDate() 
    {
        return feeDate;
    }
    public void setFeeType(String feeType) 
    {
        this.feeType = feeType;
    }

    public String getFeeType() 
    {
        return feeType;
    }
    public void setFeeStatus(String feeStatus) 
    {
        this.feeStatus = feeStatus;
    }

    public String getFeeStatus() 
    {
        return feeStatus;
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
    public void setExtFinanceFeeSelect1(String extFinanceFeeSelect1) 
    {
        this.extFinanceFeeSelect1 = extFinanceFeeSelect1;
    }

    public String getExtFinanceFeeSelect1() 
    {
        return extFinanceFeeSelect1;
    }
    public void setExtFinanceFeeSelect2(String extFinanceFeeSelect2) 
    {
        this.extFinanceFeeSelect2 = extFinanceFeeSelect2;
    }

    public String getExtFinanceFeeSelect2() 
    {
        return extFinanceFeeSelect2;
    }
    public void setExtFinanceFeeSelect3(String extFinanceFeeSelect3) 
    {
        this.extFinanceFeeSelect3 = extFinanceFeeSelect3;
    }

    public String getExtFinanceFeeSelect3() 
    {
        return extFinanceFeeSelect3;
    }
    public void setExtFinanceFeeSelect4(String extFinanceFeeSelect4) 
    {
        this.extFinanceFeeSelect4 = extFinanceFeeSelect4;
    }

    public String getExtFinanceFeeSelect4() 
    {
        return extFinanceFeeSelect4;
    }
    public void setExtFinanceFeeSelect5(String extFinanceFeeSelect5) 
    {
        this.extFinanceFeeSelect5 = extFinanceFeeSelect5;
    }

    public String getExtFinanceFeeSelect5() 
    {
        return extFinanceFeeSelect5;
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
            .append("feeId", getFeeId())
            .append("customerId", getCustomerId())
            .append("orderId", getOrderId())
            .append("feeName", getFeeName())
            .append("feeMoney", getFeeMoney())
            .append("feeDate", getFeeDate())
            .append("feeType", getFeeType())
            .append("feeStatus", getFeeStatus())
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
            .append("extFinanceFeeSelect1", getExtFinanceFeeSelect1())
            .append("extFinanceFeeSelect2", getExtFinanceFeeSelect2())
            .append("extFinanceFeeSelect3", getExtFinanceFeeSelect3())
            .append("extFinanceFeeSelect4", getExtFinanceFeeSelect4())
            .append("extFinanceFeeSelect5", getExtFinanceFeeSelect5())
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
