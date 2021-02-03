package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.common.utils.StringUtils;
import com.jeethink.crm.domain.*;
import com.jeethink.crm.mapper.Crm2CustomerMapper;
import com.jeethink.crm.mapper.Crm2ShareRelationMapper;
import com.jeethink.crm.mapper.CrmShareRelationMapper;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm2.ICrm2ComplaintService;
import com.jeethink.crm.service.crm2.ICrm2CustomerService;
import com.jeethink.crm.service.crm2.ICrm2PersonService;
import com.jeethink.crm.service.crm2.ICrm2VisitService;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 客户Service业务层处理
 *
 * @author jeethink
 * @date 2020-03-01
 */
@Service
public class Crm2CustomerServiceImpl implements ICrm2CustomerService {
    private static final Logger log = LoggerFactory.getLogger(ICrmCustomerService.class);
    @Autowired
    private Crm2CustomerMapper crm2CustomerMapper;
    @Autowired
    private Crm2ShareRelationMapper crm2ShareRelationMapper;
    @Autowired
    private ICrm2PersonService crm2PersonService;

    @Autowired
    private ICrm2ComplaintService crm2ComplaintService;

    @Autowired
    private ICrm2VisitService crm2VisitService;

    @Autowired
    private Crm2FollowServiceImpl crm2FollowService;

    /**
     * 查询客户
     *
     * @param customerId 客户ID
     * @return 客户
     */
    @Override
    public Crm2Customer selectCrmCustomerById(Long customerId) {
        return crm2CustomerMapper.selectCrmCustomerById(customerId);
    }

    /**
     * 查询客户列表
     *
     * @param crmCustomer 客户
     * @return 客户
     */
    @Override
    public List<Crm2Customer> selectCrmCustomerList(Crm2Customer crmCustomer) {
        return crm2CustomerMapper.selectCrmCustomerList(crmCustomer);
    }

    @Override
    public List<Crm2Customer> selectCrmCustomerListMy(Crm2Customer crmCustomer) {
        return crm2CustomerMapper.selectCrmCustomerListMy(crmCustomer);
    }


    /**
     * 查询客户列表 共享
     *
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<Crm2Customer> selectCrmCustomerListShare(Crm2Customer crmCustomer, Long userid) {
        List<Long> customerIds = crm2ShareRelationMapper.selectShareCustomerId(userid);
        if (CollectionUtils.isEmpty(customerIds)) {
            return Collections.emptyList();
        }
        return crm2CustomerMapper.selectCrmCustomerListShare(customerIds, crmCustomer);
    }

    /**
     * 查询客户列表 公共
     *
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<Crm2Customer> selectCrmCustomerListPublic(Crm2Customer crmCustomer) {
        return crm2CustomerMapper.selectCrmCustomerListPublic(crmCustomer);
    }

    /**
     * 新增客户
     *
     * @param crmCustomer 客户
     * @return 结果
     */
    @Override
    public int insertCrmCustomer(Crm2Customer crmCustomer) {
        crmCustomer.setCreateTime(DateUtils.getNowDate());
        return crm2CustomerMapper.insertCrmCustomer(crmCustomer);
    }

    /**
     * 修改客户
     *
     * @param crmCustomer 客户
     * @return 结果
     */
    @Override
    public int updateCrmCustomer(Crm2Customer crmCustomer) {
        crmCustomer.setUpdateTime(DateUtils.getNowDate());
        return crm2CustomerMapper.updateCrmCustomer(crmCustomer);
    }

