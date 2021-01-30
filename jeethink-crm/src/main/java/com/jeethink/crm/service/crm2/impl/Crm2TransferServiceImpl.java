package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.constant.BusinessTypeConstants;
import com.jeethink.crm.domain.*;
import com.jeethink.crm.mapper.Crm2TransferMapper;
import com.jeethink.crm.service.crm2.*;
import com.jeethink.crm.service.finace2.IFinance2PayPlanService;
import com.jeethink.crm.service.finace2.IFinance2PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 转交记录Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-19
 */
@Service
public class Crm2TransferServiceImpl implements ICrm2TransferService 
{
    @Autowired
    private Crm2TransferMapper crm2TransferMapper;
    
    @Autowired
    private ICrm2CustomerService crm2CustomerService;
    
    @Autowired
    private ICrm2ClueService crm2ClueService;
    
    @Autowired
	private ICrm2OrderPriceService crm2OrderPriceService;

    @Autowired
	private ICrm2OrderService crm2OrderService;
    
    @Autowired
    private ICrm2ContractApplyService crm2ContractApplyService;
    
    @Autowired
    private ICrm2ContractService crm2ContractService;
    
    @Autowired
    private IFinance2PayService finance2PayService;
    
    @Autowired
    private IFinance2PayPlanService finance2PayPlanService;
    /**
     * 查询转交记录
     * 
     * @param trasferId 转交记录ID
     * @return 转交记录
     */
    @Override
    public CrmTransfer selectCrmTransferById(Long trasferId)
    {
        return crm2TransferMapper.selectCrmTransferById(trasferId);
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
        return crm2TransferMapper.selectCrmTransferList(crmTransfer);
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
        return crm2TransferMapper.insertCrmTransfer(crmTransfer);
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
        return crm2TransferMapper.updateCrmTransfer(crmTransfer);
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
        return crm2TransferMapper.deleteCrmTransferByIds(Convert.toStrArray(ids));
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
        return crm2TransferMapper.deleteCrmTransferById(trasferId);
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
    			CrmCustomer crmCustomer= crm2CustomerService.selectCrmCustomerById(id);
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
        		crm2CustomerService.updateCrmCustomer(crmCustomer);
    		}
    		else if(businessType.equals(BusinessTypeConstants.Clue)) {//线索
    			CrmClue crmClue=crm2ClueService.selectCrmClueById(id);
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
    			crm2ClueService.updateCrmClue(crmClue);
    		}    
    		else if(businessType.equals(BusinessTypeConstants.OrderPrice)) {//报价单
    			CrmOrderPrice crmOrderPrice=crm2OrderPriceService.selectCrmOrderPriceById(id);
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
    			crm2OrderPriceService.updateCrmOrderPrice(crmOrderPrice);
    		} 
    		else if(businessType.equals(BusinessTypeConstants.Order)) {//订单
    			CrmOrder crmOrder=crm2OrderService.selectCrmOrderById(id);
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
    			crm2OrderService.updateCrmOrder(crmOrder);
    		} 
    		else if(businessType.equals(BusinessTypeConstants.ContractApply)) {//合同申请
    			CrmContractApply crmContractApply=crm2ContractApplyService.selectCrmContractApplyById(id);
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
    			crm2ContractApplyService.updateCrmContractApply(crmContractApply);
    		}  
    		else if(businessType.equals(BusinessTypeConstants.Contract)) {//合同记录
    			CrmContract crmContract=crm2ContractService.selectCrmContractById(id);
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
    			crm2ContractService.updateCrmContract(crmContract);
    		}     		  
    		else if(businessType.equals(BusinessTypeConstants.Pay)) {//回款
    			FinancePay financePay=finance2PayService.selectFinancePayById(id);
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
    			finance2PayService.updateFinancePay(financePay);
    		} 
    		else if(businessType.equals(BusinessTypeConstants.PayPlan)) {//回款计划
    			FinancePayPlan financePayPlan=finance2PayPlanService.selectFinancePayPlanById(id);
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
    			finance2PayPlanService.updateFinancePayPlan(financePayPlan);
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
    			CrmCustomer crmCustomer= crm2CustomerService.selectCrmCustomerById(id);
        		crmCustomer.setBelongTo(operName);
        		crmCustomer.setUpdateBy(operName);
        		crm2CustomerService.updateCrmCustomer(crmCustomer);
    		}
    		else if(businessType.equals(BusinessTypeConstants.Clue)) {//线索
    			CrmClue crmClue=crm2ClueService.selectCrmClueById(id);
    			crmClue.setBelongTo(operName);
    			crmClue.setUpdateBy(operName);
    			crm2ClueService.updateCrmClue(crmClue);
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
