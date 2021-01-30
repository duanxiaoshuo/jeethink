package com.jeethink.crm.service.wms.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeethink.crm.mapper.WmsSupplierMapper;
import com.jeethink.crm.domain.WmsProduct;
import com.jeethink.crm.domain.WmsSupplier;
import com.jeethink.crm.service.wms.IWmsProductService;
import com.jeethink.crm.service.wms.IWmsSupplierService;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;

/**
 * 产品供应商Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-12
 */
@Service
public class WmsSupplierServiceImpl implements IWmsSupplierService 
{
    @Autowired
    private WmsSupplierMapper wmsSupplierMapper;
    
    @Autowired
    private IWmsProductService wmsProductService;

    /**
     * 查询产品供应商
     * 
     * @param supplierIid 产品供应商ID
     * @return 产品供应商
     */
    @Override
    public WmsSupplier selectWmsSupplierById(Long supplierIid)
    {
        return wmsSupplierMapper.selectWmsSupplierById(supplierIid);
    }

    /**
     * 查询产品供应商列表
     * 
     * @param wmsSupplier 产品供应商
     * @return 产品供应商
     */
    @Override
    public List<WmsSupplier> selectWmsSupplierList(WmsSupplier wmsSupplier)
    {
        return wmsSupplierMapper.selectWmsSupplierList(wmsSupplier);
    }

    /**
     * 新增产品供应商
     * 
     * @param wmsSupplier 产品供应商
     * @return 结果
     */
    @Override
    public int insertWmsSupplier(WmsSupplier wmsSupplier)
    {
    	wmsSupplier.setDelFlag("0");
        wmsSupplier.setCreateTime(DateUtils.getNowDate());
        return wmsSupplierMapper.insertWmsSupplier(wmsSupplier);
    }

    /**
     * 修改产品供应商
     * 
     * @param wmsSupplier 产品供应商
     * @return 结果
     */
    @Override
    public int updateWmsSupplier(WmsSupplier wmsSupplier)
    {
        wmsSupplier.setUpdateTime(DateUtils.getNowDate());
        return wmsSupplierMapper.updateWmsSupplier(wmsSupplier);
    }

    /**
     * 删除产品供应商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWmsSupplierByIds(String ids)
    {    	
    	Long[] supplierIds = Convert.toLongArray(ids);
    	for(int j=0;j<supplierIds.length;j++){    
    		Long supplierId=supplierIds[j];
    		WmsProduct wmsProduct=new WmsProduct();
    		wmsProduct.setSupplierId(supplierId);
    		wmsProduct.setDelFlag("0");
        	List<WmsProduct> list = wmsProductService.selectWmsProductList(wmsProduct);
        	if(list.size()>0)
        	{
        		throw new BusinessException(String.format("供应商%1$s存在关联的产品,不能删除!", supplierId));
        	}
    	}
        return wmsSupplierMapper.deleteWmsSupplierByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品供应商信息
     * 
     * @param supplierIid 产品供应商ID
     * @return 结果
     */
    @Override
    public int deleteWmsSupplierById(Long supplierIid)
    {
        return wmsSupplierMapper.deleteWmsSupplierById(supplierIid);
    }
}
