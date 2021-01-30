package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 库存操作和产品对应对象 wms_stock_product
 * 
 * @author jeethink
 * @date 2020-08-21
 */
public class WmsStockProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long stockProductId;

    /** 操作ID  入库 出库 调拨  盘点等 */
    @Excel(name = "操作ID  入库 出库 调拨  盘点等")
    private Long opId;

    /** 产品ID */
    @Excel(name = "产品ID")
    private Long productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 产品数量或之前数量 */
    @Excel(name = "产品数量或之前数量")
    private Long productNum1;

    /** 产品数量或之后数量 */
    @Excel(name = "产品数量或之后数量")
    private Long productNum2;

    /** 类型（ 入库 出库 调拨  盘点等） */
    @Excel(name = "类型", readConverterExp = "入=库,出=库,调=拨,盘=点等")
    private String type;

    /** 删除标识 */
    private String delFlag;

    public void setStockProductId(Long stockProductId) 
    {
        this.stockProductId = stockProductId;
    }

    public Long getStockProductId() 
    {
        return stockProductId;
    }
    public void setOpId(Long opId) 
    {
        this.opId = opId;
    }

    public Long getOpId() 
    {
        return opId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setProductNum1(Long productNum1) 
    {
        this.productNum1 = productNum1;
    }

    public Long getProductNum1() 
    {
        return productNum1;
    }
    public void setProductNum2(Long productNum2) 
    {
        this.productNum2 = productNum2;
    }

    public Long getProductNum2() 
    {
        return productNum2;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
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
            .append("stockProductId", getStockProductId())
            .append("opId", getOpId())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("productNum1", getProductNum1())
            .append("productNum2", getProductNum2())
            .append("type", getType())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}