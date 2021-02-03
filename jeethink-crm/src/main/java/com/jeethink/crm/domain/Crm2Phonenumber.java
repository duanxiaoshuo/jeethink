package com.jeethink.crm.domain;

import com.jeethink.common.annotation.Excel;
import com.jeethink.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 电话本对象 crm_phonenumber
 * 
 * @author jeethink
 * @date 2020-03-15
 */
public class Crm2Phonenumber extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 电话本ID */
    private Long phonenumberId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 英文名 */
    private String englshName;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 职务 */
    private String duty;

    /** 固定电话 */
    private String telephone;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 传值 */
    private String fax;

    /** qq */
    @Excel(name = "qq")
    private String qq;

    /** MSN */
    private String msn;

    /** 爱好 */
    private String hobby;

    /** 婚否 */
    @Excel(name = "婚否")
    private String marriage;

    /** 出生日期 */
    private Date birthday;

    /** 头像（保留） */
    private String avatar;

    /** 数据字典（个人、公有） */
    @Excel(name = "数据字典", readConverterExp = "个=人、公有")
    private String phonenumberType;

    /** 删除标识 */
    private String delFlag;

    public void setPhonenumberId(Long phonenumberId) 
    {
        this.phonenumberId = phonenumberId;
    }

    public Long getPhonenumberId() 
    {
        return phonenumberId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setEnglshName(String englshName) 
    {
        this.englshName = englshName;
    }

    public String getEnglshName() 
    {
        return englshName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
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
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setPhonenumberType(String phonenumberType) 
    {
        this.phonenumberType = phonenumberType;
    }

    public String getPhonenumberType() 
    {
        return phonenumberType;
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
            .append("phonenumberId", getPhonenumberId())
            .append("name", getName())
            .append("englshName", getEnglshName())
            .append("sex", getSex())
            .append("duty", getDuty())
            .append("telephone", getTelephone())
            .append("mobile", getMobile())
            .append("email", getEmail())
            .append("fax", getFax())
            .append("qq", getQq())
            .append("msn", getMsn())
            .append("hobby", getHobby())
            .append("marriage", getMarriage())
            .append("birthday", getBirthday())
            .append("avatar", getAvatar())
            .append("phonenumberType", getPhonenumberType())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
