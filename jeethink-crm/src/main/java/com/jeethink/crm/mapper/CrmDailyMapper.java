package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.CrmDaily;

/**
 * 日志管理Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-15
 */
public interface CrmDailyMapper 
{
    /**
     * 查询日志管理
     * 
     * @param dailyId 日志管理ID
     * @return 日志管理
     */
    public CrmDaily selectCrmDailyById(Long dailyId);

    /**
     * 查询日志管理列表
     * 
     * @param crmDaily 日志管理
     * @return 日志管理集合
     */
    public List<CrmDaily> selectCrmDailyList(CrmDaily crmDaily);

    /**
     * 新增日志管理
     * 
     * @param crmDaily 日志管理
     * @return 结果
     */
    public int insertCrmDaily(CrmDaily crmDaily);

    /**
     * 修改日志管理
     * 
     * @param crmDaily 日志管理
     * @return 结果
     */
    public int updateCrmDaily(CrmDaily crmDaily);

    /**
     * 删除日志管理
     * 
     * @param dailyId 日志管理ID
     * @return 结果
     */
    public int deleteCrmDailyById(Long dailyId);

    /**
     * 批量删除日志管理
     * 
     * @param dailyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmDailyByIds(String[] dailyIds);
}
