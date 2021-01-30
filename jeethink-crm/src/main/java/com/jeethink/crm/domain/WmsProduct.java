package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 产品对象 wms_product
 * 
 * @author jeethink
 * @date 2020-03-12
 */
public class WmsProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品ID */
    private Long productId;

    /** 供货商 */
    @Excel(name = "供货商")
    private Long supplierId;
    
    /** 产品分类id */
    private Long productCatogoryId;
    
    @Excel(name = "产品分类", targetAttr = "catogoryName", type = Type.EXPORT)
    private WmsProductCatogory wmsProductCatogory;
    
    @Excel(name = "供应商名称", targetAttr = "supplierName", type = Type.EXPORT)
    private WmsSupplier supplier;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 进价 */
    @Excel(name = "进价")
    private Long incomePrice;

    /** 售价 */
    @Excel(name = "售价")
    private Long salePrice;

    /** 产品类别 */
    @Excel(name = "产品类别")
    private String productCategory;

    /** 货号 */
    @Excel(name = "货号")
    private String productNo;

    /** 产品计量单位 */
    @Excel(name = "产品计量单位")
    private String productUnit;

    /** 产品规格(保留，暂不使用多规格) */
    private String productSpec;

    /** 产品状态 */
    @Excel(name = "产品状态")
    private String prodcutStatus;

    /** 删除标识 */
    private String delFlag;
    
    /** 产品图片 */
    private String productImage;

    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }    
    
    public Long getProductCatogoryId() {
		return productCatogoryId;
	}

	public void setProductCatogoryId(Long productCatogoryId) {
		this.productCatogoryId = productCatogoryId;
	}

	public WmsProductCatogory getWmsProductCatogory() {
		return wmsProductCatogory;
	}

	public void setWmsProductCatogory(WmsProductCatogory wmsProductCategory) {
		this.wmsProductCatogory = wmsProductCategory;
	}

	public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public WmsSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(WmsSupplier supplier) {
		this.supplier = supplier;
	}

	public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setIncomePrice(Long incomePrice) 
    {
        this.incomePrice = incomePrice;
    }

    public Long getIncomePrice() 
    {
        return incomePrice;
    }
    public void setSalePrice(Long salePrice) 
    {
        this.salePrice = salePrice;
    }

    public Long getSalePrice() 
    {
        return salePrice;
    }
    public void setProductCategory(String productCategory) 
    {
        this.productCategory = productCategory;
    }

    public String getProductCategory() 
    {
        return productCategory;
    }
    public void setProductNo(String productNo) 
    {
        this.productNo = productNo;
    }

    public String getProductNo() 
    {
        return productNo;
    }
    public void setProductUnit(String productUnit) 
    {
        this.productUnit = productUnit;
    }

    public String getProductUnit() 
    {
        return productUnit;
    }
    public void setProductSpec(String productSpec) 
    {
        this.productSpec = productSpec;
    }

    public String getProductSpec() 
    {
        return productSpec;
    }
    public void setProdcutStatus(String prodcutStatus) 
    {
        this.prodcutStatus = prodcutStatus;
    }

    public String getProdcutStatus() 
    {
        return prodcutStatus;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    
    public void setProductImage(String productImage) 
    {
        this.productImage = productImage;
    }

    public String getProductImage() 
    {
        return productImage;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productId", getProductId())
            .append("productCatogoryId", getProductCatogoryId())
            .append("supplierId", getSupplierId())
            .append("productName", getProductName())
            .append("incomePrice", getIncomePrice())
            .append("salePrice", getSalePrice())
            .append("productCategory", getProductCategory())
            .append("productNo", getProductNo())
            .append("productUnit", getProductUnit())
            .append("productSpec", getProductSpec())
            .append("prodcutStatus", getProdcutStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("productImage", getProductImage())
            .toString();
    }
}
