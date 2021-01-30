package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 客户拜访对象 crm_visit
 * 
 * @author jeethink
 * @date 2020-03-10
 */
public class CrmVisit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 拜访ID */
    private Long visitId;

    /** 客户id */
    private Long customerId;
    
    @Excel(name = "所属客户", targetAttr = "customerName", type = Type.EXPORT)
    private CrmCustomer customer;

    /** 拜访人姓名 */
    @Excel(name = "拜访人姓名")
    private String visitName;

    /** 拜访人电话 */
    @Excel(name = "拜访人电话")
    private String visitPhone;

    /** 拜访内容 */
    @Excel(name = "拜访内容")
    private String visitContent;

    /** 拜访时间 */
    @Excel(name = "拜访时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date visitDate;

    /** 下次拜访时间（保留） */
    private String nextTime;

    /** 拜访类型（保留） */
    private String visitType;

    /** 拜访状态 */
    @Excel(name = "拜访状态")
    private String visitStatus;

    /** 删除标识 */
    private String delFlag;

    public void setVisitId(Long visitId) 
    {
        this.visitId = visitId;
    }

    public Long getVisitId() 
    {
        return visitId;
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

	public void setVisitName(String visitName) 
    {
        this.visitName = visitName;
    }

    public String getVisitName() 
    {
        return visitName;
    }
    public void setVisitPhone(String visitPhone) 
    {
        this.visitPhone = visitPhone;
    }

    public String getVisitPhone() 
    {
        return visitPhone;
    }
    public void setVisitContent(String visitContent) 
    {
        this.visitContent = visitContent;
    }

    public String getVisitContent() 
    {
        return visitContent;
    }
    public void setVisitDate(Date visitDate) 
    {
        this.visitDate = visitDate;
    }

    public Date getVisitDate() 
    {
        return visitDate;
    }
    public void setNextTime(String nextTime) 
    {
        this.nextTime = nextTime;
    }

    public String getNextTime() 
    {
        return nextTime;
    }
    public void setVisitType(String visitType) 
    {
        this.visitType = visitType;
    }

    public String getVisitType() 
    {
        return visitType;
    }
    public void setVisitStatus(String visitStatus) 
    {
        this.visitStatus = visitStatus;
    }

    public String getVisitStatus() 
    {
        return visitStatus;
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
            .append("visitId", getVisitId())
            .append("customerId", getCustomerId())
            .append("visitName", getVisitName())
            .append("visitPhone", getVisitPhone())
            .append("visitContent", getVisitContent())
            .append("visitDate", getVisitDate())
            .append("nextTime", getNextTime())
            .append("visitType", getVisitType())
            .append("visitStatus", getVisitStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
