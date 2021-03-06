package com.jeethink.crm.service.crm1;

import java.util.List;
import com.jeethink.crm.domain.CrmFollow;

/**
 * 客户跟进记录Service接口
 * 
 * @author jeethink
 * @date 2020-03-10
 */
public interface ICrmFollowService 
{
    /**
     * 查询客户跟进记录
     * 
     * @param followId 客户跟进记录ID
     * @return 客户跟进记录
     */
    public CrmFollow selectCrmFollowById(Long followId);

    /**
     * 查询客户跟进记录列表
     * 
     * @param crmFollow 客户跟进记录
     * @return 客户跟进记录集合
     */
    public List<CrmFollow> selectCrmFollowList(CrmFollow crmFollow);
    
    /**
     * 查询客户跟进记录列表
     * 
     * @param crmFollow 客户跟进记录
     * @return 客户跟进记录集合
     */
    public List<CrmFollow> selectCrmFollowListAll(CrmFollow crmFollow);

    /**
     * 新增客户跟进记录
     * 
     * @param crmFollow 客户跟进记录
     * @return 结果
     */
    public int insertCrmFollow(CrmFollow crmFollow);

    /**
     * 修改客户跟进记录
     * 
     * @param crmFollow 客户跟进记录
     * @return 结果
     */
    public int updateCrmFollow(CrmFollow crmFollow);

    /**
     * 批量删除客户跟进记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmFollowByIds(String ids);

    /**
     * 删除客户跟进记录信息
     * 
     * @param followId 客户跟进记录ID
     * @return 结果
     */
    public int deleteCrmFollowById(Long followId);
}
