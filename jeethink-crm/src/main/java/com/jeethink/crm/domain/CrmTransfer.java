package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 转交记录对象 crm_transfer
 * 
 * @author jeethink
 * @date 2020-04-19
 */
public class CrmTransfer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 拜访ID */
    private Long transferId;

    /** 业务ID，客户，订单、回款等的ID */
    private Long businessId;

    /** 业务类型，客户，订单、回款等 */
    private String businessType;

    /** 转交类型（0 公客  1 转交人） */
    private String transferType;

    /** 转交人 */
    @Excel(name = "转交人")
    private String transferTo;

    /** 转给谁 */
    @Excel(name = "转给谁")
    private String belongTo;

    /** 转交原因 */
    @Excel(name = "转交原因")
    private String reason;

    /** 转为公客时使用，表示公客分组id */
    @Excel(name = "转为公客时使用，表示公客分组id")
    private Long publicId;

    /** 删除标识 */
    private String delFlag;

    public void setTransferId(Long transferId) 
    {
        this.transferId = transferId;
    }

    public Long getTransferId() 
    {
        return transferId;
    }
    public void setBusinessId(Long businessId) 
    {
        this.businessId = businessId;
    }

    public Long getBusinessId() 
    {
        return businessId;
    }
    public void setBusinessType(String businessType) 
    {
        this.businessType = businessType;
    }

    public String getBusinessType() 
    {
        return businessType;
    }
    public void setTransferType(String transferType) 
    {
        this.transferType = transferType;
    }

    public String getTransferType() 
    {
        return transferType;
    }
    public void setTransferTo(String transferTo) 
    {
        this.transferTo = transferTo;
    }

    public String getTransferTo() 
    {
        return transferTo;
    }
    public void setBelongTo(String belongTo) 
    {
        this.belongTo = belongTo;
    }

    public String getBelongTo() 
    {
        return belongTo;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setPublicId(Long publicId) 
    {
        this.publicId = publicId;
    }

    public Long getPublicId() 
    {
        return publicId;
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
            .append("transferId", getTransferId())
            .append("businessId", getBusinessId())
            .append("businessType", getBusinessType())
            .append("transferType", getTransferType())
            .append("transferTo", getTransferTo())
            .append("belongTo", getBelongTo())
            .append("reason", getReason())
            .append("publicId", getPublicId())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
