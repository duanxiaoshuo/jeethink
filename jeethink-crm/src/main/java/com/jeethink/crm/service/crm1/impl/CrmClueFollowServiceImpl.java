package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.CrmClueFollowMapper;
import com.jeethink.crm.domain.CrmClueFollow;
import com.jeethink.crm.service.crm1.ICrmClueFollowService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;

/**
 * 客户跟进记录Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-10
 */
@Service
public class CrmClueFollowServiceImpl implements ICrmClueFollowService 
{
    @Autowired
    private CrmClueFollowMapper crmClueFollowMapper;

    /**
     * 查询客户跟进记录
     * 
     * @param followId 客户跟进记录ID
     * @return 客户跟进记录
     */
    @Override
    public CrmClueFollow selectCrmClueFollowById(Long followId)
    {
        return crmClueFollowMapper.selectCrmClueFollowById(followId);
    }

    /**
     * 查询客户跟进记录列表
     * 
     * @param crmFollow 客户跟进记录
     * @return 客户跟进记录
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CrmClueFollow> selectCrmClueFollowList(CrmClueFollow crmFollow)
    {
        return crmClueFollowMapper.selectCrmClueFollowList(crmFollow);
    }
    
    /**
     * 查询客户跟进记录列表
     * 
     * @param crmFollow 客户跟进记录
     * @return 客户跟进记录
     */
    @Override
    public List<CrmClueFollow> selectCrmClueFollowListAll(CrmClueFollow crmFollow)
    {
        return crmClueFollowMapper.selectCrmClueFollowListAll(crmFollow);
    }

    /**
     * 新增客户跟进记录
     * 
     * @param crmFollow 客户跟进记录
     * @return 结果
     */
    @Override
    public int insertCrmClueFollow(CrmClueFollow crmFollow)
    {
    	crmFollow.setDelFlag("0");
        crmFollow.setCreateTime(DateUtils.getNowDate());
        return crmClueFollowMapper.insertCrmClueFollow(crmFollow);
    }

    /**
     * 修改客户跟进记录
     * 
     * @param crmFollow 客户跟进记录
     * @return 结果
     */
    @Override
    public int updateCrmClueFollow(CrmClueFollow crmFollow)
    {
        crmFollow.setUpdateTime(DateUtils.getNowDate());
        return crmClueFollowMapper.updateCrmClueFollow(crmFollow);
    }

    /**
     * 删除客户跟进记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmClueFollowByIds(String ids)
    {
        return crmClueFollowMapper.deleteCrmClueFollowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户跟进记录信息
     * 
     * @param followId 客户跟进记录ID
     * @return 结果
     */
    @Override
    public int deleteCrmClueFollowById(Long followId)
    {
        return crmClueFollowMapper.deleteCrmClueFollowById(followId);
    }
}
