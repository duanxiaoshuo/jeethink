package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.CrmTransfer;

/**
 * 转交记录Mapper接口
 * 
 * @author jeethink
 * @date 2020-04-19
 */
public interface CrmTransferMapper 
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
     * 删除转交记录
     * 
     * @param trasferId 转交记录ID
     * @return 结果
     */
    public int deleteCrmTransferById(Long trasferId);

    /**
     * 批量删除转交记录
     * 
     * @param trasferIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrmTransferByIds(String[] trasferIds);
}
