package com.jeethink.crm.service.wms.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeethink.crm.mapper.WmsStockEnterMapper;
import com.jeethink.crm.domain.WmsStock;
import com.jeethink.crm.domain.WmsStockEnter;
import com.jeethink.crm.domain.WmsStockHistory;
import com.jeethink.crm.domain.WmsStockProduct;
import com.jeethink.crm.service.wms.IWmsStockEnterService;
import com.jeethink.crm.service.wms.IWmsStockHistoryService;
import com.jeethink.crm.service.wms.IWmsStockProductService;
import com.jeethink.crm.service.wms.IWmsStockService;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;

/**
 * 入库管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-16
 */
@Service
public class WmsStockEnterServiceImpl implements IWmsStockEnterService {
	@Autowired
	private WmsStockEnterMapper wmsStockEnterMapper;

	@Autowired
	private IWmsStockService wmsStockService;

	@Autowired
	private IWmsStockHistoryService wmsStockHistoryService;

	@Autowired
	private IWmsStockProductService wmsStockProductService;

	/**
	 * 查询入库管理
	 * 
	 * @param enterId 入库管理ID
	 * @return 入库管理
	 */
	@Override
	public WmsStockEnter selectWmsStockEnterById(Long enterId) {
		return wmsStockEnterMapper.selectWmsStockEnterById(enterId);
	}

	/**
	 * 查询入库管理列表
	 * 
	 * @param wmsStockEnter 入库管理
	 * @return 入库管理
	 */
	@Override
	public List<WmsStockEnter> selectWmsStockEnterList(WmsStockEnter wmsStockEnter) {
		return wmsStockEnterMapper.selectWmsStockEnterList(wmsStockEnter);
	}

	/**
	 * 新增入库管理
	 * 
	 * @param wmsStockEnter 入库管理
	 * @return 结果
	 */
	@Override
	@Transactional
	public int insertWmsStockEnter(WmsStockEnter wmsStockEnter) {
		wmsStockEnter.setDelFlag("0");
		wmsStockEnter.setCreateTime(DateUtils.getNowDate());
		wmsStockEnterMapper.insertWmsStockEnter(wmsStockEnter);
		if(wmsStockEnter.getProducts()!=null&&wmsStockEnter.getProducts().size()>0) {
			insertProducts(wmsStockEnter);
		}
		return 1;
	}

	/**
	 * 修改入库管理
	 * 
	 * @param wmsStockEnter 入库管理
	 * @return 结果
	 */
	@Override
	@Transactional
	public int updateWmsStockEnter(WmsStockEnter wmsStockEnter) {
		wmsStockEnter.setUpdateTime(DateUtils.getNowDate());
		wmsStockEnterMapper.updateWmsStockEnter(wmsStockEnter);
		if(wmsStockEnter.getProducts()!=null&&wmsStockEnter.getProducts().size()>0) {
			insertProducts(wmsStockEnter);
		}
		return 1;
	}

	/**
	 * 删除入库管理对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	@Transactional
	public int deleteWmsStockEnterByIds(String ids) {

		String[] enterIds = Convert.toStrArray(ids);
		for (int j = 0; j < enterIds.length; j++) {
			Long enterId = Long.parseLong(enterIds[j]);
			WmsStockEnter wmsStockEnter = wmsStockEnterMapper.selectWmsStockEnterById(enterId);
			if (wmsStockEnter.getEnterStatus().equals("2")) {
				throw new BusinessException(String.format("入库%1$s已经入库审核通过,不能删除", enterIds[j]));
			}
		}
		return wmsStockEnterMapper.deleteWmsStockEnterByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除入库管理信息
	 * 
	 * @param enterId 入库管理ID
	 * @return 结果
	 */
	@Override
	@Transactional
	public int deleteWmsStockEnterById(Long enterId) {
		// 先删除之前的对应关系
		wmsStockProductService.deleteWmsStockProductByOpId(enterId, "0");
		return wmsStockEnterMapper.deleteWmsStockEnterById(enterId);
	}
    
