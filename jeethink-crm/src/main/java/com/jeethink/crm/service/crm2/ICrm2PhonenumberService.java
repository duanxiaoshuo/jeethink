package com.jeethink.crm.service.crm2;

import com.jeethink.crm.domain.CrmPhonenumber;

import java.util.List;

/**
 * 电话本Service接口
 * 
 * @author jeethink
 * @date 2020-03-15
 */
public interface ICrm2PhonenumberService
{
    /**
     * 查询电话本
     * 
     * @param phonenumberId 电话本ID
     * @return 电话本
     */
    public CrmPhonenumber selectCrmPhonenumberById(Long phonenumberId);

    /**
     * 查询电话本列表
     * 
     * @param crmPhonenumber 电话本
     * @return 电话本集合
     */
    public List<CrmPhonenumber> selectCrmPhonenumberList(CrmPhonenumber crmPhonenumber);

    /**
     * 新增电话本
     * 
     * @param crmPhonenumber 电话本
     * @return 结果
     */
    public int insertCrmPhonenumber(CrmPhonenumber crmPhonenumber);

    /**
     * 修改电话本
     * 
     * @param crmPhonenumber 电话本
     * @return 结果
     */
    public int updateCrmPhonenumber(CrmPhonenumber crmPhonenumber);

    /**
     * 批量删除电话本
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmPhonenumberByIds(String ids);

    /**
     * 删除电话本信息
     * 
     * @param phonenumberId 电话本ID
     * @return 结果
     */
    public int deleteCrmPhonenumberById(Long phonenumberId);
}
