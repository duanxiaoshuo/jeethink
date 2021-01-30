package com.jeethink.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeethink.crm.domain.WmsProductCatogory;

/**
 * 产品分类管理Mapper接口
 * 
 * @author jeethink
 * @date 2020-04-30
 */
public interface WmsProductCatogoryMapper 
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
     * 删除产品分类管理
     * 
     * @param productCatogoryId 产品分类管理ID
     * @return 结果
     */
    public int deleteWmsProductCatogoryById(Long productCatogoryId);

    /**
     * 批量删除产品分类管理
     * 
     * @param productCatogoryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsProductCatogoryByIds(String[] productCatogoryIds);
    
    /**
     * 根据ID查询所有子产品分类
     * @param productCatogoryId 产品分类管理ID
     * @return 产品分类列表
     */
    public List<WmsProductCatogory> selectChildrenProductCatogoryById(Long productCatogoryId);
    
    /**
     * 修改子元素关系
     * 
     * @param productCatogorys 子元素
     * @return 结果
     */
    public int updateProductCatogoryChildren(@Param("productCatogorys") List<WmsProductCatogory> productCatogorys);
}
