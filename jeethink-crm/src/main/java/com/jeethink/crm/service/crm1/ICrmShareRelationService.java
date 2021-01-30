package com.jeethink.crm.service.crm1;

import com.jeethink.crm.domain.CrmShareRelation;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author jeethink
 * @date 2021-01-30
 */
public interface ICrmShareRelationService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CrmShareRelation selectCrmShareRelationById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param crmShareRelation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CrmShareRelation> selectCrmShareRelationList(CrmShareRelation crmShareRelation);

    /**
     * 新增【请填写功能名称】
     * 
     * @param crmShareRelation 【请填写功能名称】
     * @return 结果
     */
    public int insertCrmShareRelation(CrmShareRelation crmShareRelation);

    /**
     * 修改【请填写功能名称】
     * 
     * @param crmShareRelation 【请填写功能名称】
     * @return 结果
     */
    public int updateCrmShareRelation(CrmShareRelation crmShareRelation);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmShareRelationByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCrmShareRelationById(Long id);
}
