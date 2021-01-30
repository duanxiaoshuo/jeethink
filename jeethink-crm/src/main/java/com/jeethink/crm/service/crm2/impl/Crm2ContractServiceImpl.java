package com.jeethink.crm.service.crm2.impl;

import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.crm.domain.CrmContract;
import com.jeethink.crm.mapper.Crm2ContractMapper;
import com.jeethink.crm.service.crm2.ICrm2ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 合同管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-03-18
 */
@Service
public class Crm2ContractServiceImpl implements ICrm2ContractService {
	@Autowired
	private Crm2ContractMapper crm2ContractMapper;

	/**
	 * 查询合同管理
	 * 
	 * @param contractId 合同管理ID
	 * @return 合同管理
	 */
	@Override
	public CrmContract selectCrmContractById(Long contractId) {
		return crm2ContractMapper.selectCrmContractById(contractId);
	}

	/**
	 * 查询合同管理列表
	 * 
	 * @param crmContract 合同管理
	 * @return 合同管理
	 */
	@Override
	@DataScope(deptAlias = "d", userAlias = "u")
	public List<CrmContract> selectCrmContractList(CrmContract crmContract) {
		return crm2ContractMapper.selectCrmContractList(crmContract);
	}

	/**
	 * 新增合同管理
	 * 
	 * @param crmContract 合同管理
	 * @return 结果
	 */
	@Override
	public int insertCrmContract(CrmContract crmContract) {
		crmContract.setDelFlag("0");
		crmContract.setCreateTime(DateUtils.getNowDate());
		return crm2ContractMapper.insertCrmContract(crmContract);
	}

	/**
	 * 修改合同管理
	 * 
	 * @param crmContract 合同管理
	 * @return 结果
	 */
	@Override
	public int updateCrmContract(CrmContract crmContract) {
		crmContract.setUpdateTime(DateUtils.getNowDate());
		return crm2ContractMapper.updateCrmContract(crmContract);
	}

	/**
	 * 删除合同管理对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteCrmContractByIds(String ids) {
		String[] enterIds = Convert.toStrArray(ids);
		for (int j = 0; j < enterIds.length; j++) {
			Long enterId = Long.parseLong(enterIds[j]);
			CrmContract crmContract = crm2ContractMapper.selectCrmContractById(enterId);
			if (!crmContract.getContractStatus().equals("0")) {
				throw new BusinessException(String.format("合同%1$s不是已保存状态,不能删除", enterIds[j]));
			}
		}
		return crm2ContractMapper.deleteCrmContractByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除合同管理信息
	 * 
	 * @param contractId 合同管理ID
	 * @return 结果
	 */
	@Override
	public int deleteCrmContractById(Long contractId) {
		return crm2ContractMapper.deleteCrmContractById(contractId);
	}

	/**
	 * 更新合同的状态 
	 * 2 已审核 
	 * 4 执行中 在合同期内
	 *  5 已完成 过期一个月内 
	 *  6 已失效 过期一个月外
	 */
	public void updateContractStatus() {
		Date nowDate = new Date();// 当前日期
		Calendar calendar = Calendar.getInstance();

		CrmContract crmContract = new CrmContract();
		List<CrmContract> list = selectCrmContractList(crmContract);
		for (CrmContract contract : list) {
			Date endDate = contract.getDateEnd();
			calendar.setTime(endDate);
			calendar.add(Calendar.MONTH, 1);
			Date endDateMonth = calendar.getTime();// 合同结束日期加一个月

			// 已经通过审核
			if (contract.getContractStatus().equals("2")) {
				// 合同结束日期大于当前日期，合同状态为 4：执行中
				if (endDate.after(nowDate)) {
					contract.setContractStatus("4");
					updateCrmContract(contract);
				}
			}
			// 执行中的合同
			else if (contract.getContractStatus().equals("4")) {
				// 当前日期大于合同结束日期，但是在过期一个月内 ，合同状态为 5：已完成
				if (nowDate.after(endDate) && nowDate.before(endDateMonth)) {
					contract.setContractStatus("5");
					updateCrmContract(contract);
				}
			}
			// 已完成的合同
			else if (contract.getContractStatus().equals("5")) {
				// 当前日期大于合同结束日期，但是在过期一个月外 ，合同状态为 6：已失效
				if (nowDate.after(endDateMonth)) {
					contract.setContractStatus("6");
					updateCrmContract(contract);
				}
			}

		}
	}
}
