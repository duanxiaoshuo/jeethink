package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 出库管理对象 wms_stock_out
 * 
 * @author jeethink
 * @date 2020-03-16
 */
public class WmsStockOut extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出库ID */
    private Long outId;
    
    /** 仓库ID */
    private Long storageId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 出库日期 */
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date outDate;

    /** 出库类型 */
    @Excel(name = "出库类型")
    private String outType;

    @Excel(name = "出库仓库", targetAttr = "storageName", type = Type.EXPORT)
    private WmsStorage storage;

    /** 出库状态 */
    private String outStatus;

    /** 出库申请人 */
    @Excel(name = "出库申请人")
    private String outBy;

    /** 删除标识 */
    private String delFlag;
    
    /** 产品列表 */
    private List<WmsStockProduct> products;

    public void setOutId(Long outId) 
    {
        this.outId = outId;
    }

    public Long getOutId() 
    {
        return outId;
    }
    
    public Long getStorageId() {
		return storageId;
	}

	public void setStorageId(Long storageId) {
		this.storageId = storageId;
	}
	
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOutDate(Date outDate) 
    {
        this.outDate = outDate;
    }

    public Date getOutDate() 
    {
        return outDate;
    }
    public void setOutType(String outType) 
    {
        this.outType = outType;
    }

    public String getOutType() 
    {
        return outType;
    }
    

	public WmsStorage getStorage() {
		return storage;
	}

	public void setStorage(WmsStorage storage) {
		this.storage = storage;
	}

	public void setOutStatus(String outStatus) 
    {
        this.outStatus = outStatus;
    }

    public String getOutStatus() 
    {
        return outStatus;
    }
    public void setOutBy(String outBy) 
    {
        this.outBy = outBy;
    }

    public String getOutBy() 
    {
        return outBy;
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
                .append("outId", getOutId())
                .append("storageId", getStorageId())
                .append("title", getTitle())
                .append("outDate", getOutDate())
                .append("outType", getOutType())
                .append("outStatus", getOutStatus())
                .append("outBy", getOutBy())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
            .toString();
    }
}
