package com.jeethink.crm.mapper;

import com.jeethink.crm.domain.Crm2Person;
import com.jeethink.crm.domain.CrmPerson;

import java.util.List;

/**
 * 联系人Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-03
 */
public interface Crm2PersonMapper
{
    /**
     * 查询联系人
     * 
     * @param personId 联系人ID
     * @return 联系人
     */
    public Crm2Person selectCrmPersonById(Long personId);

    /**
     * 查询联系人列表
     * 
     * @param crmPerson 联系人
     * @return 联系人集合
     */
    public List<Crm2Person> selectCrmPersonList(Crm2Person crmPerson);
    
    /**
     * 查询联系人列表 无数据权限控制
     * 
     * @param crmPerson 联系人
     * @return 联系人集合
     */
    public List<Crm2Person> selectCrmPersonListAll(Crm2Person crmPerson);


    /**
     * 新增联系人
     * 
     * @param crmPerson 联系人
     * @return 结果
     */
    public int insertCrmPerson(Crm2Person crmPerson);

    /**
     * 修改联系人
     * 
     * @param crmPerson 联系人
     * @return 结果
     */
    public int updateCrmPerson(Crm2Person crmPerson);

    /**
     * 删除联系人
     * 
     * @param personId 联系人ID
     * @return 结果
     */
    public int deleteCrmPersonById(Long personId);

    /**
     * 批量删除联系人
     * 
     * @param personIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmPersonByIds(String[] personIds);
    
    /**
     * 查询联系人
     * 
     * @param personName 联系人名称
     * @return 联系人
     */
    public Crm2Person selectCrmPersonByName(String personName);
}
