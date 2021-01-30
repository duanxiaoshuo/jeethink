package com.jeethink.crm.service.crm2;

import com.jeethink.crm.domain.CrmVisit;

import java.util.List;

/**
 * 客户拜访Service接口
 * 
 * @author jeethink
 * @date 2020-03-10
 */
public interface ICrm2VisitService
{
    /**
     * 查询客户拜访
     * 
     * @param visitId 客户拜访ID
     * @return 客户拜访
     */
    public CrmVisit selectCrmVisitById(Long visitId);

    /**
     * 查询客户拜访列表
     * 
     * @param crmVisit 客户拜访
     * @return 客户拜访集合
     */
    public List<CrmVisit> selectCrmVisitList(CrmVisit crmVisit);
    
    /**
     * 查询客户拜访列表
     * 
     * @param crmVisit 客户拜访
     * @return 客户拜访集合
     */
    public List<CrmVisit> selectCrmVisitListAll(CrmVisit crmVisit);

    /**
     * 新增客户拜访
     * 
     * @param crmVisit 客户拜访
     * @return 结果
     */
    public int insertCrmVisit(CrmVisit crmVisit);

    /**
     * 修改客户拜访
     * 
     * @param crmVisit 客户拜访
     * @return 结果
     */
    public int updateCrmVisit(CrmVisit crmVisit);

    /**
     * 批量删除客户拜访
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmVisitByIds(String ids);

    /**
     * 删除客户拜访信息
     * 
     * @param visitId 客户拜访ID
     * @return 结果
     */
    public int deleteCrmVisitById(Long visitId);
}