    /**
     * 删除客户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmCustomerByIds(String ids) {

        Long[] customerIds = Convert.toLongArray(ids);
        for (int j = 0; j < customerIds.length; j++) {
            Long customerId = customerIds[j];

            //查询是否有联系人
            Crm2Person crmPerson = new Crm2Person();
            crmPerson.setCustomerId(customerId);
            List<Crm2Person> list = crm2PersonService.selectCrmPersonListAll(crmPerson);
            if (list.size() > 0) {
                throw new BusinessException(String.format("客户%1$s存在关联的联系人,不能删除", customerId));
            }

            //报价单

            //订单

            //合同

            //查询是否有投诉
            CrmComplaint crmComplaint = new CrmComplaint();
            crmComplaint.setCustomerId(customerId);
            List<CrmComplaint> listComplaint = crm2ComplaintService.selectCrmComplaintListAll(crmComplaint);
            if (listComplaint.size() > 0) {
                throw new BusinessException(String.format("客户%1$s存在关联的投诉记录,不能删除", customerId));
            }

            //查询是否有拜访
            CrmVisit crmVisit = new CrmVisit();
            crmVisit.setCustomerId(customerId);
            List<CrmVisit> listVisit = crm2VisitService.selectCrmVisitListAll(crmVisit);
            if (listVisit.size() > 0) {
                throw new BusinessException(String.format("客户%1$s存在关联的拜访记录,不能删除", customerId));
            }

            //查询是否有跟进
            CrmFollow crmFollow = new CrmFollow();
            crmFollow.setCustomerId(customerId);
            List<CrmFollow> listFollow = crm2FollowService.selectCrmFollowListAll(crmFollow);
            if (listFollow.size() > 0) {
                throw new BusinessException(String.format("客户%1$s存在关联的跟进记录,不能删除", customerId));
            }
        }
        return crm2CustomerMapper.deleteCrmCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息
     *
     * @param customerId 客户ID
     * @return 结果
     */
    @Override
    public int deleteCrmCustomerById(Long customerId) {
        return crm2CustomerMapper.deleteCrmCustomerById(customerId);
    }

    /**
     * 批量分享客户
     *
     * @param customerId 需要分享的数据ID
     * @param isShare    是否分享
     * @param operName   操作人
     * @return 结果
     */
    @Override
    @Transactional
    public int shareCrmCustomerByIds(Long customerId, String isShare, String operName, Long share, Long shred, String loginName) {
        int successNum = 0;
        Crm2Customer crmCustomer = this.selectCrmCustomerById(customerId);
        if (null == crmCustomer) {
            return successNum;

        }
        //公客不能设置共享属性
        if (StringUtils.isEmpty(crmCustomer.getBelongTo()) || StringUtils.isNull(crmCustomer.getBelongTo())) {
            return successNum;

        }


        crmCustomer.setIsShare(isShare);
        crmCustomer.setUpdateBy(operName);
        this.updateCrmCustomer(crmCustomer);
        if (isShare.equals("1")) {
            CrmShareRelation crmShareRelation = crm2ShareRelationMapper.selectCrmShareRelation(customerId, share, shred);
            if (null == crmShareRelation) {
                return successNum;

            }
            crmShareRelation = new CrmShareRelation();
            crmShareRelation.setCustomerId(customerId);
            crmShareRelation.setShareUserId(share);
            crmShareRelation.setSharedUserId(shred);
            crmShareRelation.setDelFlag("0");
            crmShareRelation.setCreateBy(loginName);
            crmShareRelation.setCreateTime(new Date());
            crmShareRelation.setUpdateTime(new Date());
            crmShareRelation.setUpdateBy(loginName);
            crm2ShareRelationMapper.insertCrmShareRelation(crmShareRelation);
        } else {
            crm2ShareRelationMapper.deleteByCustomerId(customerId, loginName);
        }

        successNum++;

        return successNum;
    }