    /**
     * 审核入库 拒绝
     * 
     * @param wmsStockEnter 入库管理
     * @return 结果
     */
	@Override
    public int auditNoWmsStockEnter(WmsStockEnter wmsStockEnter) {
    	wmsStockEnter.setEnterStatus("3");//已驳回
		wmsStockEnter.setUpdateTime(DateUtils.getNowDate());
		return wmsStockEnterMapper.updateWmsStockEnter(wmsStockEnter);
	}

	/**
	 * 审核入库 通过
	 * 
	 * @param wmsStockEnter 入库管理
	 * @return 结果
	 */
	@Override
	@Transactional
	public int auditOkWmsStockEnter(WmsStockEnter wmsStockEnter) {
		long enterId=wmsStockEnter.getEnterId();
		long storageId = wmsStockEnter.getStorageId();
    	wmsStockEnter.setEnterStatus("2");//已审核
		wmsStockEnter.setUpdateTime(DateUtils.getNowDate());
		wmsStockEnterMapper.updateWmsStockEnter(wmsStockEnter);		
		
		WmsStockProduct wmsStockProduct=new WmsStockProduct();
		wmsStockProduct.setOpId(enterId);
		wmsStockProduct.setType("0");//入库
		List<WmsStockProduct> products= wmsStockProductService.selectWmsStockProductList(wmsStockProduct);
		for (WmsStockProduct product : products) {
			//更改库存
			long productId=product.getProductId();
			WmsStock wmsStock =  wmsStockService.selectWmsStock(storageId,productId);
			Long oldCount = 0L;
			Long newCount = 0L;
			if (wmsStock != null) {
				Long stockNum = wmsStock.getStockNum();
				oldCount = stockNum;
				newCount = oldCount + product.getProductNum1();
				wmsStock.setStockNum(newCount);
				wmsStock.setUpdateBy(wmsStockEnter.getUpdateBy());
				wmsStockService.updateWmsStock(wmsStock);
			} else {
				wmsStock = new WmsStock();
				wmsStock.setStockNum(0L);
				oldCount = 0L;
				newCount = product.getProductNum1();
				wmsStock.setStorageId(storageId);
				wmsStock.setProductId(productId);
				wmsStock.setStockNum(newCount);
				wmsStock.setCreateTime(DateUtils.getNowDate());
				wmsStock.setCreateBy(wmsStockEnter.getUpdateBy());			
				wmsStockService.insertWmsStock(wmsStock);
			}			

			// 库存记录
			WmsStockHistory wmsStockHistory=new WmsStockHistory();
			wmsStockHistory.setStorageId(storageId);
			wmsStockHistory.setProductId(productId);
			wmsStockHistory.setHistoryType("0");//直接入库
			wmsStockHistory.setNumBefore(oldCount);
			wmsStockHistory.setNumAfter(newCount);
			wmsStockHistory.setHistoryDate(DateUtils.getNowDate());
			wmsStockHistory.setHistoryBy(wmsStockEnter.getEnterBy());
			wmsStockHistory.setDelFlag("0");
			wmsStockHistory.setCreateBy(wmsStockEnter.getUpdateBy());
			wmsStockHistoryService.insertWmsStockHistory(wmsStockHistory);		
		}
		return 1;
	}

	//批量插入入库对应的产品
	private void insertProducts(WmsStockEnter wmsStockEnter) {
		// 先删除之前的对应关系
		wmsStockProductService.deleteWmsStockProductByOpId(wmsStockEnter.getEnterId(), "0");
		for (WmsStockProduct product : wmsStockEnter.getProducts()) {
			product.setOpId(wmsStockEnter.getEnterId());
			product.setType("0");// 入库
			product.setDelFlag("0");
			product.setCreateBy(wmsStockEnter.getCreateBy());
			wmsStockProductService.insertWmsStockProduct(product);
		}
	}
}
