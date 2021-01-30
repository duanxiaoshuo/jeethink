package com.jeethink.crm.service.wms.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeethink.crm.mapper.WmsStockOutMapper;
import com.jeethink.crm.domain.WmsStock;
import com.jeethink.crm.domain.WmsStockHistory;
import com.jeethink.crm.domain.WmsStockOut;
import com.jeethink.crm.domain.WmsStockProduct;
import com.jeethink.crm.service.wms.IWmsStockHistoryService;
import com.jeethink.crm.service.wms.IWmsStockOutService;
import com.jeethink.crm.service.wms.IWmsStockProductService;
import com.jeethink.crm.service.wms.IWmsStockService;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;

/**
 * 出库管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-16
 */
@Service
public class WmsStockOutServiceImpl implements IWmsStockOutService 
{
    @Autowired
    private WmsStockOutMapper wmsStockOutMapper;
    
    @Autowired
	private IWmsStockService wmsStockService;
	
	@Autowired
	private IWmsStockHistoryService wmsStockHistoryService;

	@Autowired
	private IWmsStockProductService wmsStockProductService;

    /**
     * 查询出库管理
     * 
     * @param outId 出库管理ID
     * @return 出库管理
     */
    @Override
    public WmsStockOut selectWmsStockOutById(Long outId)
    {
        return wmsStockOutMapper.selectWmsStockOutById(outId);
    }

    /**
     * 查询出库管理列表
     * 
     * @param wmsStockOut 出库管理
     * @return 出库管理
     */
    @Override
    public List<WmsStockOut> selectWmsStockOutList(WmsStockOut wmsStockOut)
    {
        return wmsStockOutMapper.selectWmsStockOutList(wmsStockOut);
    }

    /**
     * 新增出库管理
     * 
     * @param wmsStockOut 出库管理
     * @return 结果
     */
    @Override
	@Transactional
    public int insertWmsStockOut(WmsStockOut wmsStockOut)
    {
    	wmsStockOut.setDelFlag("0");
        wmsStockOut.setCreateTime(DateUtils.getNowDate());
        wmsStockOutMapper.insertWmsStockOut(wmsStockOut);
		if(wmsStockOut.getProducts()!=null&&wmsStockOut.getProducts().size()>0) {
			insertProducts(wmsStockOut);
		}
		return 1;
    }

    /**
     * 修改出库管理
     * 
     * @param wmsStockOut 出库管理
     * @return 结果
     */
    @Override
	@Transactional
    public int updateWmsStockOut(WmsStockOut wmsStockOut)
    {
        wmsStockOut.setUpdateTime(DateUtils.getNowDate());
        wmsStockOutMapper.updateWmsStockOut(wmsStockOut);
		if(wmsStockOut.getProducts()!=null&&wmsStockOut.getProducts().size()>0) {
			insertProducts(wmsStockOut);
		}
		return 1;
    }

    /**
     * 删除出库管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockOutByIds(String ids)
    {
        return wmsStockOutMapper.deleteWmsStockOutByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除出库管理信息
     * 
     * @param outId 出库管理ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockOutById(Long outId)
    {
		// 先删除之前的对应关系
		wmsStockProductService.deleteWmsStockProductByOpId(outId, "1");
        return wmsStockOutMapper.deleteWmsStockOutById(outId);
    }
    
    /**
     * 审核出库  拒绝
     * 
     * @param wmsStockOut 出库管理
     * @return 结果
     */
    @Override
    public int auditNoWmsStockOut(WmsStockOut wmsStockOut) {
		wmsStockOut.setOutStatus("3");//已驳回
        wmsStockOut.setUpdateTime(DateUtils.getNowDate());
        return wmsStockOutMapper.updateWmsStockOut(wmsStockOut);
    }
    
    /**
     * 审核出库 通过
     * 
     * @param wmsStockOut 出库管理
     * @return 结果
     */
    @Override
    @Transactional
    public int auditOkWmsStockOut(WmsStockOut wmsStockOut)
    {
    	long outId=wmsStockOut.getOutId();
    	long storageId=wmsStockOut.getStorageId();
		wmsStockOut.setOutStatus("2");//已审核	
		wmsStockOut.setUpdateTime(DateUtils.getNowDate());
		wmsStockOutMapper.updateWmsStockOut(wmsStockOut);
		
		
		WmsStockProduct wmsStockProduct=new WmsStockProduct();
		wmsStockProduct.setOpId(outId);
		wmsStockProduct.setType("1");//出库
		List<WmsStockProduct> products= wmsStockProductService.selectWmsStockProductList(wmsStockProduct);
		for (WmsStockProduct product : products) {			
			//更改库存
			long productId=product.getProductId();
			String productName=product.getProductName();
	    	WmsStock wmsStock = wmsStockService.selectWmsStock(storageId,productId);
			Long oldCount = 0L;
			Long newCount = 0L;
			if (wmsStock != null) {
				Long stockNum = wmsStock.getStockNum();
				oldCount = stockNum;
				if(oldCount<product.getProductNum1())
				{
					throw new BusinessException(String.format("产品：%s 库存数量小于出库数量,不能出库",productName));
				}
				newCount = oldCount - product.getProductNum1();
				wmsStock.setStockNum(newCount);
				wmsStock.setUpdateBy(wmsStockOut.getUpdateBy());
				wmsStockService.updateWmsStock(wmsStock);
			} else {
				throw new BusinessException(String.format("产品：%s 库存没有该产品,不能出库",productName));
			}
	
			//库存记录
			WmsStockHistory wmsStockHistory=new WmsStockHistory();
			wmsStockHistory.setStorageId(storageId);
			wmsStockHistory.setProductId(productId);
			wmsStockHistory.setHistoryType("1");//直接出库
			wmsStockHistory.setNumBefore(oldCount);
			wmsStockHistory.setNumAfter(newCount);
			wmsStockHistory.setHistoryDate(DateUtils.getNowDate());
			wmsStockHistory.setHistoryBy(wmsStockOut.getOutBy());
			wmsStockHistory.setDelFlag("0");
			wmsStockHistory.setCreateBy(wmsStockOut.getUpdateBy());
			wmsStockHistoryService.insertWmsStockHistory(wmsStockHistory);			
		}    	
    	return 1;
    }
    
	//批量插入入库对应的产品
	private void insertProducts(WmsStockOut wmsStockOut) {
		// 先删除之前的对应关系
		wmsStockProductService.deleteWmsStockProductByOpId(wmsStockOut.getOutId(), "1");
		for (WmsStockProduct product : wmsStockOut.getProducts()) {
			product.setOpId(wmsStockOut.getOutId());
			product.setType("1");// 出库
			product.setDelFlag("0");
			product.setCreateBy(wmsStockOut.getCreateBy());
			wmsStockProductService.insertWmsStockProduct(product);
		}
	}
}
