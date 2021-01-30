package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeethink.crm.mapper.CrmContractApplyMapper;
import com.jeethink.crm.domain.CrmContract;
import com.jeethink.crm.domain.CrmContractApply;
import com.jeethink.crm.service.crm1.ICrmContractApplyService;
import com.jeethink.crm.service.crm1.ICrmContractService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;

/**
 * 合同申请Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-10
 */
@Service
public class CrmContractApplyServiceImpl implements ICrmContractApplyService 
{
    @Autowired
    private CrmContractApplyMapper crmContractApplyMapper;

    @Autowired
	private ICrmContractService crmContractService;
    /**
     * 查询合同申请
     * 
     * @param contractId 合同申请ID
     * @return 合同申请
     */
    @Override
    public CrmContractApply selectCrmContractApplyById(Long contractId)
    {
        return crmContractApplyMapper.selectCrmContractApplyById(contractId);
    }

    /**
     * 查询合同申请列表
     * 
     * @param crmContractApply 合同申请
     * @return 合同申请
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CrmContractApply> selectCrmContractApplyList(CrmContractApply crmContractApply)
    {
        return crmContractApplyMapper.selectCrmContractApplyList(crmContractApply);
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
        return crmContractApplyMapper.insertCrmContractApply(crmContractApply);
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
        return crmContractApplyMapper.updateCrmContractApply(crmContractApply);
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
        return crmContractApplyMapper.deleteCrmContractApplyByIds(Convert.toStrArray(ids));
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
        return crmContractApplyMapper.deleteCrmContractApplyById(contractId);
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
    	CrmContractApply crmContractApply=crmContractApplyMapper.selectCrmContractApplyById(contractId);
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
    	return crmContractService.insertCrmContract(crmContract);    	
    }
}
