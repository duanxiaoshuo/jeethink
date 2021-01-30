package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 库存管理对象 wms_stock
 * 
 * @author jeethink
 * @date 2020-03-15
 */
public class WmsStock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 库存ID */
    private Long stockId;   
    
    /** 仓库ID */
    private Long storageId;

    /** 产品ID */
    @Excel(name = "产品ID")
    private Long productId;
    
    @Excel(name = "产品名称", targetAttr = "productName", type = Type.EXPORT)
    private WmsProduct product;
    
    @Excel(name = "仓库名称", targetAttr = "storageName", type = Type.EXPORT)
    private WmsStorage storage;

    public WmsStorage getStorage() {
		return storage;
	}

	public void setStorage(WmsStorage storage) {
		this.storage = storage;
	}

		/** 库存数量 */
    @Excel(name = "库存数量")
    private Long stockNum;

    /** 删除标识 */
    private String delFlag;

    public void setStockId(Long stockId) 
    {
        this.stockId = stockId;
    }

    public Long getStockId() 
    {
        return stockId;
    }
    
    public Long getStorageId() {
		return storageId;
	}

	public void setStorageId(Long storageId) {
		this.storageId = storageId;
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

	public void setStockNum(Long stockNum) 
    {
        this.stockNum = stockNum;
    }

    public Long getStockNum() 
    {
        return stockNum;
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
            .append("stockId", getStockId())
            .append("productId", getProductId())
            .append("stockNum", getStockNum())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
