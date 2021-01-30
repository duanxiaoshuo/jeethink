package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 入库管理对象 wms_stock_enter
 * 
 * @author jeethink
 * @date 2020-03-16
 */
public class WmsStockEnter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 入库ID */
    private Long enterId;  
    
    /** 仓库ID */
    private Long storageId; 
    
    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 入库日期 */
    @Excel(name = "入库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enterDate;

    /** 入库类型 */
    @Excel(name = "入库类型")
    private String enterType;    

    @Excel(name = "入库仓库", targetAttr = "storageName", type = Type.EXPORT)
    private WmsStorage storage;

    /** 入库状态 */
    @Excel(name = "入库状态")
    private String enterStatus;

    /** 负责人 */
    @Excel(name = "负责人")
    private String enterBy;

    /** 删除标识 */
    private String delFlag;
    
    /** 产品列表 */
    private List<WmsStockProduct> products;

    public void setEnterId(Long enterId) 
    {
        this.enterId = enterId;
    }

    public Long getEnterId() 
    {
        return enterId;
    }
    
    public Long getStorageId() {
		return storageId;
	}

	public void setStorageId(Long storageId) {
		this.storageId = storageId;
	}
	
    public void setEnterDate(Date enterDate) 
    {
        this.enterDate = enterDate;
    }

    public Date getEnterDate() 
    {
        return enterDate;
    }
    public void setEnterType(String enterType) 
    {
        this.enterType = enterType;
    }

    public String getEnterType() 
    {
        return enterType;
    }

	public WmsStorage getStorage() {
		return storage;
	}

	public void setStorage(WmsStorage storage) {
		this.storage = storage;
	}

	public void setEnterStatus(String enterStatus) 
    {
        this.enterStatus = enterStatus;
    }

    public String getEnterStatus() 
    {
        return enterStatus;
    }
    public void setEnterBy(String enterBy) 
    {
        this.enterBy = enterBy;
    }

    public String getEnterBy() 
    {
        return enterBy;
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
                .append("enterId", getEnterId())
                .append("storageId", getStorageId())
                .append("title", getTitle())
                .append("enterDate", getEnterDate())
                .append("enterType", getEnterType())
                .append("enterStatus", getEnterStatus())
                .append("enterBy", getEnterBy())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
            .toString();
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
