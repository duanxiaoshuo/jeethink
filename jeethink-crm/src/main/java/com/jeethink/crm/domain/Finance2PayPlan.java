package com.jeethink.crm.domain;

import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 回款计划对象 finance_pay_plan
 * 
 * @author jeethink
 * @date 2020-04-13
 */
public class Finance2PayPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 回款ID */
    private Long planId;

    /** 客户id */
    private Long customerId;
    
    @Excel(name = "所属客户", targetAttr = "customerName", type = Type.EXPORT)
    private CrmCustomer customer;
    
    /** 关联订单ID */
    @Excel(name = "关联订单ID")
    private Long orderId;    

    /** 订单对象 */
    @Excel(name = "订单主题", targetAttr = "orderName", type = Type.EXPORT)
    private CrmOrder order;

    /** 回款主题 */
    @Excel(name = "回款主题")
    private String planName;

    /** 计划回款金额 */
    @Excel(name = "计划回款金额")
    private Long planMoney;

    /** 计划回款日期 */
    @Excel(name = "计划回款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planDate;

    /** 计划回款方式（现金、支票等） */
    @Excel(name = "计划回款方式", readConverterExp = "现=金、支票等")
    private String planType;

    /** 回款期次(首期款，第二期，第三期，第四期) */
    @Excel(name = "回款期次(首期款，第二期，第三期，第四期)")
    private String planTimes;

    /** 回款状态(待收款、已收款、已逾期) */
    @Excel(name = "回款状态(待收款、已收款、已逾期)")
    private String planStatus;

    /** 已回款 */
    @Excel(name = "已回款")
    private Long payedMoney;

    /** 待回款 */
    @Excel(name = "待回款")
    private Long needMoney;

    /** 逾期天数，逾期时使用 */
    @Excel(name = "逾期天数，逾期时使用")
    private Integer overDays;
    
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
    @Excel(name = "扩展文本框")
    private String extInput1;

    /** 扩展文本框 */
    @Excel(name = "扩展文本框")
    private String extInput2;

    /** 扩展文本框 */
    @Excel(name = "扩展文本框")
    private String extInput3;

    /** 扩展文本框 */
    @Excel(name = "扩展文本框")
    private String extInput4;

    /** 扩展文本框 */
    @Excel(name = "扩展文本框")
    private String extInput5;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extFinancePayPlanSelect1;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extFinancePayPlanSelect2;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extFinancePayPlanSelect3;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extFinancePayPlanSelect4;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extFinancePayPlanSelect5;

    /** 扩展数字框 */
    @Excel(name = "扩展数字框")
    private Long extNumber1;

    /** 扩展数字框 */
    @Excel(name = "扩展数字框")
    private Long extNumber2;

    /** 扩展数字框 */
    @Excel(name = "扩展数字框")
    private Long extNumber3;

    /** 扩展数字框 */
    @Excel(name = "扩展数字框")
    private Long extNumber4;

    /** 扩展数字框 */
    @Excel(name = "扩展数字框")
    private Long extNumber5;

    /** 扩展日期 */
    @Excel(name = "扩展日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date extDate1;

    /** 扩展日期 */
    @Excel(name = "扩展日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date extDate2;

    /** 扩展日期 */
    @Excel(name = "扩展日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date extDate3;

    /** 扩展日期 */
    @Excel(name = "扩展日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date extDate4;

    /** 扩展日期 */
    @Excel(name = "扩展日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date extDate5;

    /** 扩展备注 */
    @Excel(name = "扩展备注")
    private String extMemo1;

    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
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
    
    public CrmOrder getOrder() {
		return order;
	}

	public void setOrder(CrmOrder order) {
		this.order = order;
	}

	public void setPlanName(String planName) 
    {
        this.planName = planName;
    }

    public String getPlanName() 
    {
        return planName;
    }
    public void setPlanMoney(Long planMoney) 
    {
        this.planMoney = planMoney;
    }

    public Long getPlanMoney() 
    {
        return planMoney;
    }
    public void setPlanDate(Date planDate) 
    {
        this.planDate = planDate;
    }

    public Date getPlanDate() 
    {
        return planDate;
    }
    public void setPlanType(String planType) 
    {
        this.planType = planType;
    }

    public String getPlanType() 
    {
        return planType;
    }
    public void setPlanTimes(String planTimes) 
    {
        this.planTimes = planTimes;
    }

    public String getPlanTimes() 
    {
        return planTimes;
    }
    public void setPlanStatus(String planStatus) 
    {
        this.planStatus = planStatus;
    }

    public String getPlanStatus() 
    {
        return planStatus;
    }
    public void setPayedMoney(Long payedMoney) 
    {
        this.payedMoney = payedMoney;
    }

    public Long getPayedMoney() 
    {
        return payedMoney;
    }
    public void setNeedMoney(Long needMoney) 
    {
        this.needMoney = needMoney;
    }

    public Long getNeedMoney() 
    {
        return needMoney;
    }
    public void setOverDays(Integer overDays) 
    {
        this.overDays = overDays;
    }

    public Integer getOverDays() 
    {
        return overDays;
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
    public void setExtFinancePayPlanSelect1(String extFinancePayPlanSelect1) 
    {
        this.extFinancePayPlanSelect1 = extFinancePayPlanSelect1;
    }

    public String getExtFinancePayPlanSelect1() 
    {
        return extFinancePayPlanSelect1;
    }
    public void setExtFinancePayPlanSelect2(String extFinancePayPlanSelect2) 
    {
        this.extFinancePayPlanSelect2 = extFinancePayPlanSelect2;
    }

    public String getExtFinancePayPlanSelect2() 
    {
        return extFinancePayPlanSelect2;
    }
    public void setExtFinancePayPlanSelect3(String extFinancePayPlanSelect3) 
    {
        this.extFinancePayPlanSelect3 = extFinancePayPlanSelect3;
    }

    public String getExtFinancePayPlanSelect3() 
    {
        return extFinancePayPlanSelect3;
    }
    public void setExtFinancePayPlanSelect4(String extFinancePayPlanSelect4) 
    {
        this.extFinancePayPlanSelect4 = extFinancePayPlanSelect4;
    }

    public String getExtFinancePayPlanSelect4() 
    {
        return extFinancePayPlanSelect4;
    }
    public void setExtFinancePayPlanSelect5(String extFinancePayPlanSelect5) 
    {
        this.extFinancePayPlanSelect5 = extFinancePayPlanSelect5;
    }

    public String getExtFinancePayPlanSelect5() 
    {
        return extFinancePayPlanSelect5;
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
            .append("planId", getPlanId())
            .append("customerId",getCustomerId())
            .append("orderId", getOrderId())
            .append("planName", getPlanName())
            .append("planMoney", getPlanMoney())
            .append("planDate", getPlanDate())
            .append("planType", getPlanType())
            .append("planTimes", getPlanTimes())
            .append("planStatus", getPlanStatus())
            .append("payedMoney", getPayedMoney())
            .append("needMoney", getNeedMoney())
            .append("overDays", getOverDays())
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
            .append("extFinancePayPlanSelect1", getExtFinancePayPlanSelect1())
            .append("extFinancePayPlanSelect2", getExtFinancePayPlanSelect2())
            .append("extFinancePayPlanSelect3", getExtFinancePayPlanSelect3())
            .append("extFinancePayPlanSelect4", getExtFinancePayPlanSelect4())
            .append("extFinancePayPlanSelect5", getExtFinancePayPlanSelect5())
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