package com.jeethink.crm.domain;

import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 日志管理对象 crm_daily
 * 
 * @author jeethink
 * @date 2020-03-15
 */
public class Crm2Daily extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long dailyId;

    /** 类别 */
    @Excel(name = "类别")
    private String dailyType;

    /** 内容 */
    @Excel(name = "内容")
    private String dailyContent;

    /** 日志附件（备用） */
    private String dailyFile;

    /** 日志抄送人（备用） */
    private String dailyTo;

    /** 删除标识 */
    private String delFlag;

    public void setDailyId(Long dailyId) 
    {
        this.dailyId = dailyId;
    }

    public Long getDailyId() 
    {
        return dailyId;
    }
    public void setDailyType(String dailyType) 
    {
        this.dailyType = dailyType;
    }

    public String getDailyType() 
    {
        return dailyType;
    }
    public void setDailyContent(String dailyContent) 
    {
        this.dailyContent = dailyContent;
    }

    public String getDailyContent() 
    {
        return dailyContent;
    }
    public void setDailyFile(String dailyFile) 
    {
        this.dailyFile = dailyFile;
    }

    public String getDailyFile() 
    {
        return dailyFile;
    }
    public void setDailyTo(String dailyTo) 
    {
        this.dailyTo = dailyTo;
    }

    public String getDailyTo() 
    {
        return dailyTo;
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
            .append("dailyId", getDailyId())
            .append("dailyType", getDailyType())
            .append("dailyContent", getDailyContent())
            .append("dailyFile", getDailyFile())
            .append("dailyTo", getDailyTo())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
