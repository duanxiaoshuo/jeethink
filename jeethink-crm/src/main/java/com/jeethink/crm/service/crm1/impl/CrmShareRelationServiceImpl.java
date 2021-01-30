package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.CrmShareRelation;
import com.jeethink.crm.mapper.CrmShareRelationMapper;
import com.jeethink.crm.service.crm1.ICrmShareRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeethink.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author jeethink
 * @date 2021-01-30
 */
@Service
public class CrmShareRelationServiceImpl implements ICrmShareRelationService
{
    @Autowired
    private CrmShareRelationMapper crmShareRelationMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CrmShareRelation selectCrmShareRelationById(Long id)
    {
        return crmShareRelationMapper.selectCrmShareRelationById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param crmShareRelation 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CrmShareRelation> selectCrmShareRelationList(CrmShareRelation crmShareRelation)
    {
        return crmShareRelationMapper.selectCrmShareRelationList(crmShareRelation);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param crmShareRelation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCrmShareRelation(CrmShareRelation crmShareRelation)
    {
        crmShareRelation.setCreateTime(DateUtils.getNowDate());
        return crmShareRelationMapper.insertCrmShareRelation(crmShareRelation);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param crmShareRelation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCrmShareRelation(CrmShareRelation crmShareRelation)
    {
        crmShareRelation.setUpdateTime(DateUtils.getNowDate());
        return crmShareRelationMapper.updateCrmShareRelation(crmShareRelation);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmShareRelationByIds(String ids)
    {
        return crmShareRelationMapper.deleteCrmShareRelationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCrmShareRelationById(Long id)
    {
        return crmShareRelationMapper.deleteCrmShareRelationById(id);
    }
}
