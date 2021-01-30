package com.jeethink.crm.mapper;

import java.util.List;

import com.jeethink.crm.domain.CrmCustomer;
import org.apache.ibatis.annotations.Param;

/**
 * 客户Mapper接口
 *
 * @author jeethink
 * @date 2020-03-01
 */
public interface CrmCustomerMapper {
    /**
     * 查询客户
     *
     * @param customerId 客户ID
     * @return 客户
     */
    public CrmCustomer selectCrmCustomerById(Long customerId);

    /**
     * 查询客户
     *
     * @param customerName 客户名称
     * @return 客户
     */
    public CrmCustomer selectCrmCustomerByName(String customerName);

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
    public List<CrmCustomer> selectCrmCustomerListShare(@Param("customerIds") List<Long> customerIds,@Param("crmCustomer")  CrmCustomer crmCustomer);

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
     * 删除客户
     *
     * @param customerId 客户ID
     * @return 结果
     */
    public int deleteCrmCustomerById(Long customerId);

    /**
     * 批量删除客户
     *
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmCustomerByIds(String[] customerIds);

    /**
     * 校验Email是否唯一
     *
     * @param email 客户信息
     * @return
     */
    public CrmCustomer checkEmailUnique(String email);

    /**
     * 校验座机是否唯一
     *
     * @param telephone 客户信息
     * @return
     */
    public CrmCustomer checkTelephoneUnique(String telephone);

    /**
     * 校验手机是否唯一
     *
     * @param mobile 客户信息
     * @return
     */
    public CrmCustomer checkMobileUnique(String mobile);

    /**
     * 查询超过30天未跟进的客户
     *
     * @return 客户
     */
    public List<CrmCustomer> selectCrmCustomerListFollowMoreThan30();

    /**
     * 查询未跟进过的客户
     *
     * @return 客户
     */
    public List<CrmCustomer> selectCrmCustomerListNoFollow();
}
