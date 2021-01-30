package com.jeethink.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeethink.crm.domain.CrmClue;

/**
 * 线索管理Mapper接口
 * 
 * @author jeethink
 * @date 2020-04-06
 */
public interface CrmClueMapper 
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
     * 删除线索管理
     * 
     * @param clueId 线索管理ID
     * @return 结果
     */
    public int deleteCrmClueById(Long clueId);

    /**
     * 批量删除线索管理
     * 
     * @param clueIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmClueByIds(String[] clueIds);
    
    /**
     * 线索管理 作废
     * 
     * @param clueId 线索管理ID
     * @param isCancel 0  1
     * @return 结果
     */
    public int cancelCrmClueById(@Param("clueId") Long clueId,@Param("isCancel") String isCancel);
    
    /**
     * 查询线索管理
     * 
     * @param mobile 手机号
     * @return 线索管理
     */
    public CrmClue selectCrmClueByMobile(String mobile);
    
    /**
     * 查询超过30天未跟进的线索
     * 
     * @return 线索
     */
    public List<CrmClue> selectCrmClueListFollowMoreThan30();
    
    /**
     * 查询未跟进过的线索
     * 
     * @return 线索
     */
    public List<CrmClue> selectCrmClueListNoFollow();
}
