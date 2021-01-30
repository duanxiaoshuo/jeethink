package com.jeethink.crm.service.wms.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.common.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeethink.crm.mapper.WmsStockAllocationMapper;
import com.jeethink.crm.domain.WmsStock;
import com.jeethink.crm.domain.WmsStockAllocation;
import com.jeethink.crm.domain.WmsStockEnter;
import com.jeethink.crm.domain.WmsStockHistory;
import com.jeethink.crm.domain.WmsStockOut;
import com.jeethink.crm.domain.WmsStockProduct;
import com.jeethink.crm.service.wms.IWmsStockAllocationService;
import com.jeethink.crm.service.wms.IWmsStockEnterService;
import com.jeethink.crm.service.wms.IWmsStockHistoryService;
import com.jeethink.crm.service.wms.IWmsStockOutService;
import com.jeethink.crm.service.wms.IWmsStockProductService;
import com.jeethink.crm.service.wms.IWmsStockService;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;

/**
 * 调拨单Service业务层处理
 * 
 * @author jeethink
 * @date 2020-08-12
 */
@Service
public class WmsStockAllocationServiceImpl implements IWmsStockAllocationService 
{
    @Autowired
    private WmsStockAllocationMapper wmsStockAllocationMapper;
    
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
     * 查询调拨单
     * 
     * @param allocationId 调拨单ID
     * @return 调拨单
     */
    @Override
    public WmsStockAllocation selectWmsStockAllocationById(Long allocationId)
    {
        return wmsStockAllocationMapper.selectWmsStockAllocationById(allocationId);
    }

    /**
     * 查询调拨单列表
     * 
     * @param wmsStockAllocation 调拨单
     * @return 调拨单
     */
    @Override
    public List<WmsStockAllocation> selectWmsStockAllocationList(WmsStockAllocation wmsStockAllocation)
    {
        return wmsStockAllocationMapper.selectWmsStockAllocationList(wmsStockAllocation);
    }

    /**
     * 新增调拨单
     * 
     * @param wmsStockAllocation 调拨单
     * @return 结果
     */
    @Override
    public int insertWmsStockAllocation(WmsStockAllocation wmsStockAllocation)
    {
        wmsStockAllocation.setCreateTime(DateUtils.getNowDate());
        wmsStockAllocationMapper.insertWmsStockAllocation(wmsStockAllocation);
		if(wmsStockAllocation.getProducts()!=null&&wmsStockAllocation.getProducts().size()>0) {
			insertProducts(wmsStockAllocation);
		}
		return 1;
    }

    /**
     * 修改调拨单
     * 
     * @param wmsStockAllocation 调拨单
     * @return 结果
     */
    @Override
    public int updateWmsStockAllocation(WmsStockAllocation wmsStockAllocation)
    {
        wmsStockAllocation.setUpdateTime(DateUtils.getNowDate());
        wmsStockAllocationMapper.updateWmsStockAllocation(wmsStockAllocation);
		if(wmsStockAllocation.getProducts()!=null&&wmsStockAllocation.getProducts().size()>0) {
			insertProducts(wmsStockAllocation);
		}
		return 1;
    }

    /**
     * 删除调拨单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockAllocationByIds(String ids)
    {
        return wmsStockAllocationMapper.deleteWmsStockAllocationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除调拨单信息
     * 
     * @param allocationId 调拨单ID
     * @return 结果
     */
    @Override
    public int deleteWmsStockAllocationById(Long allocationId)
    {
		// 先删除之前的对应关系
		wmsStockProductService.deleteWmsStockProductByOpId(allocationId, "2");
        return wmsStockAllocationMapper.deleteWmsStockAllocationById(allocationId);
    }

    /**
     * 审核调拨单-拒绝
     * 
     * @param wmsStockAllocation 调拨单
     * @return 结果
     */
    @Override
    public int auditNoWmsStockAllocation(WmsStockAllocation wmsStockAllocation) {
    	wmsStockAllocation.setAllocationStatus("3");//已驳回
    	wmsStockAllocation.setUpdateTime(DateUtils.getNowDate());
        return wmsStockAllocationMapper.updateWmsStockAllocation(wmsStockAllocation);
    }

