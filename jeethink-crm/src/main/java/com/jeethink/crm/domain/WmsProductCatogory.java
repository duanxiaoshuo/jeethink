package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.TreeEntity;

/**
 * 产品分类管理对象 wms_product_catogory
 * 
 * @author jeethink
 * @date 2020-04-30
 */
public class WmsProductCatogory extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品分类id */
    private Long productCatogoryId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String catogoryName;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 分类状态:0正常,1停用 */
    @Excel(name = "分类状态:0正常,1停用")
    private String status;

    /** 删除标识 */
    private String delFlag;

    public void setProductCatogoryId(Long productCatogoryId) 
    {
        this.productCatogoryId = productCatogoryId;
    }

    public Long getProductCatogoryId() 
    {
        return productCatogoryId;
    }
    public void setCatogoryName(String catogoryName) 
    {
        this.catogoryName = catogoryName;
    }

    public String getCatogoryName() 
    {
        return catogoryName;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("productCatogoryId", getProductCatogoryId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("catogoryName", getCatogoryName())
            .append("orderNum", getOrderNum())
            .append("leader", getLeader())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
