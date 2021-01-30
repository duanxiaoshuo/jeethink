package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.CrmVisit;
import com.jeethink.crm.mapper.Crm2VisitMapper;
import com.jeethink.crm.service.crm2.ICrm2VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户拜访Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-10
 */
@Service
public class Crm2VisitServiceImpl implements ICrm2VisitService
{
    @Autowired
    private Crm2VisitMapper crm2VisitMapper;

    /**
     * 查询客户拜访
     * 
     * @param visitId 客户拜访ID
     * @return 客户拜访
     */
    @Override
    public CrmVisit selectCrmVisitById(Long visitId)
    {
        return crm2VisitMapper.selectCrmVisitById(visitId);
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
        return crm2VisitMapper.selectCrmVisitList(crmVisit);
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
        return crm2VisitMapper.selectCrmVisitListAll(crmVisit);
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
        return crm2VisitMapper.insertCrmVisit(crmVisit);
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
        return crm2VisitMapper.updateCrmVisit(crmVisit);
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
        return crm2VisitMapper.deleteCrmVisitByIds(Convert.toStrArray(ids));
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
        return crm2VisitMapper.deleteCrmVisitById(visitId);
    }
}
