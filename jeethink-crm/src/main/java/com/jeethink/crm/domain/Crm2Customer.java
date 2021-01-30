package com.jeethink.crm.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 客户对象 crm2_customer
 * 
 * @author jeethink
 * @date 2021-01-24
 */
public class Crm2Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private Long customerId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 客户简称 */
    @Excel(name = "客户简称")
    private String nameShort;

    /** 公司座机 */
    @Excel(name = "公司座机")
    private String telephone;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 传值 */
    @Excel(name = "传值")
    private String fax;

    /** 网址 */
    @Excel(name = "网址")
    private String website;

    /** 邮编 */
    @Excel(name = "邮编")
    private String zip;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 地区 */
    @Excel(name = "地区")
    private String region;

    /** 客户地址 */
    @Excel(name = "客户地址")
    private String address;

    /** 意向产品（后期应可以选择） */
    @Excel(name = "意向产品", readConverterExp = "后=期应可以选择")
    private String intrestedProdcut;

    /** 所属行业（字典型） */
    @Excel(name = "所属行业", readConverterExp = "字=典型")
    private String industry;

    /** 客户状态（新开发、洽谈中、已成交） */
    @Excel(name = "客户状态", readConverterExp = "新=开发、洽谈中、已成交")
    private String customerStatus;

    /** 客户来源(字典类型) */
    @Excel(name = "客户来源(字典类型)")
    private String customerSource;

    /** 客户等级（字典类型) */
    @Excel(name = "客户等级", readConverterExp = "客户等级（字典类型)")
    private String customerDegree;

    /** 客户优先级（字典类型) */
    @Excel(name = "客户优先级", readConverterExp = "客户优先级（字典类型)")
    private String customerPriority;

    /** 客户信誉度（字典 分星展示） */
    @Excel(name = "客户信誉度", readConverterExp = "字=典,分=星展示")
    private String customerCredit;

    /** 客户类型（0  个人、1  企业） */
    @Excel(name = "客户类型", readConverterExp = "0=,个=人、1,企=业")
    private String customerType;

    /** 客户成熟度 A+ A- B+ B-  新加 */
    @Excel(name = "客户成熟度 A+ A- B+ B-  新加")
    private String customerMaturity;

    /** 客户类别(普通，vip ，代理商等) */
    @Excel(name = "客户类别(普通，vip ，代理商等)")
    private String customerCategory;

    /** 年收入 */
    @Excel(name = "年收入")
    private String annualIncome;

    /** 客户获取时间 */
    @Excel(name = "客户获取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date getDate;

    /** 原负责人(login_name) */
    @Excel(name = "原负责人(login_name)")
    private String sourceBelongTo;

    /** 负责人(login_name) */
    @Excel(name = "负责人(login_name)")
    private String belongTo;

    /** 转交时间 */
    @Excel(name = "转交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trasferDate;

    /** 转交人(login_name) */
    @Excel(name = "转交人(login_name)")
    private String trasferTo;

    /** 是否共享客户 */
    @Excel(name = "是否共享客户")
    private String isShare;

    /** 共享时间 */
    @Excel(name = "共享时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shareDate;

    /** 头像（保留） */
    @Excel(name = "头像", readConverterExp = "保=留")
    private String avatar;

    /** qq（保留） */
    @Excel(name = "qq", readConverterExp = "保=留")
    private String qq;

    /** 删除标识 */
    private String delFlag;

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setNameShort(String nameShort) 
    {
        this.nameShort = nameShort;
    }

    public String getNameShort() 
    {
        return nameShort;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setFax(String fax) 
    {
        this.fax = fax;
    }

    public String getFax() 
    {
        return fax;
    }
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }
    public void setZip(String zip) 
    {
        this.zip = zip;
    }

    public String getZip() 
    {
        return zip;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setRegion(String region) 
    {
        this.region = region;
    }

    public String getRegion() 
    {
        return region;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setIntrestedProdcut(String intrestedProdcut) 
    {
        this.intrestedProdcut = intrestedProdcut;
    }

    public String getIntrestedProdcut() 
    {
        return intrestedProdcut;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setCustomerStatus(String customerStatus) 
    {
        this.customerStatus = customerStatus;
    }

    public String getCustomerStatus() 
    {
        return customerStatus;
    }
    public void setCustomerSource(String customerSource) 
    {
        this.customerSource = customerSource;
    }

    public String getCustomerSource() 
    {
        return customerSource;
    }
    public void setCustomerDegree(String customerDegree) 
    {
        this.customerDegree = customerDegree;
    }

    public String getCustomerDegree() 
    {
        return customerDegree;
    }
    public void setCustomerPriority(String customerPriority) 
    {
        this.customerPriority = customerPriority;
    }

    public String getCustomerPriority() 
    {
        return customerPriority;
    }
    public void setCustomerCredit(String customerCredit) 
    {
        this.customerCredit = customerCredit;
    }

    public String getCustomerCredit() 
    {
        return customerCredit;
    }
    public void setCustomerType(String customerType) 
    {
        this.customerType = customerType;
    }

    public String getCustomerType() 
    {
        return customerType;
    }
    public void setCustomerMaturity(String customerMaturity) 
    {
        this.customerMaturity = customerMaturity;
    }

    public String getCustomerMaturity() 
    {
        return customerMaturity;
    }
    public void setCustomerCategory(String customerCategory) 
    {
        this.customerCategory = customerCategory;
    }

    public String getCustomerCategory() 
    {
        return customerCategory;
    }
    public void setAnnualIncome(String annualIncome) 
    {
        this.annualIncome = annualIncome;
    }

    public String getAnnualIncome() 
    {
        return annualIncome;
    }
    public void setGetDate(Date getDate) 
    {
        this.getDate = getDate;
    }

    public Date getGetDate() 
    {
        return getDate;
    }
    public void setSourceBelongTo(String sourceBelongTo) 
    {
        this.sourceBelongTo = sourceBelongTo;
    }

    public String getSourceBelongTo() 
    {
        return sourceBelongTo;
    }
    public void setBelongTo(String belongTo) 
    {
        this.belongTo = belongTo;
    }

    public String getBelongTo() 
    {
        return belongTo;
    }
    public void setTrasferDate(Date trasferDate) 
    {
        this.trasferDate = trasferDate;
    }

    public Date getTrasferDate() 
    {
        return trasferDate;
    }
    public void setTrasferTo(String trasferTo) 
    {
        this.trasferTo = trasferTo;
    }

    public String getTrasferTo() 
    {
        return trasferTo;
    }
    public void setIsShare(String isShare) 
    {
        this.isShare = isShare;
    }

    public String getIsShare() 
    {
        return isShare;
    }
    public void setShareDate(Date shareDate) 
    {
        this.shareDate = shareDate;
    }

    public Date getShareDate() 
    {
        return shareDate;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setQq(String qq) 
    {
        this.qq = qq;
    }

    public String getQq() 
    {
        return qq;
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
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("nameShort", getNameShort())
            .append("telephone", getTelephone())
            .append("mobile", getMobile())
            .append("email", getEmail())
            .append("fax", getFax())
            .append("website", getWebsite())
            .append("zip", getZip())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("region", getRegion())
            .append("address", getAddress())
            .append("intrestedProdcut", getIntrestedProdcut())
            .append("industry", getIndustry())
            .append("customerStatus", getCustomerStatus())
            .append("customerSource", getCustomerSource())
            .append("customerDegree", getCustomerDegree())
            .append("customerPriority", getCustomerPriority())
            .append("customerCredit", getCustomerCredit())
            .append("customerType", getCustomerType())
            .append("customerMaturity", getCustomerMaturity())
            .append("customerCategory", getCustomerCategory())
            .append("annualIncome", getAnnualIncome())
            .append("getDate", getGetDate())
            .append("sourceBelongTo", getSourceBelongTo())
            .append("belongTo", getBelongTo())
            .append("trasferDate", getTrasferDate())
            .append("trasferTo", getTrasferTo())
            .append("isShare", getIsShare())
            .append("shareDate", getShareDate())
            .append("avatar", getAvatar())
            .append("qq", getQq())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
