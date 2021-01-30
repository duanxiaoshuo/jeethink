package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.CrmPhonenumberMapper;
import com.jeethink.crm.domain.CrmPhonenumber;
import com.jeethink.crm.service.crm1.ICrmPhonenumberService;
import com.jeethink.common.core.text.Convert;

/**
 * 电话本Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-15
 */
@Service
public class CrmPhonenumberServiceImpl implements ICrmPhonenumberService 
{
    @Autowired
    private CrmPhonenumberMapper crmPhonenumberMapper;

    /**
     * 查询电话本
     * 
     * @param phonenumberId 电话本ID
     * @return 电话本
     */
    @Override
    public CrmPhonenumber selectCrmPhonenumberById(Long phonenumberId)
    {
        return crmPhonenumberMapper.selectCrmPhonenumberById(phonenumberId);
    }

    /**
     * 查询电话本列表
     * 
     * @param crmPhonenumber 电话本
     * @return 电话本
     */
    @Override
    public List<CrmPhonenumber> selectCrmPhonenumberList(CrmPhonenumber crmPhonenumber)
    {
        return crmPhonenumberMapper.selectCrmPhonenumberList(crmPhonenumber);
    }

    /**
     * 新增电话本
     * 
     * @param crmPhonenumber 电话本
     * @return 结果
     */
    @Override
    public int insertCrmPhonenumber(CrmPhonenumber crmPhonenumber)
    {
    	crmPhonenumber.setDelFlag("0");
        crmPhonenumber.setCreateTime(DateUtils.getNowDate());
        return crmPhonenumberMapper.insertCrmPhonenumber(crmPhonenumber);
    }

    /**
     * 修改电话本
     * 
     * @param crmPhonenumber 电话本
     * @return 结果
     */
    @Override
    public int updateCrmPhonenumber(CrmPhonenumber crmPhonenumber)
    {
        crmPhonenumber.setUpdateTime(DateUtils.getNowDate());
        return crmPhonenumberMapper.updateCrmPhonenumber(crmPhonenumber);
    }

    /**
     * 删除电话本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmPhonenumberByIds(String ids)
    {
        return crmPhonenumberMapper.deleteCrmPhonenumberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除电话本信息
     * 
     * @param phonenumberId 电话本ID
     * @return 结果
     */
    @Override
    public int deleteCrmPhonenumberById(Long phonenumberId)
    {
        return crmPhonenumberMapper.deleteCrmPhonenumberById(phonenumberId);
    }
}
