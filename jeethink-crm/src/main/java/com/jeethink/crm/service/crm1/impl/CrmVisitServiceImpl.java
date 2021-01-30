package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.CrmVisitMapper;
import com.jeethink.crm.domain.CrmVisit;
import com.jeethink.crm.service.crm1.ICrmVisitService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;

/**
 * 客户拜访Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-10
 */
@Service
public class CrmVisitServiceImpl implements ICrmVisitService 
{
    @Autowired
    private CrmVisitMapper crmVisitMapper;

    /**
     * 查询客户拜访
     * 
     * @param visitId 客户拜访ID
     * @return 客户拜访
     */
    @Override
    public CrmVisit selectCrmVisitById(Long visitId)
    {
        return crmVisitMapper.selectCrmVisitById(visitId);
    }

    /**
     * 查询客户拜访列表
     * 
     * @param crmVisit 客户拜访
     * @return 客户拜访
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CrmVisit> selectCrmVisitList(CrmVisit crmVisit)
    {
        return crmVisitMapper.selectCrmVisitList(crmVisit);
    }
    
    /**
     * 查询客户拜访列表
     * 
     * @param crmVisit 客户拜访
     * @return 客户拜访
     */
    @Override
    public List<CrmVisit> selectCrmVisitListAll(CrmVisit crmVisit)
    {
        return crmVisitMapper.selectCrmVisitListAll(crmVisit);
    }

    /**
     * 新增客户拜访
     * 
     * @param crmVisit 客户拜访
     * @return 结果
     */
    @Override
    public int insertCrmVisit(CrmVisit crmVisit)
    {
    	crmVisit.setDelFlag("0");
        crmVisit.setCreateTime(DateUtils.getNowDate());
        return crmVisitMapper.insertCrmVisit(crmVisit);
    }

    /**
     * 修改客户拜访
     * 
     * @param crmVisit 客户拜访
     * @return 结果
     */
    @Override
    public int updateCrmVisit(CrmVisit crmVisit)
    {
        crmVisit.setUpdateTime(DateUtils.getNowDate());
        return crmVisitMapper.updateCrmVisit(crmVisit);
    }

    /**
     * 删除客户拜访对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmVisitByIds(String ids)
    {
        return crmVisitMapper.deleteCrmVisitByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户拜访信息
     * 
     * @param visitId 客户拜访ID
     * @return 结果
     */
    @Override
    public int deleteCrmVisitById(Long visitId)
    {
        return crmVisitMapper.deleteCrmVisitById(visitId);
    }
}
