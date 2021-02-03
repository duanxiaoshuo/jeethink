package com.jeethink.crm.service.crm2;

import com.jeethink.crm.domain.Crm2Customer;
import com.jeethink.crm.domain.Crm2CustomerTemplate;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmCustomerTemplate;

import java.util.List;

/**
 * 客户Service接口
 * 
 * @author jeethink
 * @date 2020-03-01
 */
public interface ICrm2CustomerService
{
    /**
     * 查询客户
     * 
     * @param customerId 客户ID
     * @return 客户
     */
    public Crm2Customer selectCrmCustomerById(Long customerId);

    /**
     * 查询客户列表
     * 
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<Crm2Customer> selectCrmCustomerList(Crm2Customer crmCustomer);
    
    /**
     * 查询客户列表 我的
     * 
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<Crm2Customer> selectCrmCustomerListMy(Crm2Customer crmCustomer);
    
    /**
     * 查询客户列表 共享
     * 
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<Crm2Customer> selectCrmCustomerListShare(Crm2Customer crmCustomer,Long userId);

    
    /**
     * 查询客户列表 公共
     * 
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<Crm2Customer> selectCrmCustomerListPublic(Crm2Customer crmCustomer);
    

    /**
     * 新增客户
     * 
     * @param crmCustomer 客户
     * @return 结果
     */
    public int insertCrmCustomer(Crm2Customer crmCustomer);

    /**
     * 修改客户
     * 
     * @param crmCustomer 客户
     * @return 结果
     */
    public int updateCrmCustomer(Crm2Customer crmCustomer);

    /**
     * 批量删除客户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmCustomerByIds(String ids);

    /**
     * 删除客户信息
     * 
     * @param customerId 客户ID
     * @return 结果
     */
    public int deleteCrmCustomerById(Long customerId);

    /**
     * 批量分享客户
     *
     * @param ids 需要分享的数据ID
     * @param isShare 是否分享
     * @param operName 操作人
     * @return 结果
     */
    public int shareCrmCustomerByIds(Long ids,String isShare, String operName,Long share,Long shared,String updateBy);

    /**
     * 导入客户数据
     * 
     * @param customerList 客户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importCustomer(List<Crm2CustomerTemplate> customerList, Boolean isUpdateSupport, String operName);
    
    /**
     * 校验客户名称是否唯一
    *
    * @param crmCustomer 客户信息
    * @return
    */
    public String checkCustomerNameUnique(Crm2Customer crmCustomer);
    
    /**
     * 校验Email是否唯一
    *
    * @param crmCustomer 客户信息
    * @return
    */
    public String checkEmailUnique(Crm2Customer crmCustomer);
    
    /**
     * 校验座机是否唯一
    *
    * @param crmCustomer 客户信息
    * @return
    */
    public String checkTelephoneUnique(Crm2Customer crmCustomer);
    
    /**
     * 校验手机是否唯一
    *
    * @param crmCustomer 客户信息
    * @return
    */
    public String checkMobileUnique(Crm2Customer crmCustomer);
    
    /**
     * 将客户转为公告客户
     */
    public void convertCrmCustomerToPublic();

}
