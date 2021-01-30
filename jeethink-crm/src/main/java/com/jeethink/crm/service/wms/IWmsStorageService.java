package com.jeethink.crm.service.wms;

import java.util.List;
import com.jeethink.crm.domain.WmsStorage;

/**
 * 仓库Service接口
 * 
 * @author jeethink
 * @date 2020-07-29
 */
public interface IWmsStorageService 
{
    /**
     * 查询仓库
     * 
     * @param storageId 仓库ID
     * @return 仓库
     */
    public WmsStorage selectWmsStorageById(Long storageId);

    /**
     * 查询仓库列表
     * 
     * @param wmsStorage 仓库
     * @return 仓库集合
     */
    public List<WmsStorage> selectWmsStorageList(WmsStorage wmsStorage);

    /**
     * 新增仓库
     * 
     * @param wmsStorage 仓库
     * @return 结果
     */
    public int insertWmsStorage(WmsStorage wmsStorage);

    /**
     * 修改仓库
     * 
     * @param wmsStorage 仓库
     * @return 结果
     */
    public int updateWmsStorage(WmsStorage wmsStorage);

    /**
     * 批量删除仓库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsStorageByIds(String ids);

    /**
     * 删除仓库信息
     * 
     * @param storageId 仓库ID
     * @return 结果
     */
    public int deleteWmsStorageById(Long storageId);
}
