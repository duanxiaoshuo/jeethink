package com.jeethink.crm.service.crm1.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.jeethink.common.utils.DateUtils;
import com.jeethink.common.utils.StringUtils;
import com.jeethink.crm.domain.*;
import com.jeethink.crm.mapper.CrmShareRelationMapper;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jeethink.crm.mapper.CrmCustomerMapper;
import com.jeethink.crm.service.crm1.ICrmComplaintService;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm1.ICrmPersonService;
import com.jeethink.crm.service.crm1.ICrmVisitService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;

/**
 * 客户Service业务层处理
 *
 * @author jeethink
 * @date 2020-03-01
 */
@Service
public class CrmCustomerServiceImpl implements ICrmCustomerService {
    private static final Logger log = LoggerFactory.getLogger(ICrmCustomerService.class);
    @Autowired
    private CrmCustomerMapper crmCustomerMapper;
    @Autowired
    private CrmShareRelationMapper crmShareRelationMapper;
    @Autowired
    private ICrmPersonService crmPersonService;

    @Autowired
    private ICrmComplaintService crmComplaintService;

    @Autowired
    private ICrmVisitService crmVisitService;

    @Autowired
    private CrmFollowServiceImpl crmFollowService;

    /**
     * 查询客户
     *
     * @param customerId 客户ID
     * @return 客户
     */
    @Override
    public CrmCustomer selectCrmCustomerById(Long customerId) {
        return crmCustomerMapper.selectCrmCustomerById(customerId);
    }

    /**
     * 查询客户列表
     *
     * @param crmCustomer 客户
     * @return 客户
     */
    @Override
    public List<CrmCustomer> selectCrmCustomerList(CrmCustomer crmCustomer) {
        return crmCustomerMapper.selectCrmCustomerList(crmCustomer);
    }

    /**
     * 查询客户列表 我的
     *
     * @param crmCustomer 客户
     * @return 客户集合
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CrmCustomer> selectCrmCustomerListMy(CrmCustomer crmCustomer) {
        return crmCustomerMapper.selectCrmCustomerListMy(crmCustomer);
    }

    /**
     * 查询客户列表 共享
     *
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<CrmCustomer> selectCrmCustomerListShare(CrmCustomer crmCustomer, Long userid) {

        List<Long> customerIds = crmShareRelationMapper.selectShareCustomerId(userid);
        if (CollectionUtils.isEmpty(customerIds)) {
            return Collections.emptyList();
        }
        return crmCustomerMapper.selectCrmCustomerListShare(customerIds, crmCustomer);
    }

    /**
     * 查询客户列表 公共
     *
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<CrmCustomer> selectCrmCustomerListPublic(CrmCustomer crmCustomer) {
        return crmCustomerMapper.selectCrmCustomerListPublic(crmCustomer);
    }

    /**
     * 新增客户
     *
     * @param crmCustomer 客户
     * @return 结果
     */
    @Override
    public int insertCrmCustomer(CrmCustomer crmCustomer) {
        crmCustomer.setCreateTime(DateUtils.getNowDate());
        return crmCustomerMapper.insertCrmCustomer(crmCustomer);
    }

