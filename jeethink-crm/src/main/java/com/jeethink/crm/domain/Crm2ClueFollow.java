package com.jeethink.crm.domain;

import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 线索跟进记录对象 crm_clue_follow
 * 
 * @author jeethink
 * @date 2020-09-2
 */
public class Crm2ClueFollow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 跟进ID */
    private Long followId;

    /** 线索id */
    @Excel(name = "线索id")
    private Long clueId;

    @Excel(name = "所属线索", targetAttr = "name", type = Type.EXPORT)
    private CrmClue clue;
    
    /** 跟进时间 */
    @Excel(name = "跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date followDate;

    /** 跟进内容 */
    @Excel(name = "跟进内容")
    private String followContent;

    /** 跟进类型 */
    @Excel(name = "跟进类型")
    private String followType;

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
    public void setClueId(Long clueId) 
    {
        this.clueId = clueId;
    }

    public Long getClueId() 
    {
        return clueId;
    }

	public CrmClue getClue() {
		return clue;
	}

	public void setClue(CrmClue clue) {
		this.clue = clue;
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
            .append("clueId", getClueId())
            .append("followDate", getFollowDate())
            .append("followContent", getFollowContent())
            .append("followType", getFollowType())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
