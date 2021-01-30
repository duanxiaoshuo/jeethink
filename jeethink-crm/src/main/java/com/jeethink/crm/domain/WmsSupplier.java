package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;

/**
 * 产品供应商对象 wms_supplier
 * 
 * @author jeethink
 * @date 2020-03-12
 */
public class WmsSupplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商ID */
    private Long supplierId;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 供应商简称 */
    @Excel(name = "供应商简称")
    private String nameShort;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    /** 职务 */
    @Excel(name = "职务")
    private String duty;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 固定电话 */
    @Excel(name = "固定电话")
    private String telephone;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 传值 */
    @Excel(name = "传值")
    private String fax;

    /** 网址 */
    @Excel(name = "网址")
    private String website;

    /** 邮编 */
    private String zip;

    /** 国家 */
    private String country;

    /** 省份 */
    private String province;

    /** 城市 */
    private String city;

    /** 地区 */
    private String region;

    /** 所属行业 */
    @Excel(name = "所属行业")
    private String industry;

    /** 供应商I状态（未供货、已供货） */
    @Excel(name = "供应商I状态", readConverterExp = "未=供货、已供货")
    private String supplierStatus;

    /** 供应商等级（优质、中等、一般、、、) */
    @Excel(name = "供应商等级", readConverterExp = "供应商等级（优质、中等、一般、、、)")
    private String supplierDegree;

    /** 供应商信誉度（字典 分星展示） */
    @Excel(name = "供应商信誉度", readConverterExp = "字=典,分=星展示")
    private String supplierCredit;

    /** 性别（保留） */
    private String sex;

    /** 头像（保留） */
    private String avatar;

    /** qq（保留） */
    private String qq;

    /** 删除标识 */
    private String delFlag;

    public void setSupplierId(Long supplierId) 
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId() 
    {
        return supplierId;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setNameShort(String nameShort) 
    {
        this.nameShort = nameShort;
    }

    public String getNameShort() 
    {
        return nameShort;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setDuty(String duty) 
    {
        this.duty = duty;
    }

    public String getDuty() 
    {
        return duty;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
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
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setSupplierStatus(String supplierStatus) 
    {
        this.supplierStatus = supplierStatus;
    }

    public String getSupplierStatus() 
    {
        return supplierStatus;
    }
    public void setSupplierDegree(String supplierDegree) 
    {
        this.supplierDegree = supplierDegree;
    }

    public String getSupplierDegree() 
    {
        return supplierDegree;
    }
    public void setSupplierCredit(String supplierCredit) 
    {
        this.supplierCredit = supplierCredit;
    }

    public String getSupplierCredit() 
    {
        return supplierCredit;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
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
            .append("supplierId", getSupplierId())
            .append("supplierName", getSupplierName())
            .append("nameShort", getNameShort())
            .append("leader", getLeader())
            .append("duty", getDuty())
            .append("address", getAddress())
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
            .append("industry", getIndustry())
            .append("supplierStatus", getSupplierStatus())
            .append("supplierDegree", getSupplierDegree())
            .append("supplierCredit", getSupplierCredit())
            .append("sex", getSex())
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
