package com.jeethink.crm.service.crm1;

import java.util.List;
import com.jeethink.crm.domain.CrmClueFollow;

/**
 * 客户跟进记录Service接口
 * 
 * @author jeethink
 * @date 2020-03-10
 */
public interface ICrmClueFollowService 
{
    /**
     * 查询客户跟进记录
     * 
     * @param followId 客户跟进记录ID
     * @return 客户跟进记录
     */
    public CrmClueFollow selectCrmClueFollowById(Long followId);

    /**
     * 查询客户跟进记录列表
     * 
     * @param crmClueFollow 客户跟进记录
     * @return 客户跟进记录集合
     */
    public List<CrmClueFollow> selectCrmClueFollowList(CrmClueFollow crmClueFollow);
    
    /**
     * 查询客户跟进记录列表
     * 
     * @param crmClueFollow 客户跟进记录
     * @return 客户跟进记录集合
     */
    public List<CrmClueFollow> selectCrmClueFollowListAll(CrmClueFollow crmClueFollow);

    /**
     * 新增客户跟进记录
     * 
     * @param crmClueFollow 客户跟进记录
     * @return 结果
     */
    public int insertCrmClueFollow(CrmClueFollow crmClueFollow);

    /**
     * 修改客户跟进记录
     * 
     * @param crmClueFollow 客户跟进记录
     * @return 结果
     */
    public int updateCrmClueFollow(CrmClueFollow crmClueFollow);

    /**
     * 批量删除客户跟进记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmClueFollowByIds(String ids);

    /**
     * 删除客户跟进记录信息
     * 
     * @param followId 客户跟进记录ID
     * @return 结果
     */
    public int deleteCrmClueFollowById(Long followId);
}
