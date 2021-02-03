package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.Crm2Person;
import com.jeethink.crm.domain.CrmPerson;
import com.jeethink.crm.mapper.Crm2PersonMapper;
import com.jeethink.crm.service.crm2.ICrm2PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联系人Service业务层处理
 *
 * @author jeethink
 * @date 2020-03-03
 */
@Service
public class Crm2PersonServiceImpl implements ICrm2PersonService {
    @Autowired
    private Crm2PersonMapper crm2PersonMapper;

    /**
     * 查询联系人
     *
     * @param personId 联系人ID
     * @return 联系人
     */
    @Override
    public Crm2Person selectCrmPersonById(Long personId) {
        return crm2PersonMapper.selectCrmPersonById(personId);
    }

    /**
     * 查询联系人列表
     *
     * @param crmPerson 联系人
     * @return 联系人
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Crm2Person> selectCrmPersonList(Crm2Person crmPerson) {
        return crm2PersonMapper.selectCrmPersonList(crmPerson);
    }

    /**
     * 查询联系人列表
     *
     * @param crmPerson 联系人
     * @return 联系人
     */
    @Override
    public List<Crm2Person> selectCrmPersonListAll(Crm2Person crmPerson) {
        return crm2PersonMapper.selectCrmPersonListAll(crmPerson);
    }

    /**
     * 新增联系人
     *
     * @param crmPerson 联系人
     * @return 结果
     */
    @Override
    public int insertCrmPerson(Crm2Person crmPerson) {
        crmPerson.setDelFlag("0");
        crmPerson.setCreateTime(DateUtils.getNowDate());
        return crm2PersonMapper.insertCrmPerson(crmPerson);
    }

    /**
     * 修改联系人
     *
     * @param crmPerson 联系人
     * @return 结果
     */
    @Override
    public int updateCrmPerson(Crm2Person crmPerson) {
        crmPerson.setUpdateTime(DateUtils.getNowDate());
        return crm2PersonMapper.updateCrmPerson(crmPerson);
    }

    /**
     * 删除联系人对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmPersonByIds(String ids) {
        return crm2PersonMapper.deleteCrmPersonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除联系人信息
     *
     * @param personId 联系人ID
     * @return 结果
     */
    @Override
    public int deleteCrmPersonById(Long personId) {
        return crm2PersonMapper.deleteCrmPersonById(personId);
    }

    /**
     * 查询联系人
     *
     * @param personName 联系人名称
     * @return 联系人
     */
    public Crm2Person selectCrmPersonByName(String personName) {
        return crm2PersonMapper.selectCrmPersonByName(personName);
    }
}
