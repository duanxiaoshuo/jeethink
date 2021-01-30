package com.jeethink.crm.service.wms;

import java.util.List;
import com.jeethink.crm.domain.WmsProductCatogory;
import com.jeethink.common.core.domain.Ztree;

/**
 * 产品分类管理Service接口
 * 
 * @author jeethink
 * @date 2020-04-30
 */
public interface IWmsProductCatogoryService 
{
    /**
     * 查询产品分类管理
     * 
     * @param productCatogoryId 产品分类管理ID
     * @return 产品分类管理
     */
    public WmsProductCatogory selectWmsProductCatogoryById(Long productCatogoryId);

    /**
     * 查询产品分类管理列表
     * 
     * @param wmsProductCatogory 产品分类管理
     * @return 产品分类管理集合
     */
    public List<WmsProductCatogory> selectWmsProductCatogoryList(WmsProductCatogory wmsProductCatogory);

    /**
     * 新增产品分类管理
     * 
     * @param wmsProductCatogory 产品分类管理
     * @return 结果
     */
    public int insertWmsProductCatogory(WmsProductCatogory wmsProductCatogory);

    /**
     * 修改产品分类管理
     * 
     * @param wmsProductCatogory 产品分类管理
     * @return 结果
     */
    public int updateWmsProductCatogory(WmsProductCatogory wmsProductCatogory);

    /**
     * 批量删除产品分类管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsProductCatogoryByIds(String ids);

    /**
     * 删除产品分类管理信息
     * 
     * @param productCatogoryId 产品分类管理ID
     * @return 结果
     */
    public int deleteWmsProductCatogoryById(Long productCatogoryId);

    /**
     * 查询产品分类管理树列表
     * 
     * @return 所有产品分类管理信息
     */
    public List<Ztree> selectWmsProductCatogoryTree();
}
