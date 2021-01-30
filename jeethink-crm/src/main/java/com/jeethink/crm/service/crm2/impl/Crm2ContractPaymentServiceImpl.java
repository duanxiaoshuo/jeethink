package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.CrmContract;
import com.jeethink.crm.domain.CrmContractPayment;
import com.jeethink.crm.mapper.Crm2ContractMapper;
import com.jeethink.crm.mapper.Crm2ContractPaymentMapper;
import com.jeethink.crm.service.crm2.ICrm2ContractPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 合同付款管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-22
 */
@Service
public class Crm2ContractPaymentServiceImpl implements ICrm2ContractPaymentService
{
    @Autowired
    private Crm2ContractPaymentMapper crm2ContractPaymentMapper;
    
    @Autowired
    private Crm2ContractMapper crm2ContractMapper;

    /**
     * 查询合同付款管理
     * 
     * @param paymentId 合同付款管理ID
     * @return 合同付款管理
     */
    @Override
    public CrmContractPayment selectCrmContractPaymentById(Long paymentId)
    {
        return crm2ContractPaymentMapper.selectCrmContractPaymentById(paymentId);
    }

    /**
     * 查询合同付款管理列表
     * 
     * @param crmContractPayment 合同付款管理
     * @return 合同付款管理
     */
    @Override
    public List<CrmContractPayment> selectCrmContractPaymentList(CrmContractPayment crmContractPayment)
    {
        return crm2ContractPaymentMapper.selectCrmContractPaymentList(crmContractPayment);
    }

    /**
     * 新增合同付款管理
     * 
     * @param crmContractPayment 合同付款管理
     * @return 结果
     */
    @Override
    public int insertCrmContractPayment(CrmContractPayment crmContractPayment)
    {
    	crmContractPayment.setDelFlag("0");
        crmContractPayment.setCreateTime(DateUtils.getNowDate());
        return crm2ContractPaymentMapper.insertCrmContractPayment(crmContractPayment);
    }

    /**
     * 修改合同付款管理
     * 
     * @param crmContractPayment 合同付款管理
     * @return 结果
     */
    @Override
    public int updateCrmContractPayment(CrmContractPayment crmContractPayment)
    {
        crmContractPayment.setUpdateTime(DateUtils.getNowDate());
        return crm2ContractPaymentMapper.updateCrmContractPayment(crmContractPayment);
    }

    /**
     * 删除合同付款管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmContractPaymentByIds(String ids)
    {
        return crm2ContractPaymentMapper.deleteCrmContractPaymentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合同付款管理信息
     * 
     * @param paymentId 合同付款管理ID
     * @return 结果
     */
    @Override
    public int deleteCrmContractPaymentById(Long paymentId)
    {
        return crm2ContractPaymentMapper.deleteCrmContractPaymentById(paymentId);
    }
    
    /**
     * 合同付款管理 审核通过
     * 
     * @param crmContractPayment 合同付款管理
     * @return 结果
     */
    @Override
    @Transactional
    public int auditCrmContractPayment(CrmContractPayment crmContractPayment) {    	
    	CrmContract crmContract= crm2ContractMapper.selectCrmContractById(crmContractPayment.getContractId());
//    	double oldPayedMoney=crmContract.getPayedMoney();
//    	double newPayedMoney=crmContract.getPayedMoney()+crmContractPayment.getPayMoney();
//    	crmContract.setPayedMoney(newPayedMoney);
//    	crm2ContractMapper.updateCrmContract(crmContract);   
//    	
//    	crmContractPayment.setRemark(oldPayedMoney+"->"+newPayedMoney);
    	return updateCrmContractPayment(crmContractPayment);
    }
}
