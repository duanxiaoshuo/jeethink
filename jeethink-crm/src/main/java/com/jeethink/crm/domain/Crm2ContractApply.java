package com.jeethink.crm.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 合同申请对象 crm2_contract_apply
 * 
 * @author jeethink
 * @date 2021-01-24
 */
public class Crm2ContractApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同ID */
    private Long contractId;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 成交订单id */
    @Excel(name = "成交订单id")
    private Long orderId;

    /** 合同编号（按一定规则生成） */
    @Excel(name = "合同编号", readConverterExp = "按=一定规则生成")
    private String contractNo;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String contractName;

    /** 合同类型（技术开发、代理记账等） */
    @Excel(name = "合同类型", readConverterExp = "技=术开发、代理记账等")
    private String contractType;

    /** 状态(未审核、审核中、执行中、已完成、已失效) */
    @Excel(name = "状态(未审核、审核中、执行中、已完成、已失效)")
    private String contractStatus;

    /** 合同内容 */
    @Excel(name = "合同内容")
    private String contractContent;

    /** 开始日期 */
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateStart;

    /** 到期日期 */
    @Excel(name = "到期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateEnd;

    /** 付款方式 保留（阶段付款、周期付款） */
    @Excel(name = "付款方式 保留", readConverterExp = "阶=段付款、周期付款")
    private String payMethod;

    /** 总金额，应收款 */
    @Excel(name = "总金额，应收款")
    private Long totalMoney;

    /** 合同签署人 */
    @Excel(name = "合同签署人")
    private String signBy;

    /** 合同签署人姓名 */
    @Excel(name = "合同签署人姓名")
    private String signByName;

    /** 合同签署日期 */
    @Excel(name = "合同签署日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signDate;

    /** 原负责人(login_name) */
    @Excel(name = "原负责人(login_name)")
    private String sourceBelongTo;

    /** 原负责人姓名 */
    @Excel(name = "原负责人姓名")
    private String sourceBelongToName;

    /** 负责人(login_name) */
    @Excel(name = "负责人(login_name)")
    private String belongTo;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String belongToName;

    /** 转交时间 */
    @Excel(name = "转交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trasferDate;

    /** 转交人(login_name) */
    @Excel(name = "转交人(login_name)")
    private String trasferTo;

    /** 转交人姓名 */
    @Excel(name = "转交人姓名")
    private String trasferToName;

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
    private String extContractApplySelect1;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extContractApplySelect2;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extContractApplySelect3;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extContractApplySelect4;

    /** 扩展下拉框 */
    @Excel(name = "扩展下拉框")
    private String extContractApplySelect5;

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

    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
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
    public void setContractNo(String contractNo) 
    {
        this.contractNo = contractNo;
    }

    public String getContractNo() 
    {
        return contractNo;
    }
    public void setContractName(String contractName) 
    {
        this.contractName = contractName;
    }

    public String getContractName() 
    {
        return contractName;
    }
    public void setContractType(String contractType) 
    {
        this.contractType = contractType;
    }

    public String getContractType() 
    {
        return contractType;
    }
    public void setContractStatus(String contractStatus) 
    {
        this.contractStatus = contractStatus;
    }

    public String getContractStatus() 
    {
        return contractStatus;
    }
    public void setContractContent(String contractContent) 
    {
        this.contractContent = contractContent;
    }

    public String getContractContent() 
    {
        return contractContent;
    }
    public void setDateStart(Date dateStart) 
    {
        this.dateStart = dateStart;
    }

    public Date getDateStart() 
    {
        return dateStart;
    }
    public void setDateEnd(Date dateEnd) 
    {
        this.dateEnd = dateEnd;
    }

    public Date getDateEnd() 
    {
        return dateEnd;
    }
    public void setPayMethod(String payMethod) 
    {
        this.payMethod = payMethod;
    }

    public String getPayMethod() 
    {
        return payMethod;
    }
    public void setTotalMoney(Long totalMoney) 
    {
        this.totalMoney = totalMoney;
    }

    public Long getTotalMoney() 
    {
        return totalMoney;
    }
    public void setSignBy(String signBy) 
    {
        this.signBy = signBy;
    }

    public String getSignBy() 
    {
        return signBy;
    }
    public void setSignByName(String signByName) 
    {
        this.signByName = signByName;
    }

    public String getSignByName() 
    {
        return signByName;
    }
    public void setSignDate(Date signDate) 
    {
        this.signDate = signDate;
    }

    public Date getSignDate() 
    {
        return signDate;
    }
    public void setSourceBelongTo(String sourceBelongTo) 
    {
        this.sourceBelongTo = sourceBelongTo;
    }

    public String getSourceBelongTo() 
    {
        return sourceBelongTo;
    }
    public void setSourceBelongToName(String sourceBelongToName) 
    {
        this.sourceBelongToName = sourceBelongToName;
    }

    public String getSourceBelongToName() 
    {
        return sourceBelongToName;
    }
    public void setBelongTo(String belongTo) 
    {
        this.belongTo = belongTo;
    }

    public String getBelongTo() 
    {
        return belongTo;
    }
    public void setBelongToName(String belongToName) 
    {
        this.belongToName = belongToName;
    }

    public String getBelongToName() 
    {
        return belongToName;
    }
    public void setTrasferDate(Date trasferDate) 
    {
        this.trasferDate = trasferDate;
    }

    public Date getTrasferDate() 
    {
        return trasferDate;
    }
    public void setTrasferTo(String trasferTo) 
    {
        this.trasferTo = trasferTo;
    }

    public String getTrasferTo() 
    {
        return trasferTo;
    }
    public void setTrasferToName(String trasferToName) 
    {
        this.trasferToName = trasferToName;
    }

    public String getTrasferToName() 
    {
        return trasferToName;
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
    public void setExtContractApplySelect1(String extContractApplySelect1) 
    {
        this.extContractApplySelect1 = extContractApplySelect1;
    }

    public String getExtContractApplySelect1() 
    {
        return extContractApplySelect1;
    }
    public void setExtContractApplySelect2(String extContractApplySelect2) 
    {
        this.extContractApplySelect2 = extContractApplySelect2;
    }

    public String getExtContractApplySelect2() 
    {
        return extContractApplySelect2;
    }
    public void setExtContractApplySelect3(String extContractApplySelect3) 
    {
        this.extContractApplySelect3 = extContractApplySelect3;
    }

    public String getExtContractApplySelect3() 
    {
        return extContractApplySelect3;
    }
    public void setExtContractApplySelect4(String extContractApplySelect4) 
    {
        this.extContractApplySelect4 = extContractApplySelect4;
    }

    public String getExtContractApplySelect4() 
    {
        return extContractApplySelect4;
    }
    public void setExtContractApplySelect5(String extContractApplySelect5) 
    {
        this.extContractApplySelect5 = extContractApplySelect5;
    }

    public String getExtContractApplySelect5() 
    {
        return extContractApplySelect5;
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
            .append("contractId", getContractId())
            .append("customerId", getCustomerId())
            .append("orderId", getOrderId())
            .append("contractNo", getContractNo())
            .append("contractName", getContractName())
            .append("contractType", getContractType())
            .append("contractStatus", getContractStatus())
            .append("contractContent", getContractContent())
            .append("dateStart", getDateStart())
            .append("dateEnd", getDateEnd())
            .append("payMethod", getPayMethod())
            .append("totalMoney", getTotalMoney())
            .append("signBy", getSignBy())
            .append("signByName", getSignByName())
            .append("signDate", getSignDate())
            .append("sourceBelongTo", getSourceBelongTo())
            .append("sourceBelongToName", getSourceBelongToName())
            .append("belongTo", getBelongTo())
            .append("belongToName", getBelongToName())
            .append("trasferDate", getTrasferDate())
            .append("trasferTo", getTrasferTo())
            .append("trasferToName", getTrasferToName())
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
            .append("extContractApplySelect1", getExtContractApplySelect1())
            .append("extContractApplySelect2", getExtContractApplySelect2())
            .append("extContractApplySelect3", getExtContractApplySelect3())
            .append("extContractApplySelect4", getExtContractApplySelect4())
            .append("extContractApplySelect5", getExtContractApplySelect5())
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
