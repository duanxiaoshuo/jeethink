package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.CrmFollow;
import com.jeethink.crm.mapper.Crm2FollowMapper;
import com.jeethink.crm.service.crm2.ICrm2FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户跟进记录Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-10
 */
@Service
public class Crm2FollowServiceImpl implements ICrm2FollowService
{
    @Autowired
    private Crm2FollowMapper crm2FollowMapper;

    /**
     * 查询客户跟进记录
     * 
     * @param followId 客户跟进记录ID
     * @return 客户跟进记录
     */
    @Override
    public CrmFollow selectCrmFollowById(Long followId)
    {
        return crm2FollowMapper.selectCrmFollowById(followId);
    }

    /**
     * 查询客户跟进记录列表
     * 
     * @param crmFollow 客户跟进记录
     * @return 客户跟进记录
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CrmFollow> selectCrmFollowList(CrmFollow crmFollow)
    {
        return crm2FollowMapper.selectCrmFollowList(crmFollow);
    }
    
    /**
     * 查询客户跟进记录列表
     * 
     * @param crmFollow 客户跟进记录
     * @return 客户跟进记录
     */
    @Override
    public List<CrmFollow> selectCrmFollowListAll(CrmFollow crmFollow)
    {
        return crm2FollowMapper.selectCrmFollowListAll(crmFollow);
    }

    /**
     * 新增客户跟进记录
     * 
     * @param crmFollow 客户跟进记录
     * @return 结果
     */
    @Override
    public int insertCrmFollow(CrmFollow crmFollow)
    {
    	crmFollow.setDelFlag("0");
        crmFollow.setCreateTime(DateUtils.getNowDate());
        return crm2FollowMapper.insertCrmFollow(crmFollow);
    }

    /**
     * 修改客户跟进记录
     * 
     * @param crmFollow 客户跟进记录
     * @return 结果
     */
    @Override
    public int updateCrmFollow(CrmFollow crmFollow)
    {
        crmFollow.setUpdateTime(DateUtils.getNowDate());
        return crm2FollowMapper.updateCrmFollow(crmFollow);
    }

    /**
     * 删除客户跟进记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmFollowByIds(String ids)
    {
        return crm2FollowMapper.deleteCrmFollowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户跟进记录信息
     * 
     * @param followId 客户跟进记录ID
     * @return 结果
     */
    @Override
    public int deleteCrmFollowById(Long followId)
    {
        return crm2FollowMapper.deleteCrmFollowById(followId);
    }
}