    /**
     * 审核调拨单-通过
     * 
     * @param wmsStockAllocation 调拨单
     * @return 结果
     */
    @Override
	@Transactional
    public int auditOkWmsStockAllocation(WmsStockAllocation wmsStockAllocation)
    {
    	long allocationId=wmsStockAllocation.getAllocationId();
    	long outStorageId=wmsStockAllocation.getOutStorageId();
    	long enterStorageId=wmsStockAllocation.getEnterStorageId();
    	wmsStockAllocation.setAllocationStatus("2");//已审核
    	wmsStockAllocation.setUpdateTime(DateUtils.getNowDate());
    	wmsStockAllocationMapper.updateWmsStockAllocation(wmsStockAllocation);
    	
    	WmsStockProduct wmsStockProduct=new WmsStockProduct();
		wmsStockProduct.setOpId(allocationId);
		wmsStockProduct.setType("2");//调拨
		List<WmsStockProduct> products= wmsStockProductService.selectWmsStockProductList(wmsStockProduct);
		WmsStockOut wmsStockOut=new WmsStockOut();//出库单
    	WmsStockEnter wmsStockEnter=new WmsStockEnter();//入库单
		for (WmsStockProduct product : products) {			
	    	long alocationNum=product.getProductNum2();//调拨数量
	    	long productId=product.getProductId();
			String productName=product.getProductName();
			
	    	//出库仓库，更改库存
	    	WmsStock wmsStock_out =  wmsStockService.selectWmsStock(outStorageId,productId);  
	    	long oldOutNum=wmsStock_out.getStockNum();
	    	long newOutNUm=oldOutNum-alocationNum;
	    	if(oldOutNum<alocationNum) {
	    		throw new BusinessException(String.format("产品：%s 库存数量小于调拨数量,不能调拨",productName));
	    	}
	    	wmsStock_out.setStockNum(newOutNUm);
	    	wmsStock_out.setUpdateBy(wmsStockAllocation.getUpdateBy());
			wmsStockService.updateWmsStock(wmsStock_out);   
			
			//增加出库单，只增加一次
    		if(StringUtils.isEmpty(wmsStockOut.getOutId()+"")||StringUtils.isNull(wmsStockOut.getOutId())) {
        		wmsStockOut.setTitle("调拨出库");
    	    	wmsStockOut.setOutStatus("2");//已审核
    	    	wmsStockOut.setStorageId(outStorageId);
    	    	wmsStockOut.setOutBy(wmsStockAllocation.getAllocationBy());
    	    	wmsStockOut.setOutType("2");//调拨出库
    	    	wmsStockOut.setOutDate(DateUtils.getNowDate());
    	    	wmsStockOut.setCreateBy(wmsStockAllocation.getUpdateBy());
    	    	wmsStockOutService.insertWmsStockOut(wmsStockOut);	    			
    		}    	    	
			
			//增加对应产品
        	WmsStockProduct wmsStockProduct_out=new WmsStockProduct();
        	wmsStockProduct_out.setOpId(wmsStockOut.getOutId());
        	wmsStockProduct_out.setProductId(productId);
        	wmsStockProduct_out.setProductName(productName);
        	wmsStockProduct_out.setProductNum1(alocationNum);
        	wmsStockProduct_out.setType("1");// 出库
        	wmsStockProduct_out.setDelFlag("0");
        	wmsStockProduct_out.setCreateBy(wmsStockAllocation.getCreateBy());
			wmsStockProductService.insertWmsStockProduct(wmsStockProduct_out);
			
			//增加出库，库存记录
			WmsStockHistory wmsStockHistoryOut=new WmsStockHistory();
			wmsStockHistoryOut.setStorageId(outStorageId);
			wmsStockHistoryOut.setProductId(productId);
			wmsStockHistoryOut.setHistoryType("3");//调拨出库
			wmsStockHistoryOut.setNumBefore(oldOutNum);
			wmsStockHistoryOut.setNumAfter(newOutNUm);
			wmsStockHistoryOut.setHistoryDate(DateUtils.getNowDate());
			wmsStockHistoryOut.setHistoryBy(wmsStockAllocation.getAllocationBy());
			wmsStockHistoryOut.setDelFlag("0");
			wmsStockHistoryOut.setCreateBy(wmsStockAllocation.getUpdateBy());
			wmsStockHistoryService.insertWmsStockHistory(wmsStockHistoryOut);
			
	    	//入库仓库，更改库存
			long oldEnterNum=0;
			long newEnterNum=alocationNum;
	    	WmsStock wmsStock_enter =  wmsStockService.selectWmsStock(enterStorageId,productId);      	    	
	    	if (wmsStock_enter != null) {
	    		oldEnterNum=wmsStock_enter.getStockNum();
	    		newEnterNum=oldEnterNum+alocationNum;
				wmsStock_enter.setStockNum(newEnterNum);
				wmsStock_enter.setUpdateBy(wmsStockAllocation.getUpdateBy());
				wmsStockService.updateWmsStock(wmsStock_enter);
			}
	    	else {
	    		WmsStock wmsStock=new WmsStock();
	    		wmsStock.setStorageId(enterStorageId);
	    		wmsStock.setStockNum(alocationNum);
	    		wmsStock.setProductId(productId);
	    		wmsStock.setCreateBy(wmsStockAllocation.getUpdateBy());
				wmsStockService.insertWmsStock(wmsStock);
	    	}	    	

    		//增加入库单，只增加一次
    		if(StringUtils.isEmpty(wmsStockEnter.getEnterId()+"")||StringUtils.isNull(wmsStockEnter.getEnterId())) {
    			wmsStockEnter.setTitle("调拨入库");
    	    	wmsStockEnter.setEnterStatus("2");//已审核
    	    	wmsStockEnter.setStorageId(enterStorageId);
    	    	wmsStockEnter.setEnterBy(wmsStockAllocation.getAllocationBy());
    	    	wmsStockEnter.setEnterType("3");//调拨入库 
    	    	wmsStockEnter.setEnterDate(DateUtils.getNowDate());
    	    	wmsStockEnter.setCreateBy(wmsStockAllocation.getUpdateBy());
    	    	wmsStockEnterService.insertWmsStockEnter(wmsStockEnter);      			
    		}	

        	//增加对应产品
        	WmsStockProduct wmsStockProduct_enter=new WmsStockProduct();
        	wmsStockProduct_enter.setOpId(wmsStockEnter.getEnterId());
        	wmsStockProduct_enter.setProductId(productId);
        	wmsStockProduct_enter.setProductName(productName);
        	wmsStockProduct_enter.setProductNum1(alocationNum);
        	wmsStockProduct_enter.setType("0");// 入库
        	wmsStockProduct_enter.setDelFlag("0");
        	wmsStockProduct_enter.setCreateBy(wmsStockAllocation.getCreateBy());
			wmsStockProductService.insertWmsStockProduct(wmsStockProduct_enter);
			
			//增加入库，库存记录
			WmsStockHistory wmsStockHistoryEnter=new WmsStockHistory();
			wmsStockHistoryEnter.setStorageId(enterStorageId);
			wmsStockHistoryEnter.setProductId(productId);
			wmsStockHistoryEnter.setHistoryType("2");//调拨入库
			wmsStockHistoryEnter.setNumBefore(oldEnterNum);
			wmsStockHistoryEnter.setNumAfter(newEnterNum);
			wmsStockHistoryEnter.setHistoryDate(DateUtils.getNowDate());
			wmsStockHistoryEnter.setHistoryBy(wmsStockAllocation.getAllocationBy());
			wmsStockHistoryEnter.setDelFlag("0");
			wmsStockHistoryEnter.setCreateBy(wmsStockAllocation.getUpdateBy());
			wmsStockHistoryService.insertWmsStockHistory(wmsStockHistoryEnter);			
		}
    	return 1;
    }

	//批量插入入库对应的产品
	private void insertProducts(WmsStockAllocation wmsStockAllocation) {
		// 先删除之前的对应关系
		wmsStockProductService.deleteWmsStockProductByOpId(wmsStockAllocation.getAllocationId(), "2");
		for (WmsStockProduct product : wmsStockAllocation.getProducts()) {
			product.setOpId(wmsStockAllocation.getAllocationId());
			product.setType("2");// 调拨
			product.setDelFlag("0");
			product.setCreateBy(wmsStockAllocation.getCreateBy());
			wmsStockProductService.insertWmsStockProduct(product);
		}
	}
}
