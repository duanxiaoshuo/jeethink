package com.jeethink.crm.service.wms.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.common.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeethink.crm.mapper.WmsStockCheckMapper;
import com.jeethink.crm.domain.WmsStock;
import com.jeethink.crm.domain.WmsStockCheck;
import com.jeethink.crm.domain.WmsStockEnter;
import com.jeethink.crm.domain.WmsStockHistory;
import com.jeethink.crm.domain.WmsStockOut;
import com.jeethink.crm.domain.WmsStockProduct;
import com.jeethink.crm.service.wms.IWmsStockCheckService;
import com.jeethink.crm.service.wms.IWmsStockEnterService;
import com.jeethink.crm.service.wms.IWmsStockHistoryService;
import com.jeethink.crm.service.wms.IWmsStockOutService;
import com.jeethink.crm.service.wms.IWmsStockProductService;
import com.jeethink.crm.service.wms.IWmsStockService;
import com.jeethink.common.core.text.Convert;

/**
 * 库存盘点Service业务层处理
 * 
 * @author jeethink
 * @date 2020-08-14
 */
@Service
public class WmsStockCheckServiceImpl implements IWmsStockCheckService 
{
    @Autowired
    private WmsStockCheckMapper wmsStockCheckMapper;

	@Autowired
	private IWmsStockService wmsStockService;	

	@Autowired
	private IWmsStockEnterService wmsStockEnterService;

	@Autowired
	private IWmsStockOutService wmsStockOutService;
	
	@Autowired
	private IWmsStockHistoryService wmsStockHistoryService;

	@Autowired
	private IWmsStockProductService wmsStockProductService;
	
    /**
     * 查询库存盘点
     * 
     * @param checkId 库存盘点ID
     * @return 库存盘点
     */
    @Override
    public WmsStockCheck selectWmsStockCheckById(Long checkId)
    {
        return wmsStockCheckMapper.selectWmsStockCheckById(checkId);
    }

    /**
     * 查询库存盘点列表
     * 
     * @param wmsStockCheck 库存盘点
     * @return 库存盘点
     */
    @Override
    public List<WmsStockCheck> selectWmsStockCheckList(WmsStockCheck wmsStockCheck)
    {
        return wmsStockCheckMapper.selectWmsStockCheckList(wmsStockCheck);
    }

    /**
     * 新增库存盘点
     * 
     * @param wmsStockCheck 库存盘点
     * @return 结果
     */
    @Override
	@Transactional
    public int insertWmsStockCheck(WmsStockCheck wmsStockCheck)
    {
        wmsStockCheck.setCreateTime(DateUtils.getNowDate());
        wmsStockCheckMapper.insertWmsStockCheck(wmsStockCheck);
		if(wmsStockCheck.getProducts()!=null&&wmsStockCheck.getProducts().size()>0) {
			insertProducts(wmsStockCheck);
		}
		return 1;
    }

    /**
     * 修改库存盘点
     * 
     * @param wmsStockCheck 库存盘点
     * @return 结果
     */
    @Override
	@Transactional
    public int updateWmsStockCheck(WmsStockCheck wmsStockCheck)
    {
        wmsStockCheck.setUpdateTime(DateUtils.getNowDate());
        wmsStockCheckMapper.updateWmsStockCheck(wmsStockCheck);
		if(wmsStockCheck.getProducts()!=null&&wmsStockCheck.getProducts().size()>0) {
			insertProducts(wmsStockCheck);
		}
		return 1;
    }

    /**
     * 删除库存盘点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockCheckByIds(String ids)
    {
        return wmsStockCheckMapper.deleteWmsStockCheckByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存盘点信息
     * 
     * @param checkId 库存盘点ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockCheckById(Long checkId)
    {
        return wmsStockCheckMapper.deleteWmsStockCheckById(checkId);
    }    
    
    /**
     * 审核入库 拒绝
     * 
     * @param wmsStockEnter 入库管理
     * @return 结果
     */
	@Override
    public int auditNoWmsStockCheck(WmsStockCheck wmsStockCheck) {
		wmsStockCheck.setCheckStatus("3");//已驳回
		wmsStockCheck.setUpdateTime(DateUtils.getNowDate());
		return wmsStockCheckMapper.updateWmsStockCheck(wmsStockCheck);
	}