    /**
     * 导入客户数据
     *
     * @param customerList    客户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    @Override
    @Transactional
    public String importCustomer(List<Crm2CustomerTemplate> customerList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(customerList) || customerList.size() == 0) {
            throw new BusinessException("导入客户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Crm2CustomerTemplate customerTemplate : customerList) {
            try {
                String customerName = customerTemplate.getCustomerName();
                if (StringUtils.isEmpty(customerName)) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、客户名称 :" + customerName + "  为空，不导入该条记录");
                    continue;
                }
                // 验证是否存在这个客户
                Crm2Customer c = crm2CustomerMapper.selectCrmCustomerByName(customerName);
                if (StringUtils.isNull(c)) {
                    Crm2Customer crmCustomer = this.getCrmCustomer(customerTemplate, new Crm2Customer());
                    crmCustomer.setDelFlag("0");
                    crmCustomer.setCreateBy(operName);
                    crmCustomer.setSourceBelongTo(operName);
                    crmCustomer.setBelongTo(operName);
                    this.insertCrmCustomer(crmCustomer);
                    Crm2Person crmPerson = this.getCrmPerson(customerTemplate, new Crm2Person());
                    crmPerson.setCustomerId(crmCustomer.getCustomerId());
                    crmPerson.setDelFlag("0");
                    crmPerson.setCreateBy(operName);
                    crm2PersonService.insertCrmPerson(crmPerson);
                    successNum++;
                    successMsg.append("<br/>").append(successNum).append("、客户名称 :").append(crmCustomer.getCustomerName()).append(",[联系人]名称:").append(crmPerson.getPersonName()).append(" 导入成功");
                } else if (isUpdateSupport) {
                    Crm2Customer crmCustomer = this.getCrmCustomer(customerTemplate, c);
                    crmCustomer.setUpdateBy(operName);
                    this.updateCrmCustomer(c);
                    successNum++;
                    successMsg.append("<br/>").append(successNum).append("、客户名称 :").append(customerTemplate.getCustomerName()).append(" 更新成功");

                    String personName = customerTemplate.getPersonName();
                    if (StringUtils.isEmpty(personName)) {
                        failureNum++;
                        failureMsg.append("<br/>").append(failureNum).append("、客户名称 :").append(customerTemplate.getCustomerName()).append(" 更新成功，[联系人]名称:").append(personName).append("  为空，不导入");
                        continue;
                    }
                    // 验证是否存在这个联系人
                    Crm2Person p = crm2PersonService.selectCrmPersonByName(customerTemplate.getPersonName());
                    if (StringUtils.isNull(p)) {
                        Crm2Person crmPerson = this.getCrmPerson(customerTemplate, new Crm2Person());
                        crmPerson.setCustomerId(crmCustomer.getCustomerId());
                        crmPerson.setDelFlag("0");
                        crmPerson.setCreateBy(operName);
                        crm2PersonService.insertCrmPerson(crmPerson);
                        successMsg.append(",[联系人]名称:" + customerTemplate.getPersonName() + " 导入成功");
                    } else {
                        Crm2Person crmPerson = this.getCrmPerson(customerTemplate, p);
                        crmPerson.setCustomerId(p.getCustomerId());
                        crmPerson.setUpdateBy(operName);
                        crm2PersonService.updateCrmPerson(crmPerson);
                        successMsg.append(",[联系人]名称:" + customerTemplate.getPersonName() + " 更新成功");
                    }
                } else {
                    failureNum++;
                    failureMsg.append("<br/>").append(failureNum).append("、客户名称 :").append(customerTemplate.getCustomerName()).append(",[联系人]名称:").append(customerTemplate.getPersonName()).append(" 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、客户名称 : " + customerTemplate.getCustomerName() + ",[联系人]名称:" + customerTemplate.getPersonName() + " 导入失败：";
                failureMsg.append(msg).append(e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();

    }

    /*
     * 根据导入模版获取客户实体
     */
    private Crm2Customer getCrmCustomer(Crm2CustomerTemplate customerTemplate, Crm2Customer crmCustomer) {
        crmCustomer.setCustomerType(customerTemplate.getCustomerType());
        crmCustomer.setCustomerName(customerTemplate.getCustomerName());
        crmCustomer.setNameShort(customerTemplate.getNameShort());
        crmCustomer.setTelephone(customerTemplate.getTelephone());
        crmCustomer.setMobile(customerTemplate.getMobile());
        crmCustomer.setEmail(customerTemplate.getEmail());
        crmCustomer.setFax(customerTemplate.getFax());
        crmCustomer.setWebsite(customerTemplate.getWebsite());
        crmCustomer.setZip(customerTemplate.getZip());
        crmCustomer.setIndustry(customerTemplate.getIndustry());
        crmCustomer.setCustomerType(customerTemplate.getCustomerStatus());
        crmCustomer.setCustomerSource(customerTemplate.getCustomerSource());
        crmCustomer.setCustomerDegree(customerTemplate.getCustomerDegree());
        crmCustomer.setCustomerCredit(customerTemplate.getCustomerCredit());
        crmCustomer.setCustomerMaturity(customerTemplate.getCustomerMaturity());
        crmCustomer.setCustomerCategory(customerTemplate.getCustomerCategory());
        crmCustomer.setIsShare(customerTemplate.getIsShare());
        crmCustomer.setAddress(customerTemplate.getAddress());
        return crmCustomer;
    }

