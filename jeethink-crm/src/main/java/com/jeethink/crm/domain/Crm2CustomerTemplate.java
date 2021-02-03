package com.jeethink.crm.domain;

import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;

import java.util.Date;

/**
 * 客户管理对象 crm_customer
 * 
 * @author jeethink
 * @date 2020-04-02
 */
public class Crm2CustomerTemplate
{
    private static final long serialVersionUID = 1L;
  
    /** 客户类型 */
    @Excel(name = "客户类型", prompt = "0=个人客户,1=企业客户", type = Type.IMPORT)
    private String customerType;

    /** 客户名称 */
    @Excel(name = "客户名称", type = Type.IMPORT, prompt = "必填，为空则不导入")
    private String customerName;

    /** 客户简称 */
    @Excel(name = "客户简称", type = Type.IMPORT)
    private String nameShort;

    /** 公司座机 */
    @Excel(name = "公司座机")
    private String telephone;

    /** 手机 */
    @Excel(name = "公司手机")
    private String mobile;

    /** 邮箱 */
    @Excel(name = "公司邮箱")
    private String email;

    /** 传值 */
    @Excel(name = "公司传值")
    private String fax;

    /** 网址 */
    @Excel(name = "公司网址")
    private String website;

    /** 邮编 */
    @Excel(name = "公司邮编")
    private String zip;

    /** 所属行业（字典型） */
    @Excel(name = "所属行业", prompt = "请根据字典配置值填写")
    private String industry;

    /** 客户状态 */
    @Excel(name = "客户状态", prompt = "请根据字典配置值填写")
    private String customerStatus;

    /** 客户来源 */
    @Excel(name = "客户来源", prompt = "请根据字典配置值填写")
    private String customerSource;

    /** 客户等级 */
    @Excel(name = "客户等级", prompt = "请根据字典配置值填写")
    private String customerDegree;

    /** 客户优先级 */
    @Excel(name = "客户优先级", prompt = "请根据字典配置值填写")
    private String customerPriority;

    /** 客户信誉度 */
    @Excel(name = "客户信誉度", prompt = "请根据字典配置值填写")
    private String customerCredit;

    /** 客户成熟度 */
    @Excel(name = "客户成熟度", prompt = "请根据字典配置值填写")
    private String customerMaturity;

    /** 客户类别 */
    @Excel(name = "客户类别", prompt = "请根据字典配置值填写")
    private String customerCategory;
    
    /** 是否共享客户 */
    @Excel(name = "是否共享", prompt = "0=否,1=是")
    private String isShare;

    /** 客户地址 */
    @Excel(name = "客户地址")
    private String address;


    /** 联系人名称 */
    @Excel(name = "[联系人]名称", prompt = "必填，为空则不导入")
    private String personName;

    /** 职务 */
    @Excel(name = "[联系人]职务")
    private String duty;

    /** 手机 */
    @Excel(name = "[联系人]手机")
    private String personMobile;

    /** 用户邮箱 */
    @Excel(name = "[联系人]邮箱")
    private String personEmail;

    /** qq */
    @Excel(name = "[联系人]qq")
    private String qq;

    /** 微信 */
    @Excel(name = "[联系人]微信")
    private String wechat;

    /** 旺旺 */
    @Excel(name = "[联系人]旺旺")
    private String wangwang;

    /** MSN */
    @Excel(name = "[联系人]MSN")
    private String msn;

    /** 爱好 */
    @Excel(name = "[联系人]爱好")
    private String hobby;

    /** 性别 */
    @Excel(name = "[联系人]性别", prompt = "0=未知,1=男,2=女")
    private String sex;

    /** 婚否   字典 */
    @Excel(name = "[联系人]婚否")
    private String marriage;

    /** 出生日期 */
    @Excel(name = "[联系人]出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNameShort() {
		return nameShort;
	}

	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	public String getCustomerDegree() {
		return customerDegree;
	}

	public void setCustomerDegree(String customerDegree) {
		this.customerDegree = customerDegree;
	}

	public String getCustomerPriority() {
		return customerPriority;
	}

	public void setCustomerPriority(String customerPriority) {
		this.customerPriority = customerPriority;
	}

	public String getCustomerCredit() {
		return customerCredit;
	}

	public void setCustomerCredit(String customerCredit) {
		this.customerCredit = customerCredit;
	}

	public String getCustomerMaturity() {
		return customerMaturity;
	}

	public void setCustomerMaturity(String customerMaturity) {
		this.customerMaturity = customerMaturity;
	}

	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getPersonMobile() {
		return personMobile;
	}

	public void setPersonMobile(String personMobile) {
		this.personMobile = personMobile;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getWangwang() {
		return wangwang;
	}

	public void setWangwang(String wangwang) {
		this.wangwang = wangwang;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}