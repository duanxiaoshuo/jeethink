package com.jeethink.crm.mapper;

import com.jeethink.crm.domain.CrmShareRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author jeethink
 * @date 2021-01-30
 */
public interface Crm2ShareRelationMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CrmShareRelation selectCrmShareRelationById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param crmShareRelation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CrmShareRelation> selectCrmShareRelationList(CrmShareRelation crmShareRelation);

    /**
     * 新增【请填写功能名称】
     *
     * @param crmShareRelation 【请填写功能名称】
     * @return 结果
     */
    public int insertCrmShareRelation(CrmShareRelation crmShareRelation);

    /**
     * 修改【请填写功能名称】
     *
     * @param crmShareRelation 【请填写功能名称】
     * @return 结果
     */
    public int updateCrmShareRelation(CrmShareRelation crmShareRelation);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCrmShareRelationById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmShareRelationByIds(String[] ids);

    /**
     * 查询与登陆人有关的客户id
     *
     * @param userid
     * @return
     */
    List<Long> selectShareCustomerId(Long userid);

    /**
     * 逻辑删除共享关系
     *
     * @param customerId
     */
    void deleteByCustomerId(@Param("customerId") Long customerId, @Param("updateBy") String updateBy);

    /**
     * 查询是否存在共享关系
     * @param customerId
     * @param share
     * @param shred
     * @return
     */
    CrmShareRelation selectCrmShareRelation(@Param("customerId") Long customerId, @Param("share")Long share,@Param("shred") Long shred);

    /**
     * 查询这个客户的共享关系
     * @param customerId
     * @return
     */
    List<CrmShareRelation> selectCustomer(Long customerId);
}
