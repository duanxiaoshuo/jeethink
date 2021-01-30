package com.jeethink.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeethink.crm.domain.WmsStockProduct;

/**
 * 库存操作和产品对应Mapper接口
 * 
 * @author jeethink
 * @date 2020-08-21
 */
public interface WmsStockProductMapper 
{
    /**
     * 查询库存操作和产品对应
     * 
     * @param stockProductId 库存操作和产品对应ID
     * @return 库存操作和产品对应
     */
    public WmsStockProduct selectWmsStockProductById(Long stockProductId);

    /**
     * 查询库存操作和产品对应列表
     * 
     * @param wmsStockProduct 库存操作和产品对应
     * @return 库存操作和产品对应集合
     */
    public List<WmsStockProduct> selectWmsStockProductList(WmsStockProduct wmsStockProduct);

    /**
     * 新增库存操作和产品对应
     * 
     * @param wmsStockProduct 库存操作和产品对应
     * @return 结果
     */
    public int insertWmsStockProduct(WmsStockProduct wmsStockProduct);

    /**
     * 修改库存操作和产品对应
     * 
     * @param wmsStockProduct 库存操作和产品对应
     * @return 结果
     */
    public int updateWmsStockProduct(WmsStockProduct wmsStockProduct);

    /**
     * 删除库存操作和产品对应
     * 
     * @param stockProductId 库存操作和产品对应ID
     * @return 结果
     */
    public int deleteWmsStockProductById(Long stockProductId);

    /**
     * 批量删除库存操作和产品对应
     * 
     * @param stockProductIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsStockProductByIds(String[] stockProductIds);
    
    /**
     * 根据操作ID和类型 批量删除
     * 
     * @param opId 操作id
     * @param type 类型
     * @return 结果
     */
    public int deleteWmsStockProductByOpId(@Param("opId") Long opId,@Param("type") String type);
}
