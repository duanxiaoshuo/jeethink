package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeethink.crm.mapper.CrmTransferMapper;
import com.jeethink.crm.constant.BusinessTypeConstants;
import com.jeethink.crm.domain.CrmClue;
import com.jeethink.crm.domain.CrmContract;
import com.jeethink.crm.domain.CrmContractApply;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmOrder;
import com.jeethink.crm.domain.CrmOrderPrice;
import com.jeethink.crm.domain.CrmTransfer;
import com.jeethink.crm.domain.FinancePay;
import com.jeethink.crm.domain.FinancePayPlan;
import com.jeethink.crm.service.crm1.ICrmClueService;
import com.jeethink.crm.service.crm1.ICrmContractApplyService;
import com.jeethink.crm.service.crm1.ICrmContractService;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm1.ICrmOrderPriceService;
import com.jeethink.crm.service.crm1.ICrmOrderService;
import com.jeethink.crm.service.crm1.ICrmTransferService;
import com.jeethink.crm.service.finace.IFinancePayPlanService;
import com.jeethink.crm.service.finace.IFinancePayService;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;

/**
 * 转交记录Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-19
 */
@Service
public class CrmTransferServiceImpl implements ICrmTransferService 
{
    @Autowired
    private CrmTransferMapper crmTransferMapper;
    
    @Autowired
    private ICrmCustomerService crmCustomerService;
    
    @Autowired
    private ICrmClueService crmClueService;
    
    @Autowired
	private ICrmOrderPriceService crmOrderPriceService;

    @Autowired
	private ICrmOrderService crmOrderService;
    
    @Autowired
    private ICrmContractApplyService crmContractApplyService;
    
    @Autowired
    private ICrmContractService crmContractService;
    
    @Autowired
    private IFinancePayService financePayService;
    
    @Autowired
    private IFinancePayPlanService financePayPlanService;
    /**
     * 查询转交记录
     * 
     * @param trasferId 转交记录ID
     * @return 转交记录
     */
    @Override
    public CrmTransfer selectCrmTransferById(Long trasferId)
    {
        return crmTransferMapper.selectCrmTransferById(trasferId);
    }

    /**
     * 查询转交记录列表
     * 
     * @param crmTransfer 转交记录
     * @return 转交记录
     */
    @Override
    public List<CrmTransfer> selectCrmTransferList(CrmTransfer crmTransfer)
    {
        return crmTransferMapper.selectCrmTransferList(crmTransfer);
    }

    /**
     * 新增转交记录
     * 
     * @param crmTransfer 转交记录
     * @return 结果
     */
    @Override
    public int insertCrmTransfer(CrmTransfer crmTransfer)
    {
    	crmTransfer.setDelFlag("0");
        crmTransfer.setCreateTime(DateUtils.getNowDate());
        return crmTransferMapper.insertCrmTransfer(crmTransfer);
    }

    /**
     * 修改转交记录
     * 
     * @param crmTransfer 转交记录
     * @return 结果
     */
    @Override
    public int updateCrmTransfer(CrmTransfer crmTransfer)
    {
        crmTransfer.setUpdateTime(DateUtils.getNowDate());
        return crmTransferMapper.updateCrmTransfer(crmTransfer);
    }

    /**
     * 删除转交记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmTransferByIds(String ids)
    {
        return crmTransferMapper.deleteCrmTransferByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除转交记录信息
     * 
     * @param trasferId 转交记录ID
     * @return 结果
     */
    @Override
    public int deleteCrmTransferById(Long trasferId)
    {
        return crmTransferMapper.deleteCrmTransferById(trasferId);
    }
    
