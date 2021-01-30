package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 客户跟进记录对象 crm_follow
 * 
 * @author jeethink
 * @date 2020-03-10
 */
public class CrmFollow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 跟进ID */
    private Long followId;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    @Excel(name = "所属客户", targetAttr = "customerName", type = Type.EXPORT)
    private CrmCustomer customer;
    
    /** 跟进时间 */
    @Excel(name = "跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date followDate;

    /** 跟进内容 */
    @Excel(name = "跟进内容")
    private String followContent;

    /** 跟进类型 */
    @Excel(name = "跟进类型")
    private String followType;

    /** 跟进状态 */
    @Excel(name = "跟进状态")
    private String followStatus;

    /** 删除标识 */
    private String delFlag;

    public void setFollowId(Long followId) 
    {
        this.followId = followId;
    }

    public Long getFollowId() 
    {
        return followId;
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

	public void setFollowDate(Date followDate) 
    {
        this.followDate = followDate;
    }

    public Date getFollowDate() 
    {
        return followDate;
    }
    public void setFollowContent(String followContent) 
    {
        this.followContent = followContent;
    }

    public String getFollowContent() 
    {
        return followContent;
    }
    public void setFollowType(String followType) 
    {
        this.followType = followType;
    }

    public String getFollowType() 
    {
        return followType;
    }
    public void setFollowStatus(String followStatus) 
    {
        this.followStatus = followStatus;
    }

    public String getFollowStatus() 
    {
        return followStatus;
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
            .append("followId", getFollowId())
            .append("customerId", getCustomerId())
            .append("followDate", getFollowDate())
            .append("followContent", getFollowContent())
            .append("followType", getFollowType())
            .append("followStatus", getFollowStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
