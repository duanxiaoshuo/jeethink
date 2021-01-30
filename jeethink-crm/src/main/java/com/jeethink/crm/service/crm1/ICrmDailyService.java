package com.jeethink.crm.service.crm1;

import java.util.List;
import com.jeethink.crm.domain.CrmDaily;

/**
 * 日志管理Service接口
 * 
 * @author jeethink
 * @date 2020-03-15
 */
public interface ICrmDailyService 
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
     * 批量删除日志管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmDailyByIds(String ids);

    /**
     * 删除日志管理信息
     * 
     * @param dailyId 日志管理ID
     * @return 结果
     */
    public int deleteCrmDailyById(Long dailyId);
}