    /**
     * 批量转交给个人
     * 批量转交公共
     * 
     * @param businessType 业务类型
     * @param ids 需要转交的数据ID
     * @param belongTo 负责人
     * @param reason   转交原因
     * @param operName 操作人
     * @return 结果
     */
    @Override
    @Transactional
    public int transferByIds(String businessType,String ids,String belongTo,String reason, String operName) {
    	
    	Long[] arrIds = Convert.toLongArray(ids);
    	int successNum = 0;
    	for(int j=0;j<arrIds.length;j++){    
    		Long id=arrIds[j];
    		if(businessType.equals(BusinessTypeConstants.Customer)) {//客户
    			CrmCustomer crmCustomer= crmCustomerService.selectCrmCustomerById(id);
        		if(crmCustomer.getBelongTo().equals(belongTo)) {
        			if(arrIds.length==1) {
        				throw new BusinessException(String.format("该客户原负责人和目标负责人相同，无法转交！", id));
        			}
        			continue;
        		}
        		crmCustomer.setIsShare("0");
        		crmCustomer.setBelongTo(belongTo);
        		crmCustomer.setTrasferDate(DateUtils.getNowDate());
        		crmCustomer.setTrasferTo(operName);
        		crmCustomer.setUpdateBy(operName);
        		crmCustomerService.updateCrmCustomer(crmCustomer);
    		}
    		else if(businessType.equals(BusinessTypeConstants.Clue)) {//线索
    			CrmClue crmClue=crmClueService.selectCrmClueById(id);
    			if(crmClue.getBelongTo().equals(belongTo)) {
        			if(arrIds.length==1) {
        				throw new BusinessException(String.format("该线索原负责人和目标负责人相同，无法转交！", id));
        			}
        			continue;
        		}
    			crmClue.setBelongTo(belongTo);
    			crmClue.setTrasferDate(DateUtils.getNowDate());
    			crmClue.setTrasferTo(operName);
    			crmClue.setUpdateBy(operName);
    			crmClueService.updateCrmClue(crmClue);
    		}    
    		else if(businessType.equals(BusinessTypeConstants.OrderPrice)) {//报价单
    			CrmOrderPrice crmOrderPrice=crmOrderPriceService.selectCrmOrderPriceById(id);
    			if(crmOrderPrice.getBelongTo().equals(belongTo)) {
        			if(arrIds.length==1) {
        				throw new BusinessException(String.format("该报价单原负责人和目标负责人相同，无法转交！", id));
        			}
        			continue;
        		}
    			crmOrderPrice.setBelongTo(belongTo);
    			crmOrderPrice.setTrasferDate(DateUtils.getNowDate());
    			crmOrderPrice.setTrasferTo(operName);
    			crmOrderPrice.setUpdateBy(operName);
    			crmOrderPriceService.updateCrmOrderPrice(crmOrderPrice);
    		} 
    		else if(businessType.equals(BusinessTypeConstants.Order)) {//订单
    			CrmOrder crmOrder=crmOrderService.selectCrmOrderById(id);
    			if(crmOrder.getBelongTo().equals(belongTo)) {
        			if(arrIds.length==1) {
        				throw new BusinessException(String.format("该订单原负责人和目标负责人相同，无法转交！", id));
        			}
        			continue;
        		}
    			crmOrder.setBelongTo(belongTo);
    			crmOrder.setTrasferDate(DateUtils.getNowDate());
    			crmOrder.setTrasferTo(operName);
    			crmOrder.setUpdateBy(operName);
    			crmOrderService.updateCrmOrder(crmOrder);
    		} 
    		else if(businessType.equals(BusinessTypeConstants.ContractApply)) {//合同申请
    			CrmContractApply crmContractApply=crmContractApplyService.selectCrmContractApplyById(id);
    			if(crmContractApply.getBelongTo().equals(belongTo)) {
        			if(arrIds.length==1) {
        				throw new BusinessException(String.format("该合同原负责人和目标负责人相同，无法转交！", id));
        			}
        			continue;
        		}
    			crmContractApply.setBelongTo(belongTo);
    			crmContractApply.setTrasferDate(DateUtils.getNowDate());
    			crmContractApply.setTrasferTo(operName);
    			crmContractApply.setUpdateBy(operName);
    			crmContractApplyService.updateCrmContractApply(crmContractApply);
    		}  
    		else if(businessType.equals(BusinessTypeConstants.Contract)) {//合同记录
    			CrmContract crmContract=crmContractService.selectCrmContractById(id);
    			if(crmContract.getBelongTo().equals(belongTo)) {
        			if(arrIds.length==1) {
        				throw new BusinessException(String.format("该合同原负责人和目标负责人相同，无法转交！", id));
        			}
        			continue;
        		}
    			crmContract.setBelongTo(belongTo);
    			crmContract.setTrasferDate(DateUtils.getNowDate());
    			crmContract.setTrasferTo(operName);
    			crmContract.setUpdateBy(operName);
    			crmContractService.updateCrmContract(crmContract);
    		}     		  
    		else if(businessType.equals(BusinessTypeConstants.Pay)) {//回款
    			FinancePay financePay=financePayService.selectFinancePayById(id);
    			if(financePay.getBelongTo().equals(belongTo)) {
        			if(arrIds.length==1) {
        				throw new BusinessException(String.format("该回款原负责人和目标负责人相同，无法转交！", id));
        			}
        			continue;
        		}
    			financePay.setBelongTo(belongTo);
    			financePay.setTrasferDate(DateUtils.getNowDate());
    			financePay.setTrasferTo(operName);
    			financePay.setUpdateBy(operName);
    			financePayService.updateFinancePay(financePay);
    		} 
    		else if(businessType.equals(BusinessTypeConstants.PayPlan)) {//回款计划
    			FinancePayPlan financePayPlan=financePayPlanService.selectFinancePayPlanById(id);
    			if(financePayPlan.getBelongTo().equals(belongTo)) {
        			if(arrIds.length==1) {
        				throw new BusinessException(String.format("该回款计划原负责人和目标负责人相同，无法转交！", id));
        			}
        			continue;
        		}
    			financePayPlan.setBelongTo(belongTo);
    			financePayPlan.setTrasferDate(DateUtils.getNowDate());
    			financePayPlan.setTrasferTo(operName);
    			financePayPlan.setUpdateBy(operName);
    			financePayPlanService.updateFinancePayPlan(financePayPlan);
    		} 
    		//添加转交记录
    		CrmTransfer crmTransfer=new CrmTransfer();
    		crmTransfer.setBusinessId(id);
    		crmTransfer.setBusinessType(businessType);
    		crmTransfer.setBelongTo(belongTo);
    		crmTransfer.setTransferTo(operName);
    		crmTransfer.setTransferType(belongTo.equals("")?"0":"1");
    		crmTransfer.setReason(reason);
    		crmTransfer.setCreateBy(operName);
    		this.insertCrmTransfer(crmTransfer);
    		successNum++;
    	}
    	return successNum;
    }
    
