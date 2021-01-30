package com.jeethink.crm.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 库存盘点对象 wms_stock_check
 * 
 * @author jeethink
 * @date 2020-08-14
 */
public class WmsStockCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 盘点ID */
    private Long checkId;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long storageId;
    
    @Excel(name = "仓库名称", targetAttr = "storageName", type = Type.EXPORT)
    private WmsStorage storage;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 盘点日期 */
    @Excel(name = "盘点日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;

    /** 盘点负责人 */
    @Excel(name = "盘点负责人")
    private String checkBy;

    /** 盘点状态 */
    @Excel(name = "盘点状态")
    private String checkStatus;

    /** 删除标识 */
    private String delFlag;
    
    /** 产品列表 */
    private List<WmsStockProduct> products;

    public void setCheckId(Long checkId) 
    {
        this.checkId = checkId;
    }

    public Long getCheckId() 
    {
        return checkId;
    }
    public void setStorageId(Long storageId) 
    {
        this.storageId = storageId;
    }

    public Long getStorageId() 
    {
        return storageId;
    }

	public WmsStorage getStorage() {
		return storage;
	}

	public void setStorage(WmsStorage storage) {
		this.storage = storage;
	}

	public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setCheckDate(Date checkDate) 
    {
        this.checkDate = checkDate;
    }

    public Date getCheckDate() 
    {
        return checkDate;
    }
    public void setCheckBy(String checkBy) 
    {
        this.checkBy = checkBy;
    }

    public String getCheckBy() 
    {
        return checkBy;
    }
    public void setCheckStatus(String checkStatus) 
    {
        this.checkStatus = checkStatus;
    }

    public String getCheckStatus() 
    {
        return checkStatus;
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
            .append("checkId", getCheckId())
            .append("storageId", getStorageId())
            .append("title", getTitle())
            .append("checkDate", getCheckDate())
            .append("checkBy", getCheckBy())
            .append("checkStatus", getCheckStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
