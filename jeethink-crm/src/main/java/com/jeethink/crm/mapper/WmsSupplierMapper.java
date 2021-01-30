package com.jeethink.crm.mapper;

import java.util.List;
import com.jeethink.crm.domain.WmsSupplier;

/**
 * 产品供应商Mapper接口
 * 
 * @author jeethink
 * @date 2020-03-12
 */
public interface WmsSupplierMapper 
{
    /**
     * 查询产品供应商
     * 
     * @param supplierIid 产品供应商ID
     * @return 产品供应商
     */
    public WmsSupplier selectWmsSupplierById(Long supplierIid);

    /**
     * 查询产品供应商列表
     * 
     * @param wmsSupplier 产品供应商
     * @return 产品供应商集合
     */
    public List<WmsSupplier> selectWmsSupplierList(WmsSupplier wmsSupplier);

    /**
     * 新增产品供应商
     * 
     * @param wmsSupplier 产品供应商
     * @return 结果
     */
    public int insertWmsSupplier(WmsSupplier wmsSupplier);

    /**
     * 修改产品供应商
     * 
     * @param wmsSupplier 产品供应商
     * @return 结果
     */
    public int updateWmsSupplier(WmsSupplier wmsSupplier);

    /**
     * 删除产品供应商
     * 
     * @param supplierIid 产品供应商ID
     * @return 结果
     */
    public int deleteWmsSupplierById(Long supplierIid);

    /**
     * 批量删除产品供应商
     * 
     * @param supplierIids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWmsSupplierByIds(String[] supplierIids);
}
