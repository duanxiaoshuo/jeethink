package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.CrmPersonMapper;
import com.jeethink.crm.domain.CrmPerson;
import com.jeethink.crm.service.crm1.ICrmPersonService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;

/**
 * 联系人Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-03
 */
@Service
public class CrmPersonServiceImpl implements ICrmPersonService 
{
    @Autowired
    private CrmPersonMapper crmPersonMapper;

    /**
     * 查询联系人
     * 
     * @param personId 联系人ID
     * @return 联系人
     */
    @Override
    public CrmPerson selectCrmPersonById(Long personId)
    {
        return crmPersonMapper.selectCrmPersonById(personId);
    }

    /**
     * 查询联系人列表
     * 
     * @param crmPerson 联系人
     * @return 联系人
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CrmPerson> selectCrmPersonList(CrmPerson crmPerson)
    {
        return crmPersonMapper.selectCrmPersonList(crmPerson);
    }
    
    /**
     * 查询联系人列表
     * 
     * @param crmPerson 联系人
     * @return 联系人
     */
    @Override
    public List<CrmPerson> selectCrmPersonListAll(CrmPerson crmPerson)
    {
        return crmPersonMapper.selectCrmPersonListAll(crmPerson);
    }

    /**
     * 新增联系人
     * 
     * @param crmPerson 联系人
     * @return 结果
     */
    @Override
    public int insertCrmPerson(CrmPerson crmPerson)
    {
    	crmPerson.setDelFlag("0");
        crmPerson.setCreateTime(DateUtils.getNowDate());
        return crmPersonMapper.insertCrmPerson(crmPerson);
    }

    /**
     * 修改联系人
     * 
     * @param crmPerson 联系人
     * @return 结果
     */
    @Override
    public int updateCrmPerson(CrmPerson crmPerson)
    {
        crmPerson.setUpdateTime(DateUtils.getNowDate());
        return crmPersonMapper.updateCrmPerson(crmPerson);
    }

    /**
     * 删除联系人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmPersonByIds(String ids)
    {
        return crmPersonMapper.deleteCrmPersonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除联系人信息
     * 
     * @param personId 联系人ID
     * @return 结果
     */
    @Override
    public int deleteCrmPersonById(Long personId)
    {
        return crmPersonMapper.deleteCrmPersonById(personId);
    }
    
    /**
     * 查询联系人
     * 
     * @param personName 联系人名称
     * @return 联系人
     */
    public CrmPerson selectCrmPersonByName(String personName) {
    	return crmPersonMapper.selectCrmPersonByName(personName);
    }
}
