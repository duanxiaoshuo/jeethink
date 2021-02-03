package com.jeethink.crm.domain;

import com.jeethink.common.annotation.Excel;
import com.jeethink.common.annotation.Excel.Type;
import com.jeethink.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 线索管理对象 crm_clue
 * 
 * @author jeethink
 * @date 2020-04-06
 */
public class Crm2Clue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 线索ID */
    private Long clueId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 公司 */
    @Excel(name = "公司")
    private String company;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 职务 */
    @Excel(name = "职务")
    private String duty;

    /** 手机 */
    @Excel(name = "手机")
    private String mobile;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

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

    /** 性别 */
    @Excel(name = "性别",readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 线索来源 */
    @Excel(name = "线索来源")
    private String clueSource;

    /** 决策等级 */
    @Excel(name = "决策等级")
    private String decisionLevel;

    /** 市场活动 */
    @Excel(name = "市场活动")
    private String marketActivity;

    /** 原负责人(login_name) */
    @Excel(name = "原负责人", type = Type.EXPORT)
    private String sourceBelongTo;

    /** 负责人(login_name) */
    @Excel(name = "负责人", type = Type.EXPORT)
    private String belongTo;

    /** 转交时间 */
    @Excel(name = "转交时间", width = 30, dateFormat = "yyyy-MM-dd", type = Type.EXPORT)
    private Date trasferDate;

    /** 转交人(login_name) */
    @Excel(name = "转交人", type = Type.EXPORT)
    private String trasferTo;

    /** 头像（保留） */
    private String avatar;

    /** 删除标识 */
    private String delFlag;

    /** 扩展文本框 */
    private String extInput1;

    /** 扩展文本框 */
    private String extInput2;

    /** 扩展文本框 */
    private String extInput3;

    /** 扩展文本框 */
    private String extInput4;

    /** 扩展文本框 */
    private String extInput5;

    /** 扩展下拉框 */
    private String extClueSelect1;

    /** 扩展下拉框 */
    private String extClueSelect2;

    /** 扩展下拉框 */
    private String extClueSelect3;

    /** 扩展下拉框 */
    private String extClueSelect4;

    /** 扩展下拉框 */
    private String extClueSelect5;

    /** 扩展数字框 */
    private Long extNumber1;

    /** 扩展数字框 */
    private Long extNumber2;

    /** 扩展数字框 */
    private Long extNumber3;

    /** 扩展数字框 */
    private Long extNumber4;

    /** 扩展数字框 */
    private Long extNumber5;

    /** 扩展日期 */
    private Date extDate1;

    /** 扩展日期 */
    private Date extDate2;

    /** 扩展日期 */
    private Date extDate3;

    /** 扩展日期 */
    private Date extDate4;

    /** 线索状态 */
    @Excel(name = "线索状态")
    private String clueStatus;

    /** 扩展备注 */
    private String extMemo1;

    public void setClueId(Long clueId) 
    {
        this.clueId = clueId;
    }

    public Long getClueId() 
    {
        return clueId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setDuty(String duty) 
    {
        this.duty = duty;
    }

    public String getDuty() 
    {
        return duty;
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
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setClueSource(String clueSource) 
    {
        this.clueSource = clueSource;
    }

    public String getClueSource() 
    {
        return clueSource;
    }
    public void setDecisionLevel(String decisionLevel) 
    {
        this.decisionLevel = decisionLevel;
    }

    public String getDecisionLevel() 
    {
        return decisionLevel;
    }
    public void setMarketActivity(String marketActivity) 
    {
        this.marketActivity = marketActivity;
    }

    public String getMarketActivity() 
    {
        return marketActivity;
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
    public void setExtInput1(String extInput1) 
    {
        this.extInput1 = extInput1;
    }

    public String getExtInput1() 
    {
        return extInput1;
    }
    public void setExtInput2(String extInput2) 
    {
        this.extInput2 = extInput2;
    }

    public String getExtInput2() 
    {
        return extInput2;
    }
    public void setExtInput3(String extInput3) 
    {
        this.extInput3 = extInput3;
    }

    public String getExtInput3() 
    {
        return extInput3;
    }
    public void setExtInput4(String extInput4) 
    {
        this.extInput4 = extInput4;
    }

    public String getExtInput4() 
    {
        return extInput4;
    }
    public void setExtInput5(String extInput5) 
    {
        this.extInput5 = extInput5;
    }

    public String getExtInput5() 
    {
        return extInput5;
    }
    public void setExtClueSelect1(String extClueSelect1) 
    {
        this.extClueSelect1 = extClueSelect1;
    }

    public String getExtClueSelect1() 
    {
        return extClueSelect1;
    }
    public void setExtClueSelect2(String extClueSelect2) 
    {
        this.extClueSelect2 = extClueSelect2;
    }

    public String getExtClueSelect2() 
    {
        return extClueSelect2;
    }
    public void setExtClueSelect3(String extClueSelect3) 
    {
        this.extClueSelect3 = extClueSelect3;
    }

    public String getExtClueSelect3() 
    {
        return extClueSelect3;
    }
    public void setExtClueSelect4(String extClueSelect4) 
    {
        this.extClueSelect4 = extClueSelect4;
    }

    public String getExtClueSelect4() 
    {
        return extClueSelect4;
    }
    public void setExtClueSelect5(String extClueSelect5) 
    {
        this.extClueSelect5 = extClueSelect5;
    }

    public String getExtClueSelect5() 
    {
        return extClueSelect5;
    }
    public void setExtNumber1(Long extNumber1) 
    {
        this.extNumber1 = extNumber1;
    }

    public Long getExtNumber1() 
    {
        return extNumber1;
    }
    public void setExtNumber2(Long extNumber2) 
    {
        this.extNumber2 = extNumber2;
    }

    public Long getExtNumber2() 
    {
        return extNumber2;
    }
    public void setExtNumber3(Long extNumber3) 
    {
        this.extNumber3 = extNumber3;
    }

    public Long getExtNumber3() 
    {
        return extNumber3;
    }
    public void setExtNumber4(Long extNumber4) 
    {
        this.extNumber4 = extNumber4;
    }

    public Long getExtNumber4() 
    {
        return extNumber4;
    }
    public void setExtNumber5(Long extNumber5) 
    {
        this.extNumber5 = extNumber5;
    }

    public Long getExtNumber5() 
    {
        return extNumber5;
    }
    public void setExtDate1(Date extDate1) 
    {
        this.extDate1 = extDate1;
    }

    public Date getExtDate1() 
    {
        return extDate1;
    }
    public void setExtDate2(Date extDate2) 
    {
        this.extDate2 = extDate2;
    }

    public Date getExtDate2() 
    {
        return extDate2;
    }
    public void setExtDate3(Date extDate3) 
    {
        this.extDate3 = extDate3;
    }

    public Date getExtDate3() 
    {
        return extDate3;
    }
    public void setExtDate4(Date extDate4) 
    {
        this.extDate4 = extDate4;
    }

    public Date getExtDate4() 
    {
        return extDate4;
    }
    public void setClueStatus(String clueStatus) 
    {
        this.clueStatus = clueStatus;
    }

    public String getClueStatus() 
    {
        return clueStatus;
    }
    public void setExtMemo1(String extMemo1) 
    {
        this.extMemo1 = extMemo1;
    }

    public String getExtMemo1() 
    {
        return extMemo1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("clueId", getClueId())
            .append("name", getName())
            .append("company", getCompany())
            .append("address", getAddress())
            .append("duty", getDuty())
            .append("mobile", getMobile())
            .append("email", getEmail())
            .append("qq", getQq())
            .append("wechat", getWechat())
            .append("wangwang", getWangwang())
            .append("msn", getMsn())
            .append("sex", getSex())
            .append("clueSource", getClueSource())
            .append("decisionLevel", getDecisionLevel())
            .append("marketActivity", getMarketActivity())
            .append("sourceBelongTo", getSourceBelongTo())
            .append("belongTo", getBelongTo())
            .append("trasferDate", getTrasferDate())
            .append("trasferTo", getTrasferTo())
            .append("avatar", getAvatar())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("extInput1", getExtInput1())
            .append("extInput2", getExtInput2())
            .append("extInput3", getExtInput3())
            .append("extInput4", getExtInput4())
            .append("extInput5", getExtInput5())
            .append("extClueSelect1", getExtClueSelect1())
            .append("extClueSelect2", getExtClueSelect2())
            .append("extClueSelect3", getExtClueSelect3())
            .append("extClueSelect4", getExtClueSelect4())
            .append("extClueSelect5", getExtClueSelect5())
            .append("extNumber1", getExtNumber1())
            .append("extNumber2", getExtNumber2())
            .append("extNumber3", getExtNumber3())
            .append("extNumber4", getExtNumber4())
            .append("extNumber5", getExtNumber5())
            .append("extDate1", getExtDate1())
            .append("extDate2", getExtDate2())
            .append("extDate3", getExtDate3())
            .append("extDate4", getExtDate4())
            .append("clueStatus", getClueStatus())
            .append("extMemo1", getExtMemo1())
            .toString();
    }
}