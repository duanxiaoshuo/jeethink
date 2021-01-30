package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.CrmClueFollow;

/**
 * 线索跟进记录Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-10
 */
public interface CrmClueFollowMapper 
{
    /**
     * 查询线索跟进记录
     * 
     * @param followId 线索跟进记录ID
     * @return 线索跟进记录
     */
    public CrmClueFollow selectCrmClueFollowById(Long followId);

    /**
     * 查询线索跟进记录列表
     * 
     * @param crmFollow 线索跟进记录
     * @return 线索跟进记录集合
     */
    public List<CrmClueFollow> selectCrmClueFollowList(CrmClueFollow crmFollow);
    
    /**
     * 查询线索跟进记录列表 无数据权限控制
     * 
     * @param crmFollow 线索跟进记录
     * @return 线索跟进记录集合
     */
    public List<CrmClueFollow> selectCrmClueFollowListAll(CrmClueFollow crmFollow);

    /**
     * 新增线索跟进记录
     * 
     * @param crmFollow 线索跟进记录
     * @return 结果
     */
    public int insertCrmClueFollow(CrmClueFollow crmFollow);

    /**
     * 修改线索跟进记录
     * 
     * @param crmFollow 线索跟进记录
     * @return 结果
     */
    public int updateCrmClueFollow(CrmClueFollow crmFollow);

    /**
     * 删除线索跟进记录
     * 
     * @param followId 线索跟进记录ID
     * @return 结果
     */
    public int deleteCrmClueFollowById(Long followId);

    /**
     * 批量删除线索跟进记录
     * 
     * @param followIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmClueFollowByIds(String[] followIds);
}
