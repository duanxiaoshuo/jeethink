package com.jeethink.crm.service.wms.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeethink.crm.mapper.WmsStorageMapper;
import com.jeethink.crm.domain.WmsStorage;
import com.jeethink.crm.service.wms.IWmsStorageService;
import com.jeethink.common.core.text.Convert;

/**
 * 仓库Service业务层处理
 * 
 * @author jeethink
 * @date 2020-07-29
 */
@Service
public class WmsStorageServiceImpl implements IWmsStorageService 
{
    @Autowired
    private WmsStorageMapper wmsStorageMapper;

    /**
     * 查询仓库
     * 
     * @param storageId 仓库ID
     * @return 仓库
     */
    @Override
    public WmsStorage selectWmsStorageById(Long storageId)
    {
        return wmsStorageMapper.selectWmsStorageById(storageId);
    }

    /**
     * 查询仓库列表
     * 
     * @param wmsStorage 仓库
     * @return 仓库
     */
    @Override
    public List<WmsStorage> selectWmsStorageList(WmsStorage wmsStorage)
    {
        return wmsStorageMapper.selectWmsStorageList(wmsStorage);
    }

    /**
     * 新增仓库
     * 
     * @param wmsStorage 仓库
     * @return 结果
     */
    @Override
    public int insertWmsStorage(WmsStorage wmsStorage)
    {
        wmsStorage.setCreateTime(DateUtils.getNowDate());
        return wmsStorageMapper.insertWmsStorage(wmsStorage);
    }

    /**
     * 修改仓库
     * 
     * @param wmsStorage 仓库
     * @return 结果
     */
    @Override
    public int updateWmsStorage(WmsStorage wmsStorage)
    {
        wmsStorage.setUpdateTime(DateUtils.getNowDate());
        return wmsStorageMapper.updateWmsStorage(wmsStorage);
    }

    /**
     * 删除仓库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWmsStorageByIds(String ids)
    {
        return wmsStorageMapper.deleteWmsStorageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除仓库信息
     * 
     * @param storageId 仓库ID
     * @return 结果
     */
    @Override
    public int deleteWmsStorageById(Long storageId)
    {
        return wmsStorageMapper.deleteWmsStorageById(storageId);
    }
}
