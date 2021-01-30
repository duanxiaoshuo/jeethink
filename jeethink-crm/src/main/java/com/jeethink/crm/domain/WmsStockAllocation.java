package com.jeethink.crm.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 调拨单对象 wms_stock_allocation
 * 
 * @author jeethink
 * @date 2020-08-12
 */
public class WmsStockAllocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 调拨ID */
    private Long allocationId;

    /** 调出仓库ID */
    @Excel(name = "调出仓库ID")
    private Long outStorageId;

    /** 调出仓库名称 */
    @Excel(name = "调出仓库名称")
    private String outStorageName;

    /** 调入仓库ID */
    @Excel(name = "调入仓库ID")
    private Long enterStorageId;

    /** 调入仓库名称 */
    @Excel(name = "调入仓库名称")
    private String enterStorageName;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 调拨日期 */
    @Excel(name = "调拨日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date allocationDate;

    /** 调拨状态（0  已保存   1:已提交   2：已审核） */
    @Excel(name = "调拨状态", readConverterExp = "0=,已=保存,1=:已提交,2=：已审核")
    private String allocationStatus;

    /** 调拨负责人 */
    @Excel(name = "调拨负责人")
    private String allocationBy;

    /** 删除标识 */
    private String delFlag;
    
    /** 产品列表 */
    private List<WmsStockProduct> products;

    public void setAllocationId(Long allocationId) 
    {
        this.allocationId = allocationId;
    }

    public Long getAllocationId() 
    {
        return allocationId;
    }
    public void setOutStorageId(Long outStorageId) 
    {
        this.outStorageId = outStorageId;
    }

    public Long getOutStorageId() 
    {
        return outStorageId;
    }
    public void setOutStorageName(String outStorageName) 
    {
        this.outStorageName = outStorageName;
    }

    public String getOutStorageName() 
    {
        return outStorageName;
    }
    public void setEnterStorageId(Long enterStorageId) 
    {
        this.enterStorageId = enterStorageId;
    }

    public Long getEnterStorageId() 
    {
        return enterStorageId;
    }
    public void setEnterStorageName(String enterStorageName) 
    {
        this.enterStorageName = enterStorageName;
    }

    public String getEnterStorageName() 
    {
        return enterStorageName;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setAllocationDate(Date allocationDate) 
    {
        this.allocationDate = allocationDate;
    }

    public Date getAllocationDate() 
    {
        return allocationDate;
    }
    public void setAllocationStatus(String allocationStatus) 
    {
        this.allocationStatus = allocationStatus;
    }

    public String getAllocationStatus() 
    {
        return allocationStatus;
    }
    public void setAllocationBy(String allocationBy) 
    {
        this.allocationBy = allocationBy;
    }

    public String getAllocationBy() 
    {
        return allocationBy;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public List<WmsStockProduct> getProducts() {
		return products;
	}

	public void setProducts(List<WmsStockProduct> products) {
		this.products = products;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("allocationId", getAllocationId())
            .append("outStorageId", getOutStorageId())
            .append("outStorageName", getOutStorageName())
            .append("enterStorageId", getEnterStorageId())
            .append("enterStorageName", getEnterStorageName())
            .append("title", getTitle())
            .append("allocationDate", getAllocationDate())
            .append("allocationStatus", getAllocationStatus())
            .append("allocationBy", getAllocationBy())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}