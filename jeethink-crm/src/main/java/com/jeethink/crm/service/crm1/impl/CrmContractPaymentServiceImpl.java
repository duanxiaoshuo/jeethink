package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeethink.crm.mapper.CrmContractMapper;
import com.jeethink.crm.mapper.CrmContractPaymentMapper;
import com.jeethink.crm.domain.CrmContract;
import com.jeethink.crm.domain.CrmContractPayment;
import com.jeethink.crm.service.crm1.ICrmContractPaymentService;
import com.jeethink.common.core.text.Convert;

/**
 * 合同付款管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-22
 */
@Service
public class CrmContractPaymentServiceImpl implements ICrmContractPaymentService 
{
    @Autowired
    private CrmContractPaymentMapper crmContractPaymentMapper;
    
    @Autowired
    private CrmContractMapper crmContractMapper;

    /**
     * 查询合同付款管理
     * 
     * @param paymentId 合同付款管理ID
     * @return 合同付款管理
     */
    @Override
    public CrmContractPayment selectCrmContractPaymentById(Long paymentId)
    {
        return crmContractPaymentMapper.selectCrmContractPaymentById(paymentId);
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
        return crmContractPaymentMapper.selectCrmContractPaymentList(crmContractPayment);
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
        return crmContractPaymentMapper.insertCrmContractPayment(crmContractPayment);
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
        return crmContractPaymentMapper.updateCrmContractPayment(crmContractPayment);
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
        return crmContractPaymentMapper.deleteCrmContractPaymentByIds(Convert.toStrArray(ids));
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
        return crmContractPaymentMapper.deleteCrmContractPaymentById(paymentId);
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
    	CrmContract crmContract= crmContractMapper.selectCrmContractById(crmContractPayment.getContractId());
//    	double oldPayedMoney=crmContract.getPayedMoney();
//    	double newPayedMoney=crmContract.getPayedMoney()+crmContractPayment.getPayMoney();
//    	crmContract.setPayedMoney(newPayedMoney);
//    	crmContractMapper.updateCrmContract(crmContract);   
//    	
//    	crmContractPayment.setRemark(oldPayedMoney+"->"+newPayedMoney);
    	return updateCrmContractPayment(crmContractPayment);
    }
}
