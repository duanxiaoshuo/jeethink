package com.jeethink.crm.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 客户投诉对象 crm2_complaint
 * 
 * @author jeethink
 * @date 2021-01-24
 */
public class Crm2Complaint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 投诉ID */
    private Long complaintId;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 投诉时间 */
    @Excel(name = "投诉时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date complaintDate;

    /** 投诉人姓名 */
    @Excel(name = "投诉人姓名")
    private String complaintName;

    /** 投诉人电话 */
    @Excel(name = "投诉人电话")
    private String complaintPhone;

    /** 投诉内容 */
    @Excel(name = "投诉内容")
    private String complaintContent;

    /** 投诉类型（产品投诉、服务投诉、） */
    @Excel(name = "投诉类型", readConverterExp = "产=品投诉、服务投诉、")
    private String complaintType;

    /** 投诉状态（0：未解决 1：解决中 2：已解决） */
    @Excel(name = "投诉状态", readConverterExp = "0=：未解决,1=：解决中,2=：已解决")
    private String complaintStatus;

    /** 删除标识 */
    private String delFlag;

    public void setComplaintId(Long complaintId) 
    {
        this.complaintId = complaintId;
    }

    public Long getComplaintId() 
    {
        return complaintId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setComplaintDate(Date complaintDate) 
    {
        this.complaintDate = complaintDate;
    }

    public Date getComplaintDate() 
    {
        return complaintDate;
    }
    public void setComplaintName(String complaintName) 
    {
        this.complaintName = complaintName;
    }

    public String getComplaintName() 
    {
        return complaintName;
    }
    public void setComplaintPhone(String complaintPhone) 
    {
        this.complaintPhone = complaintPhone;
    }

    public String getComplaintPhone() 
    {
        return complaintPhone;
    }
    public void setComplaintContent(String complaintContent) 
    {
        this.complaintContent = complaintContent;
    }

    public String getComplaintContent() 
    {
        return complaintContent;
    }
    public void setComplaintType(String complaintType) 
    {
        this.complaintType = complaintType;
    }

    public String getComplaintType() 
    {
        return complaintType;
    }
    public void setComplaintStatus(String complaintStatus) 
    {
        this.complaintStatus = complaintStatus;
    }

    public String getComplaintStatus() 
    {
        return complaintStatus;
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
            .append("complaintId", getComplaintId())
            .append("customerId", getCustomerId())
            .append("complaintDate", getComplaintDate())
            .append("complaintName", getComplaintName())
            .append("complaintPhone", getComplaintPhone())
            .append("complaintContent", getComplaintContent())
            .append("complaintType", getComplaintType())
            .append("complaintStatus", getComplaintStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
