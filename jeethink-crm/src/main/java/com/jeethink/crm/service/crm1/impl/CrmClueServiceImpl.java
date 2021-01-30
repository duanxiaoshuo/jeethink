package com.jeethink.crm.service.crm1.impl;

import java.util.List;
import com.jeethink.common.utils.DateUtils;
import com.jeethink.common.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeethink.crm.mapper.CrmClueMapper;
import com.jeethink.crm.domain.CrmClue;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmPerson;
import com.jeethink.crm.service.crm1.ICrmClueService;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm1.ICrmPersonService;
import com.jeethink.common.annotation.DataScope;
import com.jeethink.common.core.text.Convert;
import com.jeethink.common.exception.BusinessException;

/**
 * 线索管理Service业务层处理
 * 
 * @author jeethink
 * @date 2020-04-06
 */
@Service
public class CrmClueServiceImpl implements ICrmClueService 
{
	private static final Logger log = LoggerFactory.getLogger(ICrmCustomerService.class);
    @Autowired
    private CrmClueMapper crmClueMapper;
    
    @Autowired
    private ICrmCustomerService crmCustomerService;
    
    @Autowired
    private ICrmPersonService crmPersonService;

    /**
     * 查询线索管理
     * 
     * @param clueId 线索管理ID
     * @return 线索管理
     */
    @Override
    public CrmClue selectCrmClueById(Long clueId)
    {
        return crmClueMapper.selectCrmClueById(clueId);
    }

    /**
     * 查询线索管理列表
     * 
     * @param crmClue 线索管理
     * @return 线索管理
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CrmClue> selectCrmClueList(CrmClue crmClue)
    {
        return crmClueMapper.selectCrmClueList(crmClue);
    }
    
    /**
     * 查询线索管理列表 公共线索
     * 
     * @param crmClue 线索管理
     * @return 线索管理集合
     */
    @Override
    public List<CrmClue> selectCrmClueListPublic(CrmClue crmClue){
    	return crmClueMapper.selectCrmClueListPublic(crmClue);
    }

    /**
     * 新增线索管理
     * 
     * @param crmClue 线索管理
     * @return 结果
     */
    @Override
    public int insertCrmClue(CrmClue crmClue)
    {
        crmClue.setCreateTime(DateUtils.getNowDate());
        return crmClueMapper.insertCrmClue(crmClue);
    }

    /**
     * 修改线索管理
     * 
     * @param crmClue 线索管理
     * @return 结果
     */
    @Override
    public int updateCrmClue(CrmClue crmClue)
    {
        crmClue.setUpdateTime(DateUtils.getNowDate());
        return crmClueMapper.updateCrmClue(crmClue);
    }

    /**
     * 删除线索管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCrmClueByIds(String ids)
    {
        return crmClueMapper.deleteCrmClueByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除线索管理信息
     * 
     * @param clueId 线索管理ID
     * @return 结果
     */
    @Override
    public int deleteCrmClueById(Long clueId)
    {
        return crmClueMapper.deleteCrmClueById(clueId);
    }
    
    /**
     * 线索管理 作废
     * 
     * @param clueId 线索管理ID
     * @param isCancel 0  1
     * @return 结果
     */
    @Override
    public int cancelCrmClueById(Long clueId,String isCancel){
    	return crmClueMapper.cancelCrmClueById(clueId, isCancel);
    }
    
    /**
     * 线索管理 转成客户
     * 
     * @param clueId 线索管理ID
     * @return 结果
     */
    @Override
    @Transactional
    public int convertCrmClueById(Long clueId,String loginName) {
    	CrmClue crmClue=crmClueMapper.selectCrmClueById(clueId);
    	crmClue.setClueStatus("2");
    	crmClueMapper.updateCrmClue(crmClue);
    	
    	CrmCustomer crmCustomer=new CrmCustomer();
    	crmCustomer.setDelFlag("0");
    	crmCustomer.setCustomerType("0");//默认个人客户
    	crmCustomer.setCreateBy(loginName);
    	crmCustomer.setCreateTime(DateUtils.getNowDate());
    	crmCustomer.setCustomerName(crmClue.getCompany());
    	crmCustomer.setAddress(crmClue.getAddress());
    	crmCustomer.setCustomerSource(crmClue.getClueSource());
    	crmCustomer.setGetDate(DateUtils.getNowDate());
    	crmCustomer.setSourceBelongTo(loginName);
    	crmCustomer.setBelongTo(loginName);
    	crmCustomerService.insertCrmCustomer(crmCustomer);
    	Long customerId=crmCustomer.getCustomerId();
    	
    	CrmPerson crmPerson=new CrmPerson();
    	crmPerson.setDelFlag("0");
    	crmPerson.setCreateBy(loginName);
    	crmPerson.setCreateTime(DateUtils.getNowDate());
    	crmPerson.setPersonName(crmClue.getName());
    	crmPerson.setDuty(crmClue.getDuty());
    	crmPerson.setMobile(crmClue.getMobile());
    	crmPerson.setEmail(crmClue.getEmail());
    	crmPerson.setQq(crmClue.getQq());
    	crmPerson.setWechat(crmClue.getWechat());
    	crmPerson.setWangwang(crmClue.getWangwang());
    	crmPerson.setMsn(crmClue.getMsn());
    	crmPerson.setSex(crmClue.getSex());
    	crmPerson.setCustomerId(customerId);    	
    	return crmPersonService.insertCrmPerson(crmPerson);
    }
    
