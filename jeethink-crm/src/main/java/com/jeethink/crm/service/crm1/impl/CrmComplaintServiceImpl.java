package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.CrmComplaintMapper;
import com.jeethink.crm.domain.CrmComplaint;
import com.jeethink.crm.service.crm1.ICrmComplaintService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;

/**
 * 客户投诉Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-09
 */
@Service
public class CrmComplaintServiceImpl implements ICrmComplaintService 
{
    @Autowired
    private CrmComplaintMapper crmComplaintMapper;

    /**
     * 查询客户投诉
     * 
     * @param complaintId 客户投诉ID
     * @return 客户投诉
     */
    @Override
    public CrmComplaint selectCrmComplaintById(Long complaintId)
    {
        return crmComplaintMapper.selectCrmComplaintById(complaintId);
    }

    /**
     * 查询客户投诉列表
     * 
     * @param crmComplaint 客户投诉
     * @return 客户投诉
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CrmComplaint> selectCrmComplaintList(CrmComplaint crmComplaint)
    {
        return crmComplaintMapper.selectCrmComplaintList(crmComplaint);
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
        return crmComplaintMapper.selectCrmComplaintListAll(crmComplaint);
    }

    /**
     * 新增客户投诉
     * 
     * @param crmComplaint 客户投诉
     * @return 结果
     */
    @Override
    public int insertCrmComplaint(CrmComplaint crmComplaint)
    {
    	crmComplaint.setDelFlag("0");
        crmComplaint.setCreateTime(DateUtils.getNowDate());
        return crmComplaintMapper.insertCrmComplaint(crmComplaint);
    }

    /**
     * 修改客户投诉
     * 
     * @param crmComplaint 客户投诉
     * @return 结果
     */
    @Override
    public int updateCrmComplaint(CrmComplaint crmComplaint)
    {
        crmComplaint.setUpdateTime(DateUtils.getNowDate());
        return crmComplaintMapper.updateCrmComplaint(crmComplaint);
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
        return crmComplaintMapper.deleteCrmComplaintByIds(Convert.toStrArray(ids));
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
        return crmComplaintMapper.deleteCrmComplaintById(complaintId);
    }
}
