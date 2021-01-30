package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.CrmVisit;

/**
 * 客户拜访Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-10
 */
public interface CrmVisitMapper 
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
     * 查询客户拜访列表  无数据权限控制
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
     * 删除客户拜访
     * 
     * @param visitId 客户拜访ID
     * @return 结果
     */
    public int deleteCrmVisitById(Long visitId);

    /**
     * 批量删除客户拜访
     * 
     * @param visitIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmVisitByIds(String[] visitIds);
}