    /**
     * 导入线索数据
     * 
     * @param clueList 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    @Transactional
    public String importClue(List<CrmClue> crmClueList, Boolean isUpdateSupport, String operName){    	
    	if (StringUtils.isNull(crmClueList) || crmClueList.size() == 0)
        {
            throw new BusinessException("导入线索数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (CrmClue crmClue : crmClueList)
        {   	           	
            try
            {
            	String mobile=crmClue.getMobile();
            	if(StringUtils.isEmpty(mobile)) {
            		failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、手机号 :" + mobile +"  为空，不导入该条记录");
                    continue;
            	}
                // 验证是否存在这个线索
            	CrmClue c = crmClueMapper.selectCrmClueByMobile(mobile);
                if (StringUtils.isNull(c))
                {            	
                	crmClue.setClueStatus("0");
                	crmClue.setDelFlag("0");
                	crmClue.setCreateBy(operName);
                	crmClue.setSourceBelongTo(operName);
                	crmClue.setBelongTo(operName);
                    this.insertCrmClue(crmClue);       
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 :" + crmClue.getName() +",手机号:"+ crmClue.getMobile()+" 导入成功");
                }
                else if (isUpdateSupport)
                {
                	crmClue.setClueId(c.getClueId());
                	crmClue.setUpdateBy(operName);
                    this.updateCrmClue(crmClue);  
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 :" + crmClue.getName() +",手机号:"+ crmClue.getMobile() +" 更新成功");                    
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、姓名 :" + crmClue.getName() +",手机号:"+ crmClue.getMobile()+" 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、姓名: " + crmClue.getName() +",手机号:"+ crmClue.getMobile() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    	
    }
    
    /**
     * 校验手机号码是否唯一
    *
    * @param user 用户信息
    * @return
    */
   @Override
   public String checkMobileUnique(CrmClue crmClue)
   {
       Long clueId = StringUtils.isNull(crmClue.getClueId()) ? -1L : crmClue.getClueId();
       CrmClue info = crmClueMapper.selectCrmClueByMobile(crmClue.getMobile());
       if (StringUtils.isNotNull(info) && info.getClueId().longValue() != clueId.longValue())
       {
           return "1";
       }
       return "0";
   }
   /**
    * 将线索转入线索池
    * 未跟进过和超过30天未跟进的线索
    */
   @Override
   public void convertCrmClueToPublic() {
   	List<CrmClue> listClueNoFollow= crmClueMapper.selectCrmClueListNoFollow();
   	List<CrmClue> listClueFollowMoreThan30= crmClueMapper.selectCrmClueListFollowMoreThan30();
   	if(listClueNoFollow!=null&&listClueNoFollow.size()>0) {
       	for(int i=0;i<listClueNoFollow.size();i++) {
       		CrmClue clue=listClueNoFollow.get(i);
       		if(clue.getBelongTo()==null||clue.getBelongTo().equals("")) {
       			break;
       		}
       		clue.setBelongTo("");
       		clue.setUpdateBy("system");
       		this.updateCrmClue(clue);
       	}
   	}
   	if(listClueFollowMoreThan30!=null&&listClueFollowMoreThan30.size()>0) {
       	for(int i=0;i<listClueFollowMoreThan30.size();i++) {
       		CrmClue clue=listClueFollowMoreThan30.get(i);
       		if(clue.getBelongTo()==null||clue.getBelongTo().equals("")) {
       			break;
       		}
       		clue.setBelongTo("");
       		clue.setUpdateBy("system");
       		this.updateCrmClue(clue);
       	}
   	}
   }
}
