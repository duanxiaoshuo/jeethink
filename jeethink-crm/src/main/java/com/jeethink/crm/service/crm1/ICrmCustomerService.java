package com.jeethink.crm.service.crm1;

import java.util.List;

import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmCustomerTemplate;

/**
 * 客户Service接口
 * 
 * @author jeethink
 * @date 2020-03-01
 */
public interface ICrmCustomerService 
{
    /**
     * 查询客户
     * 
     * @param customerId 客户ID
     * @return 客户
     */
    public CrmCustomer selectCrmCustomerById(Long customerId);

    /**
     * 查询客户列表
     * 
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<CrmCustomer> selectCrmCustomerList(CrmCustomer crmCustomer);
    
    /**
     * 查询客户列表 我的
     * 
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<CrmCustomer> selectCrmCustomerListMy(CrmCustomer crmCustomer);
    
    /**
     * 查询客户列表 共享
     * 
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<CrmCustomer> selectCrmCustomerListShare(CrmCustomer crmCustomer,Long userId);

    /**
     * 查询客户列表 公共
     *
     * @param crmCustomer 客户
     * @return 客户集合
     */
    public List<CrmCustomer> selectCrmCustomerListPublic(CrmCustomer crmCustomer);


    /**
     * 新增客户
     *
     * @param crmCustomer 客户
     * @return 结果
     */
    public int insertCrmCustomer(CrmCustomer crmCustomer);

    /**
     * 修改客户
     *
     * @param crmCustomer 客户
     * @return 结果
     */
    public int updateCrmCustomer(CrmCustomer crmCustomer);

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
    public int shareCrmCustomerByIds(String ids,String isShare, String operName,Long share,Long shared,String updateBy);
    
    /**
     * 导入客户数据
     * 
     * @param customerList 客户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importCustomer(List<CrmCustomerTemplate> customerList, Boolean isUpdateSupport, String operName);
    
    /**
     * 校验客户名称是否唯一
    *
    * @param crmCustomer 客户信息
    * @return
    */
    public String checkCustomerNameUnique(CrmCustomer crmCustomer);
    
    /**
     * 校验Email是否唯一
    *
    * @param crmCustomer 客户信息
    * @return
    */
    public String checkEmailUnique(CrmCustomer crmCustomer);
    
    /**
     * 校验座机是否唯一
    *
    * @param crmCustomer 客户信息
    * @return
    */
    public String checkTelephoneUnique(CrmCustomer crmCustomer);
    
    /**
     * 校验手机是否唯一
    *
    * @param crmCustomer 客户信息
    * @return
    */
    public String checkMobileUnique(CrmCustomer crmCustomer);
    
    /**
     * 将客户转为公告客户
     */
    public void convertCrmCustomerToPublic();

}
