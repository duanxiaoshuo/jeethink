package com.jeethink.crm.service.crm2;

import com.jeethink.crm.domain.CrmClue;

import java.util.List;

/**
 * 线索管理Service接口
 * 
 * @author jeethink
 * @date 2020-04-06
 */
public interface ICrm2ClueService
{
    /**
     * 查询线索管理
     * 
     * @param clueId 线索管理ID
     * @return 线索管理
     */
    public CrmClue selectCrmClueById(Long clueId);

    /**
     * 查询线索管理列表
     * 
     * @param crmClue 线索管理
     * @return 线索管理集合
     */
    public List<CrmClue> selectCrmClueList(CrmClue crmClue);
    
    /**
     * 查询线索管理列表 公共线索
     * 
     * @param crmClue 线索管理
     * @return 线索管理集合
     */
    public List<CrmClue> selectCrmClueListPublic(CrmClue crmClue);

    /**
     * 新增线索管理
     * 
     * @param crmClue 线索管理
     * @return 结果
     */
    public int insertCrmClue(CrmClue crmClue);

    /**
     * 修改线索管理
     * 
     * @param crmClue 线索管理
     * @return 结果
     */
    public int updateCrmClue(CrmClue crmClue);

    /**
     * 批量删除线索管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmClueByIds(String ids);

    /**
     * 删除线索管理信息
     * 
     * @param clueId 线索管理ID
     * @return 结果
     */
    public int deleteCrmClueById(Long clueId);
    
    /**
     * 线索管理 作废
     * 
     * @param clueId 线索管理ID
     * @param isCancel 0  1
     * @return 结果
     */
    public int cancelCrmClueById(Long clueId,String isCancel);
    
    /**
     * 线索管理 转成客户
     * 
     * @param clueId 线索管理ID
     * @return 结果
     */
    public int convertCrmClueById(Long clueId,String loginName);
    
    
    /**
     * 导入线索数据
     * 
     * @param clueList 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importClue(List<CrmClue> customerList, Boolean isUpdateSupport, String operName);
    
    /**
     * 校验手机号码是否唯一
    *
    * @param crmClue 线索信息
    * @return
    */
   public String checkMobileUnique(CrmClue crmClue);
   
   /**
    * 将线索转如线索池
    */
   public void convertCrmClueToPublic();
}
