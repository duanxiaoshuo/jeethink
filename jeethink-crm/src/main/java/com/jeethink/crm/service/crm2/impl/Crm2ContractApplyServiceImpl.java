package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.Crm2ContractApply;
import com.jeethink.crm.domain.CrmContract;
import com.jeethink.crm.domain.CrmContractApply;
import com.jeethink.crm.mapper.Crm2ContractApplyMapper;
import com.jeethink.crm.service.crm2.ICrm2ContractApplyService;
import com.jeethink.crm.service.crm2.ICrm2ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 合同申请Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-10
 */
@Service
public class Crm2ContractApplyServiceImpl implements ICrm2ContractApplyService
{
    @Autowired
    private Crm2ContractApplyMapper crm2ContractApplyMapper;

    @Autowired
	private ICrm2ContractService crm2ContractService;
    /**
     * 查询合同申请
     * 
     * @param contractId 合同申请ID
     * @return 合同申请
     */
    @Override
    public CrmContractApply selectCrmContractApplyById(Long contractId)
    {
        return crm2ContractApplyMapper.selectCrmContractApplyById(contractId);
    }

    /**
     * 查询合同申请列表
     * 
     * @param crmContractApply 合同申请
     * @return 合同申请
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Crm2ContractApply> selectCrmContractApplyList(Crm2ContractApply crmContractApply)
    {
        return crm2ContractApplyMapper.selectCrmContractApplyList(crmContractApply);
    }

    /**
     * 新增合同申请
     * 
     * @param crmContractApply 合同申请
     * @return 结果
     */
    @Override
    public int insertCrmContractApply(CrmContractApply crmContractApply)
    {
        crmContractApply.setCreateTime(DateUtils.getNowDate());
        return crm2ContractApplyMapper.insertCrmContractApply(crmContractApply);
    }

    /**
     * 修改合同申请
     * 
     * @param crmContractApply 合同申请
     * @return 结果
     */
    @Override
    public int updateCrmContractApply(CrmContractApply crmContractApply)
    {
        crmContractApply.setUpdateTime(DateUtils.getNowDate());
        return crm2ContractApplyMapper.updateCrmContractApply(crmContractApply);
    }

    /**
     * 删除合同申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmContractApplyByIds(String ids)
    {
        return crm2ContractApplyMapper.deleteCrmContractApplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合同申请信息
     * 
     * @param contractId 合同申请ID
     * @return 结果
     */
    @Override
    public int deleteCrmContractApplyById(Long contractId)
    {
        return crm2ContractApplyMapper.deleteCrmContractApplyById(contractId);
    }
    
    /**
     * 归档合同申请
     * 
     * @param contractId 合同申请ID
     * @param operName 操作人
     * @return 合同申请
     */
    @Override
    @Transactional
    public int fileCrmContractApplyById(Long contractId,String operName) {
    	//更新合同申请状态
    	CrmContractApply crmContractApply=crm2ContractApplyMapper.selectCrmContractApplyById(contractId);
    	crmContractApply.setContractStatus("4");//已归档
    	crmContractApply.setUpdateBy(operName);
    	this.updateCrmContractApply(crmContractApply);    	
    	//添加合同记录
    	CrmContract crmContract =new CrmContract();
    	crmContract.setCustomerId(crmContractApply.getCustomerId());
    	crmContract.setOrderId(crmContractApply.getOrderId());
    	crmContract.setContractNo(crmContractApply.getContractNo());
    	crmContract.setContractName(crmContractApply.getContractName());
    	crmContract.setContractType(crmContractApply.getContractType());
    	crmContract.setContractContent(crmContractApply.getContractContent());
    	crmContract.setDateStart(crmContractApply.getDateStart());
    	crmContract.setDateEnd(crmContractApply.getDateEnd());
    	crmContract.setPayMethod(crmContractApply.getPayMethod());
    	crmContract.setTotalMoney(crmContractApply.getTotalMoney());
    	crmContract.setSignBy(crmContractApply.getSignBy());
    	crmContract.setSignDate(DateUtils.getNowDate());
    	crmContract.setSignByName(crmContractApply.getSignByName());
    	crmContract.setContractStatus("0");//已保存
    	crmContract.setDelFlag("0");
    	crmContract.setCreateBy(operName);
    	return crm2ContractService.insertCrmContract(crmContract);    	
    }
}
