package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.WmsStockCheck;

/**
 * 库存盘点Mapper接口
 * 
 * @author jeethink
 * @date 2020-08-14
 */
public interface WmsStockCheckMapper 
{
    /**
     * 查询库存盘点
     * 
     * @param checkId 库存盘点ID
     * @return 库存盘点
     */
    public WmsStockCheck selectWmsStockCheckById(Long checkId);

    /**
     * 查询库存盘点列表
     * 
     * @param wmsStockCheck 库存盘点
     * @return 库存盘点集合
     */
    public List<WmsStockCheck> selectWmsStockCheckList(WmsStockCheck wmsStockCheck);

    /**
     * 新增库存盘点
     * 
     * @param wmsStockCheck 库存盘点
     * @return 结果
     */
    public int insertWmsStockCheck(WmsStockCheck wmsStockCheck);

    /**
     * 修改库存盘点
     * 
     * @param wmsStockCheck 库存盘点
     * @return 结果
     */
    public int updateWmsStockCheck(WmsStockCheck wmsStockCheck);

    /**
     * 删除库存盘点
     * 
     * @param checkId 库存盘点ID
     * @return 结果
     */
    public int deleteWmsStockCheckById(Long checkId);

    /**
     * 批量删除库存盘点
     * 
     * @param checkIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsStockCheckByIds(String[] checkIds);
}
