package com.jeethink.crm.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 客户联系人对象 crm_person
 * 
 * @author jeethink
 * @date 2020-04-02
 */
public class CrmPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 联系人ID */
    private Long personId;

    /** 所属客户 */
    @Excel(name = "所属客户")
    private Long customerId;
    
    @Excel(name = "所属客户", targetAttr = "customerName", type = Type.EXPORT)
    private CrmCustomer customer;

    /** 联系人名称 */
    @Excel(name = "联系人名称")
    private String personName;

    /** 联系人英文名 */
    @Excel(name = "联系人英文名")
    private String englishName;

    /** 职务 */
    @Excel(name = "职务")
    private String duty;

    /** 固定电话 */
    @Excel(name = "固定电话")
    private String telephone;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 传真 */
    @Excel(name = "传真")
    private String fax;

    /** qq */
    @Excel(name = "qq")
    private String qq;

    /** 微信 */
    @Excel(name = "微信")
    private String wechat;

    /** 旺旺 */
    @Excel(name = "旺旺")
    private String wangwang;

    /** MSN */
    @Excel(name = "MSN")
    private String msn;

    /** 爱好 */
    @Excel(name = "爱好")
    private String hobby;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 婚否   字典 */
    @Excel(name = "婚否   字典")
    private String marriage;

    /** 出生日期 */
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 决策等级（字典 分星展示） */
    @Excel(name = "决策等级", readConverterExp = "字=典,分=星展示")
    private String personDecisionLevel;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idNum;

    /** 家庭电话 */
    @Excel(name = "家庭电话")
    private String homeMobile;

    /** 家庭住址 */
    @Excel(name = "家庭住址")
    private String homeAddress;

    /** 头像（保留） */
    @Excel(name = "头像", readConverterExp = "保=留")
    private String avatar;

    /** 删除标识 */
    private String delFlag;

    public void setPersonId(Long personId) 
    {
        this.personId = personId;
    }

    public Long getPersonId() 
    {
        return personId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    
    public CrmCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}

	public void setPersonName(String personName) 
    {
        this.personName = personName;
    }

    public String getPersonName() 
    {
        return personName;
    }
    public void setEnglishName(String englishName) 
    {
        this.englishName = englishName;
    }

    public String getEnglishName() 
    {
        return englishName;
    }
    public void setDuty(String duty) 
    {
        this.duty = duty;
    }

    public String getDuty() 
    {
        return duty;
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
    public void setQq(String qq) 
    {
        this.qq = qq;
    }

    public String getQq() 
    {
        return qq;
    }
    public void setWechat(String wechat) 
    {
        this.wechat = wechat;
    }

    public String getWechat() 
    {
        return wechat;
    }
    public void setWangwang(String wangwang) 
    {
        this.wangwang = wangwang;
    }

    public String getWangwang() 
    {
        return wangwang;
    }
    public void setMsn(String msn) 
    {
        this.msn = msn;
    }

    public String getMsn() 
    {
        return msn;
    }
    public void setHobby(String hobby) 
    {
        this.hobby = hobby;
    }

    public String getHobby() 
    {
        return hobby;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setMarriage(String marriage) 
    {
        this.marriage = marriage;
    }

    public String getMarriage() 
    {
        return marriage;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setPersonDecisionLevel(String personDecisionLevel) 
    {
        this.personDecisionLevel = personDecisionLevel;
    }

    public String getPersonDecisionLevel() 
    {
        return personDecisionLevel;
    }
    public void setIdType(String idType) 
    {
        this.idType = idType;
    }

    public String getIdType() 
    {
        return idType;
    }
    public void setIdNum(String idNum) 
    {
        this.idNum = idNum;
    }

    public String getIdNum() 
    {
        return idNum;
    }
    public void setHomeMobile(String homeMobile) 
    {
        this.homeMobile = homeMobile;
    }

    public String getHomeMobile() 
    {
        return homeMobile;
    }
    public void setHomeAddress(String homeAddress) 
    {
        this.homeAddress = homeAddress;
    }

    public String getHomeAddress() 
    {
        return homeAddress;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
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
            .append("personId", getPersonId())
            .append("customerId", getCustomerId())
            .append("personName", getPersonName())
            .append("englishName", getEnglishName())
            .append("duty", getDuty())
            .append("telephone", getTelephone())
            .append("mobile", getMobile())
            .append("email", getEmail())
            .append("fax", getFax())
            .append("qq", getQq())
            .append("wechat", getWechat())
            .append("wangwang", getWangwang())
            .append("msn", getMsn())
            .append("hobby", getHobby())
            .append("sex", getSex())
            .append("marriage", getMarriage())
            .append("birthday", getBirthday())
            .append("personDecisionLevel", getPersonDecisionLevel())
            .append("idType", getIdType())
            .append("idNum", getIdNum())
            .append("homeMobile", getHomeMobile())
            .append("homeAddress", getHomeAddress())
            .append("avatar", getAvatar())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}