    /**
     * 批量领取
     * 
     * @param businessType 业务类型
     * @param ids 需要领取的数据ID
     * @param reason   领取原因
     * @param operName 操作人
     * @return 结果
     */
    @Override
    @Transactional
    public int getByIds(String businessType,String ids,String reason, String operName) {
    	
    	Long[] arrIds = Convert.toLongArray(ids);
    	int successNum = 0;
    	for(int j=0;j<arrIds.length;j++){    
    		Long id=arrIds[j];
    		if(businessType.equals(BusinessTypeConstants.Customer)) {//客户
    			CrmCustomer crmCustomer= crmCustomerService.selectCrmCustomerById(id);
        		crmCustomer.setBelongTo(operName);
        		crmCustomer.setUpdateBy(operName);
        		crmCustomerService.updateCrmCustomer(crmCustomer);
    		}
    		else if(businessType.equals(BusinessTypeConstants.Clue)) {//线索
    			CrmClue crmClue=crmClueService.selectCrmClueById(id);
    			crmClue.setBelongTo(operName);
    			crmClue.setUpdateBy(operName);
    			crmClueService.updateCrmClue(crmClue);
    		}
    		
    		//添加领取记录
    		CrmTransfer crmTransfer=new CrmTransfer();
    		crmTransfer.setBusinessId(id);
    		crmTransfer.setBusinessType(businessType);
    		crmTransfer.setBelongTo(operName);
    		crmTransfer.setTransferTo(operName);
    		crmTransfer.setTransferType("2");//领取
    		crmTransfer.setReason(reason);
    		crmTransfer.setCreateBy(operName);
    		this.insertCrmTransfer(crmTransfer);
    		successNum++;
    	}
    	return successNum;
    }
}