    /*
     * 根据导入模版获取联系人实体
     */
    private Crm2Person getCrmPerson(Crm2CustomerTemplate customerTemplate, Crm2Person crmPerson) {
        //设置联系人信息
        crmPerson.setPersonName(customerTemplate.getPersonName());
        crmPerson.setDuty(customerTemplate.getDuty());
        crmPerson.setMobile(customerTemplate.getPersonMobile());
        crmPerson.setEmail(customerTemplate.getPersonEmail());
        crmPerson.setQq(customerTemplate.getQq());
        crmPerson.setWechat(customerTemplate.getWechat());
        crmPerson.setWangwang(customerTemplate.getWangwang());
        crmPerson.setHobby(customerTemplate.getHobby());
        crmPerson.setSex(customerTemplate.getSex());
        crmPerson.setMarriage(customerTemplate.getMarriage());
        return crmPerson;
    }

    /**
     * 校验客户名称是否唯一
     *
     * @param crmCustomer 客户信息
     * @return
     */
    @Override
    public String checkCustomerNameUnique(Crm2Customer crmCustomer) {
        Long customerId = StringUtils.isNull(crmCustomer.getCustomerId()) ? -1L : crmCustomer.getCustomerId();
        Crm2Customer info = crm2CustomerMapper.selectCrmCustomerByName(crmCustomer.getCustomerName());
        if (StringUtils.isNotNull(info) && info.getCustomerId().longValue() != customerId.longValue()) {
            return "1";
        }
        return "0";
    }

    /**
     * 校验Email是否唯一
     *
     * @param crmCustomer 客户信息
     * @return
     */
    @Override
    public String checkEmailUnique(Crm2Customer crmCustomer) {
        Long customerId = StringUtils.isNull(crmCustomer.getCustomerId()) ? -1L : crmCustomer.getCustomerId();
        Crm2Customer info = crm2CustomerMapper.checkEmailUnique(crmCustomer.getEmail());
        if (StringUtils.isNotNull(info) && info.getCustomerId().longValue() != customerId.longValue()) {
            return "1";
        }
        return "0";
    }

    /**
     * 校验座机是否唯一
     *
     * @param crmCustomer 客户信息
     * @return
     */
    @Override
    public String checkTelephoneUnique(Crm2Customer crmCustomer) {
        Long customerId = StringUtils.isNull(crmCustomer.getCustomerId()) ? -1L : crmCustomer.getCustomerId();
        Crm2Customer info = crm2CustomerMapper.checkTelephoneUnique(crmCustomer.getTelephone());
        if (StringUtils.isNotNull(info) && info.getCustomerId().longValue() != customerId.longValue()) {
            return "1";
        }
        return "0";
    }

    /**
     * 校验手机是否唯一
     *
     * @param crmCustomer 客户信息
     * @return
     */
    @Override
    public String checkMobileUnique(Crm2Customer crmCustomer) {
        Long customerId = StringUtils.isNull(crmCustomer.getCustomerId()) ? -1L : crmCustomer.getCustomerId();
        Crm2Customer info = crm2CustomerMapper.checkMobileUnique(crmCustomer.getMobile());
        if (StringUtils.isNotNull(info) && info.getCustomerId().longValue() != customerId.longValue()) {
            return "1";
        }
        return "0";
    }

    /**
     * 将客户转为公告客户
     * 未跟进过和超过30天未跟进的客户
     */
    @Override
    public void convertCrmCustomerToPublic() {
        List<Crm2Customer> listCustomerNoFollow = crm2CustomerMapper.selectCrmCustomerListNoFollow();
        List<Crm2Customer> listCustomerFollowMoreThan30 = crm2CustomerMapper.selectCrmCustomerListFollowMoreThan30();
        if (listCustomerNoFollow != null && listCustomerNoFollow.size() > 0) {
            for (int i = 0; i < listCustomerNoFollow.size(); i++) {
                Crm2Customer customer = listCustomerNoFollow.get(i);
                if (customer.getBelongTo() == null || customer.getBelongTo().equals("")) {
                    break;
                }
                customer.setBelongTo("");
                customer.setUpdateBy("system");
                this.updateCrmCustomer(customer);
            }
        }
        if (listCustomerFollowMoreThan30 != null && listCustomerFollowMoreThan30.size() > 0) {
            for (int i = 0; i < listCustomerFollowMoreThan30.size(); i++) {
                Crm2Customer customer = listCustomerFollowMoreThan30.get(i);
                if (customer.getBelongTo() == null || customer.getBelongTo().equals("")) {
                    break;
                }
                customer.setBelongTo("");
                customer.setUpdateBy("system");
                this.updateCrmCustomer(customer);
            }
        }
    }
}
