package com.jeethink.crm.domain;

import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 客户报价单对象 crm_order_price
 * 
 * @author jeethink
 * @date 2020-04-03
 */
public class Crm2OrderPrice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long orderId;

    /** 订单编码 */
    @Excel(name = "订单编码")
    private String orderCode;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId; 
    
    @Excel(name = "所属客户", targetAttr = "customerName", type = Type.EXPORT)
    private CrmCustomer customer;

    /** 订单名称/主题 */
    @Excel(name = "订单名称/主题")
    private String orderName;

    /** 总价(单子合计价格) */
    @Excel(name = "总价(单子合计价格)")
    private Double totalPrice;
    
    /** 总成本 */
    private Double costPrice;

    /** 报价时间 */
    @Excel(name = "报价时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date prcieDate;

    /** 报价人 */
    @Excel(name = "报价人")
    private String prcieBy;

    /** 报价j截止时间 */
    @Excel(name = "报价截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date prcieEndDate;

    /** 订单时间 */
    @Excel(name = "订单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 订单状态（保留） */
    @Excel(name = "订单状态", readConverterExp = "保=留")
    private String orderStatus;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;
    
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
    private String extOrderPriceSelect1;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extOrderPriceSelect2;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extOrderPriceSelect3;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extOrderPriceSelect4;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extOrderPriceSelect5;

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

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setOrderCode(String orderCode) 
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode() 
    {
        return orderCode;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    
    public CrmCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}

	public void setOrderName(String orderName) 
    {
        this.orderName = orderName;
    }

    public String getOrderName() 
    {
        return orderName;
    }
    public void setTotalPrice(Double totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() 
    {
        return totalPrice;
    }
    
    
    public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public void setPrcieDate(Date prcieDate) 
    {
        this.prcieDate = prcieDate;
    }

    public Date getPrcieDate() 
    {
        return prcieDate;
    }
    public void setPrcieBy(String prcieBy) 
    {
        this.prcieBy = prcieBy;
    }

    public String getPrcieBy() 
    {
        return prcieBy;
    }
    public void setPrcieEndDate(Date prcieEndDate) 
    {
        this.prcieEndDate = prcieEndDate;
    }

    public Date getPrcieEndDate() 
    {
        return prcieEndDate;
    }
    public void setOrderDate(Date orderDate) 
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() 
    {
        return orderDate;
    }
    public void setOrderStatus(String orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() 
    {
        return orderStatus;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
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
    public void setExtOrderPriceSelect1(String extOrderPriceSelect1) 
    {
        this.extOrderPriceSelect1 = extOrderPriceSelect1;
    }

    public String getExtOrderPriceSelect1() 
    {
        return extOrderPriceSelect1;
    }
    public void setExtOrderPriceSelect2(String extOrderPriceSelect2) 
    {
        this.extOrderPriceSelect2 = extOrderPriceSelect2;
    }

    public String getExtOrderPriceSelect2() 
    {
        return extOrderPriceSelect2;
    }
    public void setExtOrderPriceSelect3(String extOrderPriceSelect3) 
    {
        this.extOrderPriceSelect3 = extOrderPriceSelect3;
    }

    public String getExtOrderPriceSelect3() 
    {
        return extOrderPriceSelect3;
    }
    public void setExtOrderPriceSelect4(String extOrderPriceSelect4) 
    {
        this.extOrderPriceSelect4 = extOrderPriceSelect4;
    }

    public String getExtOrderPriceSelect4() 
    {
        return extOrderPriceSelect4;
    }
    public void setExtOrderPriceSelect5(String extOrderPriceSelect5) 
    {
        this.extOrderPriceSelect5 = extOrderPriceSelect5;
    }

    public String getExtOrderPriceSelect5() 
    {
        return extOrderPriceSelect5;
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
            .append("orderId", getOrderId())
            .append("orderCode", getOrderCode())
            .append("customerId", getCustomerId())
            .append("orderName", getOrderName())
            .append("totalPrice", getTotalPrice())
            .append("costPrice", getCostPrice())
            .append("prcieDate", getPrcieDate())
            .append("prcieBy", getPrcieBy())
            .append("prcieEndDate", getPrcieEndDate())
            .append("orderDate", getOrderDate())
            .append("orderStatus", getOrderStatus())
            .append("picture", getPicture())
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
            .append("extOrderPriceSelect1", getExtOrderPriceSelect1())
            .append("extOrderPriceSelect2", getExtOrderPriceSelect2())
            .append("extOrderPriceSelect3", getExtOrderPriceSelect3())
            .append("extOrderPriceSelect4", getExtOrderPriceSelect4())
            .append("extOrderPriceSelect5", getExtOrderPriceSelect5())
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
