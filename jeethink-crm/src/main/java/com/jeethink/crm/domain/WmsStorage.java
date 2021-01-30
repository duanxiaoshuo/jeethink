package com.jeethink.crm.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 仓库对象 wms_storage
 * 
 * @author jeethink
 * @date 2020-07-29
 */
public class WmsStorage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 仓库ID */
    private Long storageId;

    /** 仓库编码 */
    @Excel(name = "仓库编码")
    private String storageNo;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String storageName;

    /** 仓库状态 */
    @Excel(name = "仓库状态")
    private String storageStatus;

    /** 仓库地址 */
    @Excel(name = "仓库地址")
    private String storageAddress;

    /** 负责人 */
    @Excel(name = "负责人")
    private String belongTo;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 手机 */
    private String mobile;

    /** 图片 */
    private String image;

    /** 删除标识 */
    private String delFlag;

    public void setStorageId(Long storageId) 
    {
        this.storageId = storageId;
    }

    public Long getStorageId() 
    {
        return storageId;
    }
    public void setStorageNo(String storageNo) 
    {
        this.storageNo = storageNo;
    }

    public String getStorageNo() 
    {
        return storageNo;
    }
    public void setStorageName(String storageName) 
    {
        this.storageName = storageName;
    }

    public String getStorageName() 
    {
        return storageName;
    }
    public void setStorageStatus(String storageStatus) 
    {
        this.storageStatus = storageStatus;
    }

    public String getStorageStatus() 
    {
        return storageStatus;
    }
    public void setStorageAddress(String storageAddress) 
    {
        this.storageAddress = storageAddress;
    }

    public String getStorageAddress() 
    {
        return storageAddress;
    }
    public void setBelongTo(String belongTo) 
    {
        this.belongTo = belongTo;
    }

    public String getBelongTo() 
    {
        return belongTo;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
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
            .append("storageId", getStorageId())
            .append("storageNo", getStorageNo())
            .append("storageName", getStorageName())
            .append("storageStatus", getStorageStatus())
            .append("storageAddress", getStorageAddress())
            .append("belongTo", getBelongTo())
            .append("phone", getPhone())
            .append("mobile", getMobile())
            .append("image", getImage())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
