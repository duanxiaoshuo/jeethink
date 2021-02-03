package com.jeethink.crm.domain;

import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.annotation.Excels;
import com.jeethink.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户订单、报价单关联产品信息对象 crm_order_product
 * 
 * @author jeethink
 * @date 2020-04-03
 */
public class Crm2OrderProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单产品ID */
    private Long orderProductId;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 产品id */
    @Excel(name = "产品id")
    private Long productId;
    
    /** 订单对象 */
    @Excels({
        @Excel(name = "订单主题", targetAttr = "orderName", type = Type.EXPORT),
        @Excel(name = "成交客户", targetAttr = "customer.customerName", type = Type.EXPORT),
    })
    private CrmOrder order;

	/** 订购数量 */
    @Excel(name = "订购数量")
    private Long productCount;

    /** 销售单价 */
    @Excel(name = "销售单价")
    private Double sellPrice;

    /** 折扣 */
    @Excel(name = "折扣")
    private Double discount;

    /** 实际报价 */
    @Excel(name = "实际价格")
    private Double realPrice;

    /** 订单产品状态（0：暂存  1：已存） */
    //@Excel(name = "订单产品状态", readConverterExp = "0=：暂存,1=：已存")
    private String orderProductStatus;

    /** 发货状态（未发货、已发货、已接收）)(备用) */
    //@Excel(name = "发货状态", readConverterExp = "未=发货、已发货、已接收")
    private String shipStatus;

    /** 发货单号(已发货产品使用)(备用) */
   // @Excel(name = "发货单号(已发货产品使用)(备用)")
    private String shipNo;

    /** 删除标识 */
    private String delFlag;
    
	@Excel(name = "产品名称", targetAttr = "productName", type = Type.EXPORT)
    private WmsProduct product;
    
    //@Excel(name = "产品库存", targetAttr = "stock_num", type = Type.EXPORT)
    private WmsStock stock;
    
    public CrmOrder getOrder() {
		return order;
	}

	public void setOrder(CrmOrder order) {
		this.order = order;
	}

	public WmsProduct getProduct() {
		return product;
	}

	public void setProduct(WmsProduct product) {
		this.product = product;
	}

	public WmsStock getStock() {
		return stock;
	}

	public void setStock(WmsStock stock) {
		this.stock = stock;
	}

    public void setOrderProductId(Long orderProductId) 
    {
        this.orderProductId = orderProductId;
    }

    public Long getOrderProductId() 
    {
        return orderProductId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setProductCount(Long productCount) 
    {
        this.productCount = productCount;
    }

    public Long getProductCount() 
    {
        return productCount;
    }
    public void setSellPrice(Double salePrice) 
    {
        this.sellPrice = salePrice;
    }

    public Double getSellPrice() 
    {
        return sellPrice;
    }
    public void setDiscount(Double discount) 
    {
        this.discount = discount;
    }

    public Double getDiscount() 
    {
        return discount;
    }
    public void setRealPrice(Double realPrice) 
    {
        this.realPrice = realPrice;
    }

    public Double getRealPrice() 
    {
        return realPrice;
    }
    public void setOrderProductStatus(String orderProductStatus) 
    {
        this.orderProductStatus = orderProductStatus;
    }

    public String getOrderProductStatus() 
    {
        return orderProductStatus;
    }
    public void setShipStatus(String shipStatus) 
    {
        this.shipStatus = shipStatus;
    }

    public String getShipStatus() 
    {
        return shipStatus;
    }
    public void setShipNo(String shipNo) 
    {
        this.shipNo = shipNo;
    }

    public String getShipNo() 
    {
        return shipNo;
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
            .append("orderProductId", getOrderProductId())
            .append("orderId", getOrderId())
            .append("productId", getProductId())
            .append("productCount", getProductCount())
            .append("salePrice", getSellPrice())
            .append("discount", getDiscount())
            .append("realPrice", getRealPrice())
            .append("orderProductStatus", getOrderProductStatus())
            .append("shipStatus", getShipStatus())
            .append("shipNo", getShipNo())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}