    /**
     * 修改客户
     *
     * @param crmCustomer 客户
     * @return 结果
     */
    @Override
    public int updateCrmCustomer(CrmCustomer crmCustomer) {
        crmCustomer.setUpdateTime(DateUtils.getNowDate());
        return crmCustomerMapper.updateCrmCustomer(crmCustomer);
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
            CrmPerson crmPerson = new CrmPerson();
            crmPerson.setCustomerId(customerId);
            List<CrmPerson> list = crmPersonService.selectCrmPersonListAll(crmPerson);
            if (list.size() > 0) {
                throw new BusinessException(String.format("客户%1$s存在关联的联系人,不能删除", customerId));
            }

            //报价单

            //订单

            //合同

            //查询是否有投诉
            CrmComplaint crmComplaint = new CrmComplaint();
            crmComplaint.setCustomerId(customerId);
            List<CrmComplaint> listComplaint = crmComplaintService.selectCrmComplaintListAll(crmComplaint);
            if (listComplaint.size() > 0) {
                throw new BusinessException(String.format("客户%1$s存在关联的投诉记录,不能删除", customerId));
            }

            //查询是否有拜访
            CrmVisit crmVisit = new CrmVisit();
            crmVisit.setCustomerId(customerId);
            List<CrmVisit> listVisit = crmVisitService.selectCrmVisitListAll(crmVisit);
            if (listVisit.size() > 0) {
                throw new BusinessException(String.format("客户%1$s存在关联的拜访记录,不能删除", customerId));
            }

            //查询是否有跟进
            CrmFollow crmFollow = new CrmFollow();
            crmFollow.setCustomerId(customerId);
            List<CrmFollow> listFollow = crmFollowService.selectCrmFollowListAll(crmFollow);
            if (listFollow.size() > 0) {
                throw new BusinessException(String.format("客户%1$s存在关联的跟进记录,不能删除", customerId));
            }
            List<CrmShareRelation> shareRelations = crmShareRelationMapper.selectCustomer(customerId);
            if (!CollectionUtils.isEmpty(shareRelations)) {
                throw new BusinessException(String.format("客户%1$s存在共享,不能删除", customerId));
            }
        }
        return crmCustomerMapper.deleteCrmCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息
     *
     * @param customerId 客户ID
     * @return 结果
     */
    @Override
    public int deleteCrmCustomerById(Long customerId) {
        return crmCustomerMapper.deleteCrmCustomerById(customerId);
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
        CrmCustomer crmCustomer = this.selectCrmCustomerById(customerId);
        if (null == crmCustomer) {
            throw new BusinessException("客户不存在！");
        }
        //公客不能设置共享属性
        if (StringUtils.isEmpty(crmCustomer.getBelongTo()) || StringUtils.isNull(crmCustomer.getBelongTo())) {
            throw new BusinessException("不能共享公共客户！");

        }
        crmCustomer.setIsShare(isShare);
        crmCustomer.setUpdateBy(operName);
        this.updateCrmCustomer(crmCustomer);
        if (isShare.equals("1")) {
            CrmShareRelation crmShareRelation = crmShareRelationMapper.selectCrmShareRelation(customerId, share, shred);
<<<<<<< HEAD
            if (null != crmShareRelation) {
                throw new BusinessException("不能重复共享！");
=======
            if (null!= crmShareRelation) {
                return successNum;

>>>>>>> 5010ddf2521633cfafa4f9495801d5d9fa2127ff
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
            crmShareRelationMapper.insertCrmShareRelation(crmShareRelation);
        } else {
            crmShareRelationMapper.deleteByCustomerId(customerId, loginName);
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
    public String importCustomer(List<CrmCustomerTemplate> customerList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(customerList) || customerList.size() == 0) {
            throw new BusinessException("导入客户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (CrmCustomerTemplate customerTemplate : customerList) {
            try {
                String customerName = customerTemplate.getCustomerName();
                if (StringUtils.isEmpty(customerName)) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、客户名称 :" + customerName + "  为空，不导入该条记录");
                    continue;
                }
                // 验证是否存在这个客户
                CrmCustomer c = crmCustomerMapper.selectCrmCustomerByName(customerName);
                if (StringUtils.isNull(c)) {
                    CrmCustomer crmCustomer = this.getCrmCustomer(customerTemplate, new CrmCustomer());
                    crmCustomer.setDelFlag("0");
                    crmCustomer.setCreateBy(operName);
                    crmCustomer.setSourceBelongTo(operName);
                    crmCustomer.setBelongTo(operName);
                    this.insertCrmCustomer(crmCustomer);
                    CrmPerson crmPerson = this.getCrmPerson(customerTemplate, new CrmPerson());
                    crmPerson.setCustomerId(crmCustomer.getCustomerId());
                    crmPerson.setDelFlag("0");
                    crmPerson.setCreateBy(operName);
                    crmPersonService.insertCrmPerson(crmPerson);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、客户名称 :" + crmCustomer.getCustomerName() + ",[联系人]名称:" + crmPerson.getPersonName() + " 导入成功");
                } else if (isUpdateSupport) {
                    CrmCustomer crmCustomer = this.getCrmCustomer(customerTemplate, c);
                    crmCustomer.setUpdateBy(operName);
                    this.updateCrmCustomer(c);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、客户名称 :" + customerTemplate.getCustomerName() + " 更新成功");

                    String personName = customerTemplate.getPersonName();
                    if (StringUtils.isEmpty(personName)) {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、客户名称 :" + customerTemplate.getCustomerName() + " 更新成功，[联系人]名称:" + personName + "  为空，不导入");
                        continue;
                    }
                    // 验证是否存在这个联系人
                    CrmPerson p = crmPersonService.selectCrmPersonByName(customerTemplate.getPersonName());
                    if (StringUtils.isNull(p)) {
                        CrmPerson crmPerson = this.getCrmPerson(customerTemplate, new CrmPerson());
                        crmPerson.setCustomerId(crmCustomer.getCustomerId());
                        crmPerson.setDelFlag("0");
                        crmPerson.setCreateBy(operName);
                        crmPersonService.insertCrmPerson(crmPerson);
                        successMsg.append(",[联系人]名称:" + customerTemplate.getPersonName() + " 导入成功");
                    } else {
                        CrmPerson crmPerson = this.getCrmPerson(customerTemplate, p);
                        crmPerson.setCustomerId(p.getCustomerId());
                        crmPerson.setUpdateBy(operName);
                        crmPersonService.updateCrmPerson(crmPerson);
                        successMsg.append(",[联系人]名称:" + customerTemplate.getPersonName() + " 更新成功");
                    }
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、客户名称 :" + customerTemplate.getCustomerName() + ",[联系人]名称:" + customerTemplate.getPersonName() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、客户名称 : " + customerTemplate.getCustomerName() + ",[联系人]名称:" + customerTemplate.getPersonName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
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
    private CrmCustomer getCrmCustomer(CrmCustomerTemplate customerTemplate, CrmCustomer crmCustomer) {
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
    private CrmPerson getCrmPerson(CrmCustomerTemplate customerTemplate, CrmPerson crmPerson) {
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
    public String checkCustomerNameUnique(CrmCustomer crmCustomer) {
        Long customerId = StringUtils.isNull(crmCustomer.getCustomerId()) ? -1L : crmCustomer.getCustomerId();
        CrmCustomer info = crmCustomerMapper.selectCrmCustomerByName(crmCustomer.getCustomerName());
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
    public String checkEmailUnique(CrmCustomer crmCustomer) {
        Long customerId = StringUtils.isNull(crmCustomer.getCustomerId()) ? -1L : crmCustomer.getCustomerId();
        CrmCustomer info = crmCustomerMapper.checkEmailUnique(crmCustomer.getEmail());
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
    public String checkTelephoneUnique(CrmCustomer crmCustomer) {
        Long customerId = StringUtils.isNull(crmCustomer.getCustomerId()) ? -1L : crmCustomer.getCustomerId();
        CrmCustomer info = crmCustomerMapper.checkTelephoneUnique(crmCustomer.getTelephone());
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
    public String checkMobileUnique(CrmCustomer crmCustomer) {
        Long customerId = StringUtils.isNull(crmCustomer.getCustomerId()) ? -1L : crmCustomer.getCustomerId();
        CrmCustomer info = crmCustomerMapper.checkMobileUnique(crmCustomer.getMobile());
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
        List<CrmCustomer> listCustomerNoFollow = crmCustomerMapper.selectCrmCustomerListNoFollow();
        List<CrmCustomer> listCustomerFollowMoreThan30 = crmCustomerMapper.selectCrmCustomerListFollowMoreThan30();
        if (listCustomerNoFollow != null && listCustomerNoFollow.size() > 0) {
            for (int i = 0; i < listCustomerNoFollow.size(); i++) {
                CrmCustomer customer = listCustomerNoFollow.get(i);
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
                CrmCustomer customer = listCustomerFollowMoreThan30.get(i);
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
