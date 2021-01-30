package com.jeethink.crm.service.crm2;

import com.jeethink.crm.domain.CrmTransfer;

import java.util.List;

/**
 * 转交记录Service接口
 * 
 * @author jeethink
 * @date 2020-04-19
 */
public interface ICrm2TransferService
{
    /**
     * 查询转交记录
     * 
     * @param trasferId 转交记录ID
     * @return 转交记录
     */
    public CrmTransfer selectCrmTransferById(Long trasferId);

    /**
     * 查询转交记录列表
     * 
     * @param crmTransfer 转交记录
     * @return 转交记录集合
     */
    public List<CrmTransfer> selectCrmTransferList(CrmTransfer crmTransfer);

    /**
     * 新增转交记录
     * 
     * @param crmTransfer 转交记录
     * @return 结果
     */
    public int insertCrmTransfer(CrmTransfer crmTransfer);

    /**
     * 修改转交记录
     * 
     * @param crmTransfer 转交记录
     * @return 结果
     */
    public int updateCrmTransfer(CrmTransfer crmTransfer);

    /**
     * 批量删除转交记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmTransferByIds(String ids);

    /**
     * 删除转交记录信息
     * 
     * @param trasferId 转交记录ID
     * @return 结果
     */
    public int deleteCrmTransferById(Long trasferId);
    
    /**
     * 批量转交给个人
     * 批量转交公共
     * 
     * @param businessType 业务类型
     * @param ids 需要转交的数据ID
     * @param belongTo 负责人
     * @param reason   转交原因
     * @param operName 操作人
     * @return 结果
     */
    public int transferByIds(String businessType,String ids,String belongTo,String reason, String operName);
    
    /**
     * 批量领取
     * 
     *  @param businessType 业务类型
     * @param ids 需要领取的数据ID
     * @param reason   领取原因
     * @param operName 操作人
     * @return 结果
     */
    public int getByIds(String businessType,String ids,String reason, String operName);
}
