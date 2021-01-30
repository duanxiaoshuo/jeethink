package com.jeethink.web.controller.crm;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.crm.domain.CrmContract;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.service.crm1.ICrmContractService;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.core.text.Convert;

/**
 * 合同管理Controller
 * 
 * @author jeethink
 * @date 2020-03-18
 */
@Controller
@RequestMapping("/crm/contract")
public class CrmContractController extends BaseController
{
    private String prefix = "crm/contract";
    
    @Autowired
    private ICrmCustomerService crmCustomerService;  

    @Autowired
    private ICrmContractService crmContractService;

    @RequiresPermissions("crm:contract:view")
    @GetMapping()
    public String contract()
    {
        return prefix + "/contract";
    }

    /**
     * 查询合同管理列表
     */
    @RequiresPermissions("crm:contract:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmContract crmContract)
    {
        startPage();
        List<CrmContract> list = crmContractService.selectCrmContractList(crmContract);
        return getDataTable(list);
    }

    /**
     * 导出合同管理列表
     */
    @RequiresPermissions("crm:contract:export")
    @Log(title = "合同管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmContract crmContract)
    {
        List<CrmContract> list = crmContractService.selectCrmContractList(crmContract);
        ExcelUtil<CrmContract> util = new ExcelUtil<CrmContract>(CrmContract.class);
        return util.exportExcel(list, "contract");
    }

    /**
     * 新增合同管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合同管理
     */
    @RequiresPermissions("crm:contract:add")
    @Log(title = "合同管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmContract crmContract)
    {
    	String loginName=ShiroUtils.getLoginName();
    	crmContract.setDelFlag("0");
    	crmContract.setContractStatus("0");//已保存
    	crmContract.setCreateBy(loginName);
    	crmContract.setSourceBelongTo(loginName);
    	crmContract.setBelongTo(loginName);
        return toAjax(crmContractService.insertCrmContract(crmContract));
    }

    /**
     * 修改合同管理
     */
    @GetMapping("/edit/{contractId}")
    public String edit(@PathVariable("contractId") Long contractId, ModelMap mmap)
    {
        CrmContract crmContract = crmContractService.selectCrmContractById(contractId);
        mmap.put("crmContract", crmContract);
        return prefix + "/edit";
    }

    /**
     * 修改保存合同管理
     */
    @RequiresPermissions("crm:contract:edit")
    @Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmContract crmContract)
    {
    	crmContract.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractService.updateCrmContract(crmContract));
    }

    /**
     * 删除合同管理
     */
    @RequiresPermissions("crm:contract:remove")
    @Log(title = "合同管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmContractService.deleteCrmContractByIds(ids));
    }
    
    /**
     * 合同管理  提交
     */
    @RequiresPermissions("crm:contract:submit")
    @Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @PostMapping("/submit")
    @ResponseBody
    public AjaxResult submitSave(CrmContract crmContract)
    {
    	crmContract.setContractStatus("1");//已提交
    	crmContract.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractService.updateCrmContract(crmContract));
    }
    
    /**
     * 合同管理  审核
     */
    @RequiresPermissions("crm:contract:audit")
    @Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @PostMapping("/auditOk/{contractId}")
    @ResponseBody
    public AjaxResult auditOkSave(@PathVariable("contractId") Long contractId)
    {
    	CrmContract crmContract = crmContractService.selectCrmContractById(contractId);    	
    	crmContract.setContractStatus("2");//已审核
    	crmContract.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractService.updateCrmContract(crmContract));
    }
    
    /**
     * 合同管理  审核
     */
    @RequiresPermissions("crm:contract:audit")
    @Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @PostMapping("/auditNo/{contractId}")
    @ResponseBody
    public AjaxResult auditNoSave(@PathVariable("contractId") Long contractId)
    {
    	CrmContract crmContract = crmContractService.selectCrmContractById(contractId);    	
    	crmContract.setContractStatus("3");//已驳回    	
    	crmContract.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractService.updateCrmContract(crmContract));
    }
    
    /**
     * 新增合同
     * 如果来自于客户详情新增，则有customerId
     */
    @GetMapping("/addContract/{customerId}")
    public String addContract(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
    	CrmCustomer crmCustomer = crmCustomerService.selectCrmCustomerById(customerId);
        mmap.put("crmCustomer", crmCustomer);
        return prefix + "/addContract";
    }
    
    /**
     * 查询选择合同 （合同付款使用）
     */
    @GetMapping("/selectContractPayment")
    public String selectContractPayment()
    {
        return prefix + "/selectContractPayment";
    }
    
    /**
     * 查询选择合同 （合同发票使用）
     */
    @GetMapping("/selectContractInvoice")
    public String selectContractInvoice()
    {
        return prefix + "/selectContractInvoice";
    }
    
    /**
     * 查询已经审核的合同
     */
    @PostMapping("/listAuditContract")
    @ResponseBody
    public TableDataInfo listAuditContract(CrmContract crmContract)
    {
        startPage();
        crmContract.setContractStatus("2");//已审核
        List<CrmContract> list = crmContractService.selectCrmContractList(crmContract);
        return getDataTable(list);
    }
    
    /**
     * 合同管理 检测付款金额是否合理 
     */
    @GetMapping("/checkPaymentMoney")
    @ResponseBody
    public Boolean checkPaymentMoney(String contractId,String payMoney)
    {
    	CrmContract crmContract = crmContractService.selectCrmContractById(Convert.toLong(contractId)); 
    	Double totalMoney=crmContract.getTotalMoney();	
    	return (totalMoney-Convert.toDouble(payMoney))>=0?true:false;
    }
    
    /**
     * 合同管理 检测发票金额是否合理
     */
    @GetMapping("/checkInvoiceMoney")
    @ResponseBody
    public Boolean checkInvoiceMoney(String contractId,String invoiceMoney)
    {
    	CrmContract crmContract = crmContractService.selectCrmContractById(Convert.toLong(contractId)); 
    	Double totalMoney=crmContract.getTotalMoney();
    	return (totalMoney-Convert.toDouble(invoiceMoney))>=0?true:false;
    }
}
