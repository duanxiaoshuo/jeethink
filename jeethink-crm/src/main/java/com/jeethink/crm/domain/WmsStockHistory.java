package com.jeethink.crm.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 库存记录对象 wms_stock_history
 * 
 * @author jeethink
 * @date 2020-08-13
 */
public class WmsStockHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long historyId;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long storageId;

    /** 产品ID */
    @Excel(name = "产品ID")
    private Long productId;
    
    @Excel(name = "产品名称", targetAttr = "productName", type = Type.EXPORT)
    private WmsProduct product;
    
    @Excel(name = "仓库名称", targetAttr = "storageName", type = Type.EXPORT)
    private WmsStorage storage;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private String historyType;

    /** 入库/出库数量 */
    @Excel(name = "入库/出库数量")
    private Long numBefore;

    /** 仓库剩余数量 */
    @Excel(name = "仓库剩余数量")
    private Long numAfter;

    /** 入库/出库日期 */
    @Excel(name = "入库/出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date historyDate;

    /** 负责人 */
    @Excel(name = "负责人")
    private String historyBy;

    /** 删除标识 */
    private String delFlag;

    public void setHistoryId(Long historyId) 
    {
        this.historyId = historyId;
    }

    public Long getHistoryId() 
    {
        return historyId;
    }
    public void setStorageId(Long storageId) 
    {
        this.storageId = storageId;
    }

    public Long getStorageId() 
    {
        return storageId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    
    public WmsProduct getProduct() {
		return product;
	}

	public void setProduct(WmsProduct product) {
		this.product = product;
	}

	public WmsStorage getStorage() {
		return storage;
	}

	public void setStorage(WmsStorage storage) {
		this.storage = storage;
	}

	public void setHistoryType(String historyType) 
    {
        this.historyType = historyType;
    }

    public String getHistoryType() 
    {
        return historyType;
    }
    public void setNumBefore(Long numBefore) 
    {
        this.numBefore = numBefore;
    }

    public Long getNumBefore() 
    {
        return numBefore;
    }
    public void setNumAfter(Long numAfter) 
    {
        this.numAfter = numAfter;
    }

    public Long getNumAfter() 
    {
        return numAfter;
    }
    public void setHistoryDate(Date historyDate) 
    {
        this.historyDate = historyDate;
    }

    public Date getHistoryDate() 
    {
        return historyDate;
    }
    public void setHistoryBy(String historyBy) 
    {
        this.historyBy = historyBy;
    }

    public String getHistoryBy() 
    {
        return historyBy;
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
            .append("historyId", getHistoryId())
            .append("storageId", getStorageId())
            .append("productId", getProductId())
            .append("historyType", getHistoryType())
            .append("numBefore", getNumBefore())
            .append("numAfter", getNumAfter())
            .append("historyDate", getHistoryDate())
            .append("historyBy", getHistoryBy())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
