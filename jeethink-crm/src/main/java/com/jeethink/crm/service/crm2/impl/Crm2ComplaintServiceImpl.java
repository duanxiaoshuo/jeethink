package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.Crm2Complaint;
import com.jeethink.crm.domain.CrmComplaint;
import com.jeethink.crm.mapper.Crm2ComplaintMapper;
import com.jeethink.crm.service.crm2.ICrm2ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户投诉Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-09
 */
@Service
public class Crm2ComplaintServiceImpl implements ICrm2ComplaintService
{
    @Autowired
    private Crm2ComplaintMapper crm2ComplaintMapper;

    /**
     * 查询客户投诉
     * 
     * @param complaintId 客户投诉ID
     * @return 客户投诉
     */
    @Override
    public Crm2Complaint selectCrmComplaintById(Long complaintId)
    {
        return crm2ComplaintMapper.selectCrmComplaintById(complaintId);
    }

    /**
     * 查询客户投诉列表
     * 
     * @param crmComplaint 客户投诉
     * @return 客户投诉
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Crm2Complaint> selectCrmComplaintList(Crm2Complaint crmComplaint)
    {
        return crm2ComplaintMapper.selectCrmComplaintList(crmComplaint);
    }
    
    /**
     * 查询客户投诉列表
     * 
     * @param crmComplaint 客户投诉
     * @return 客户投诉
     */
    @Override
    public List<CrmComplaint> selectCrmComplaintListAll(CrmComplaint crmComplaint)
    {
        return crm2ComplaintMapper.selectCrmComplaintListAll(crmComplaint);
    }

    /**
     * 新增客户投诉
     * 
     * @param crmComplaint 客户投诉
     * @return 结果
     */
    @Override
    public int insertCrmComplaint(Crm2Complaint crmComplaint)
    {
    	crmComplaint.setDelFlag("0");
        crmComplaint.setCreateTime(DateUtils.getNowDate());
        return crm2ComplaintMapper.insertCrmComplaint(crmComplaint);
    }

    /**
     * 修改客户投诉
     * 
     * @param crmComplaint 客户投诉
     * @return 结果
     */
    @Override
    public int updateCrmComplaint(Crm2Complaint crmComplaint)
    {
        crmComplaint.setUpdateTime(DateUtils.getNowDate());
        return crm2ComplaintMapper.updateCrmComplaint(crmComplaint);
    }

    /**
     * 删除客户投诉对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmComplaintByIds(String ids)
    {
        return crm2ComplaintMapper.deleteCrmComplaintByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户投诉信息
     * 
     * @param complaintId 客户投诉ID
     * @return 结果
     */
    @Override
    public int deleteCrmComplaintById(Long complaintId)
    {
        return crm2ComplaintMapper.deleteCrmComplaintById(complaintId);
    }
}
