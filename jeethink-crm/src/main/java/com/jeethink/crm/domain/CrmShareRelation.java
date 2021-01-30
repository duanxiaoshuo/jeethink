package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 crm_share_relation
 * 
 * @author jeethink
 * @date 2021-01-30
 */
public class CrmShareRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 共员工id */
    @Excel(name = "共员工id")
    private Long shareUserId;

    /** 被员工id */
    @Excel(name = "被员工id")
    private Long sharedUserId;

    /** 删除标识 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setShareUserId(Long shareUserId)
    {
        this.shareUserId = shareUserId;
    }

    public Long getShareUserId() 
    {
        return shareUserId;
    }
    public void setSharedUserId(Long sharedUserId) 
    {
        this.sharedUserId = sharedUserId;
    }

    public Long getSharedUserId() 
    {
        return sharedUserId;
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
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("shareUserId", getShareUserId())
            .append("sharedUserId", getSharedUserId())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
