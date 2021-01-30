package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.CrmDailyMapper;
import com.jeethink.crm.domain.CrmDaily;
import com.jeethink.crm.service.crm1.ICrmDailyService;
import com.jeethink.common.core.text.Convert;

/**
 * 日志管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-15
 */
@Service
public class CrmDailyServiceImpl implements ICrmDailyService 
{
    @Autowired
    private CrmDailyMapper crmDailyMapper;

    /**
     * 查询日志管理
     * 
     * @param dailyId 日志管理ID
     * @return 日志管理
     */
    @Override
    public CrmDaily selectCrmDailyById(Long dailyId)
    {
        return crmDailyMapper.selectCrmDailyById(dailyId);
    }

    /**
     * 查询日志管理列表
     * 
     * @param crmDaily 日志管理
     * @return 日志管理
     */
    @Override
    public List<CrmDaily> selectCrmDailyList(CrmDaily crmDaily)
    {
        return crmDailyMapper.selectCrmDailyList(crmDaily);
    }

    /**
     * 新增日志管理
     * 
     * @param crmDaily 日志管理
     * @return 结果
     */
    @Override
    public int insertCrmDaily(CrmDaily crmDaily)
    {
    	crmDaily.setDelFlag("0");
        crmDaily.setCreateTime(DateUtils.getNowDate());
        return crmDailyMapper.insertCrmDaily(crmDaily);
    }

    /**
     * 修改日志管理
     * 
     * @param crmDaily 日志管理
     * @return 结果
     */
    @Override
    public int updateCrmDaily(CrmDaily crmDaily)
    {
        crmDaily.setUpdateTime(DateUtils.getNowDate());
        return crmDailyMapper.updateCrmDaily(crmDaily);
    }

    /**
     * 删除日志管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmDailyByIds(String ids)
    {
        return crmDailyMapper.deleteCrmDailyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除日志管理信息
     * 
     * @param dailyId 日志管理ID
     * @return 结果
     */
    @Override
    public int deleteCrmDailyById(Long dailyId)
    {
        return crmDailyMapper.deleteCrmDailyById(dailyId);
    }
}
