package com.jeethink.crm.service.crm2;

import com.jeethink.crm.domain.Crm2Complaint;
import com.jeethink.crm.domain.CrmComplaint;

import java.util.List;

/**
 * 客户投诉Service接口
 * 
 * @author jeethink
 * @date 2020-03-09
 */
public interface ICrm2ComplaintService
{
    /**
     * 查询客户投诉
     *
     * @param complaintId 客户投诉ID
     * @return 客户投诉
     */
    public Crm2Complaint selectCrmComplaintById(Long complaintId);

    /**
     * 查询客户投诉列表
     * 
     * @param crmComplaint 客户投诉
     * @return 客户投诉集合
     */
    public List<Crm2Complaint> selectCrmComplaintList(Crm2Complaint crmComplaint);
    
    /**
     * 查询客户投诉列表
     * 
     * @param crmComplaint 客户投诉
     * @return 客户投诉集合
     */
    public List<CrmComplaint> selectCrmComplaintListAll(CrmComplaint crmComplaint);

    /**
     * 新增客户投诉
     * 
     * @param crmComplaint 客户投诉
     * @return 结果
     */
    public int insertCrmComplaint(Crm2Complaint crmComplaint);

    /**
     * 修改客户投诉
     * 
     * @param crmComplaint 客户投诉
     * @return 结果
     */
    public int updateCrmComplaint(Crm2Complaint crmComplaint);

    /**
     * 批量删除客户投诉
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmComplaintByIds(String ids);

    /**
     * 删除客户投诉信息
     * 
     * @param complaintId 客户投诉ID
     * @return 结果
     */
    public int deleteCrmComplaintById(Long complaintId);
}