    /**
     * 审核库存盘点 通过
     * 
     * @param wmsStockCheck 库存盘点
     * @return 结果
     */
    @Override
	@Transactional
    public int auditOkWmsStockCheck(WmsStockCheck wmsStockCheck) 
    {    	
    	long storageId=wmsStockCheck.getStorageId();
    	long checkId=wmsStockCheck.getCheckId();
    	wmsStockCheck.setCheckStatus("2");//已审核
        wmsStockCheck.setUpdateTime(DateUtils.getNowDate());
		wmsStockCheckMapper.updateWmsStockCheck(wmsStockCheck);
		
		WmsStockProduct wmsStockProduct=new WmsStockProduct();
		wmsStockProduct.setOpId(checkId);
		wmsStockProduct.setType("3");//盘点
		List<WmsStockProduct> products= wmsStockProductService.selectWmsStockProductList(wmsStockProduct);		
    	WmsStockOut wmsStockOut=new WmsStockOut();//出库单
    	WmsStockEnter wmsStockEnter=new WmsStockEnter();//入库单
		for (WmsStockProduct product : products) {

	    	long numStock=product.getProductNum1();//当前数量
	    	long numStockActual=product.getProductNum2();//实际数量    	
	    	long productId=product.getProductId();	   
	    	String productName=product.getProductName();

	    	//增加库存记录
			WmsStockHistory wmsStockHistory=new WmsStockHistory();
			wmsStockHistory.setStorageId(storageId);
			wmsStockHistory.setProductId(productId);
			wmsStockHistory.setNumBefore(numStock);
			wmsStockHistory.setNumAfter(numStockActual);
			wmsStockHistory.setHistoryDate(DateUtils.getNowDate());
			wmsStockHistory.setHistoryBy(wmsStockCheck.getCheckBy());
			wmsStockHistory.setDelFlag("0");
			wmsStockHistory.setCreateBy(wmsStockCheck.getUpdateBy());
			
			//当前数量大于实际数量，需要出库
	    	if(numStock>numStockActual){    			    		
	    		//增加出库单，只增加一次
	    		if(StringUtils.isEmpty(wmsStockOut.getOutId()+"")||StringUtils.isNull(wmsStockOut.getOutId())) {
		    		wmsStockOut.setTitle("盘点出库");
		        	wmsStockOut.setOutStatus("2");//已审核
		        	wmsStockOut.setStorageId(storageId);
		        	wmsStockOut.setOutBy(wmsStockCheck.getCheckBy());
		        	wmsStockOut.setOutType("3");//盘点出库
		        	wmsStockOut.setOutDate(DateUtils.getNowDate());
		        	wmsStockOut.setCreateBy(wmsStockCheck.getUpdateBy());
		        	wmsStockOutService.insertWmsStockOut(wmsStockOut);   
	    		} 		
	        	
	        	//增加出库，库存记录
	        	wmsStockHistory.setHistoryType("5");//设置历史记录为：盘点出库
	        	
	        	//增加对应产品
	        	WmsStockProduct wmsStockProduct_out=new WmsStockProduct();
	        	wmsStockProduct_out.setOpId(wmsStockOut.getOutId());
	        	wmsStockProduct_out.setProductId(productId);
	        	wmsStockProduct_out.setProductName(productName);
	        	wmsStockProduct_out.setProductNum1(numStock-numStockActual);
	        	wmsStockProduct_out.setType("1");// 出库
	        	wmsStockProduct_out.setDelFlag("0");
	        	wmsStockProduct_out.setCreateBy(wmsStockCheck.getCreateBy());
				wmsStockProductService.insertWmsStockProduct(wmsStockProduct_out);
	        	
	    	}
	    	//当前数量小于实际数量，需要入库
	    	else if(numStockActual>numStock) {
	    		//增加入库单，只增加一次
	    		if(StringUtils.isEmpty(wmsStockEnter.getEnterId()+"")||StringUtils.isNull(wmsStockEnter.getEnterId())) {
		        	wmsStockEnter.setTitle("盘点入库");
		        	wmsStockEnter.setEnterStatus("2");//已审核
		        	wmsStockEnter.setStorageId(storageId);
		        	wmsStockEnter.setEnterBy(wmsStockCheck.getCheckBy());
		        	wmsStockEnter.setEnterType("4");//盘点入库
		        	wmsStockEnter.setEnterDate(DateUtils.getNowDate());
		        	wmsStockEnter.setCreateBy(wmsStockCheck.getUpdateBy());
		        	wmsStockEnterService.insertWmsStockEnter(wmsStockEnter);  		    			
	    		}	    
	        	
	    		//增加入库，库存记录
	    		wmsStockHistory.setHistoryType("4");//设置历史记录为：盘点入库
	    		
	        	//增加对应产品
	        	WmsStockProduct wmsStockProduct_enter=new WmsStockProduct();
	        	wmsStockProduct_enter.setOpId(wmsStockEnter.getEnterId());
	        	wmsStockProduct_enter.setProductId(productId);
	        	wmsStockProduct_enter.setProductName(productName);
	        	wmsStockProduct_enter.setProductNum1(numStockActual-numStock);
	        	wmsStockProduct_enter.setType("0");// 入库
	        	wmsStockProduct_enter.setDelFlag("0");
	        	wmsStockProduct_enter.setCreateBy(wmsStockCheck.getCreateBy());
				wmsStockProductService.insertWmsStockProduct(wmsStockProduct_enter);
	    	}   	
			wmsStockHistoryService.insertWmsStockHistory(wmsStockHistory);
		
			//更改仓库数量
	    	WmsStock wmsStock =  wmsStockService.selectWmsStock(storageId,productId);  
	    	wmsStock.setStockNum(numStockActual);
	    	wmsStock.setUpdateBy(wmsStockCheck.getUpdateBy());
			wmsStockService.updateWmsStock(wmsStock);   	
		}
    	return 1;    	
    }
    
	//批量插入入库对应的产品
	private void insertProducts(WmsStockCheck wmsStockCheck) {
		// 先删除之前的对应关系
		wmsStockProductService.deleteWmsStockProductByOpId(wmsStockCheck.getCheckId(), "3");
		for (WmsStockProduct product : wmsStockCheck.getProducts()) {
			product.setOpId(wmsStockCheck.getCheckId());
			product.setType("3");// 盘点
			product.setDelFlag("0");
			product.setCreateBy(wmsStockCheck.getCreateBy());
			wmsStockProductService.insertWmsStockProduct(product);
		}
	}
}
