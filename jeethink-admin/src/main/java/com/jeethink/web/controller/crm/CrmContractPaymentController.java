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
import com.jeethink.crm.domain.CrmContractPayment;
import com.jeethink.crm.service.crm1.ICrmContractPaymentService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 合同付款管理Controller
 * 
 * @author jeethink
 * @date 2020-03-22
 */
@Controller
@RequestMapping("/crm/contractPayment")
public class CrmContractPaymentController extends BaseController
{
    private String prefix = "crm/contractPayment";

    @Autowired
    private ICrmContractPaymentService crmContractPaymentService;

    @RequiresPermissions("crm:contractPayment:view")
    @GetMapping()
    public String contractPayment()
    {
        return prefix + "/contractPayment";
    }

    /**
     * 查询合同付款管理列表
     */
    @RequiresPermissions("crm:contractPayment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmContractPayment crmContractPayment)
    {
        startPage();
        List<CrmContractPayment> list = crmContractPaymentService.selectCrmContractPaymentList(crmContractPayment);
        return getDataTable(list);
    }

    /**
     * 导出合同付款管理列表
     */
    @RequiresPermissions("crm:contractPayment:export")
    @Log(title = "合同付款管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmContractPayment crmContractPayment)
    {
        List<CrmContractPayment> list = crmContractPaymentService.selectCrmContractPaymentList(crmContractPayment);
        ExcelUtil<CrmContractPayment> util = new ExcelUtil<CrmContractPayment>(CrmContractPayment.class);
        return util.exportExcel(list, "contractPayment");
    }

    /**
     * 新增合同付款管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合同付款管理
     */
    @RequiresPermissions("crm:contractPayment:add")
    @Log(title = "合同付款管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmContractPayment crmContractPayment)
    {
    	crmContractPayment.setPayStatus("0");//已保存
    	crmContractPayment.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractPaymentService.insertCrmContractPayment(crmContractPayment));
    }

    /**
     * 修改合同付款管理
     */
    @GetMapping("/edit/{paymentId}")
    public String edit(@PathVariable("paymentId") Long paymentId, ModelMap mmap)
    {
        CrmContractPayment crmContractPayment = crmContractPaymentService.selectCrmContractPaymentById(paymentId);
        mmap.put("crmContractPayment", crmContractPayment);
        return prefix + "/edit";
    }

    /**
     * 修改保存合同付款管理
     */
    @RequiresPermissions("crm:contractPayment:edit")
    @Log(title = "合同付款管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmContractPayment crmContractPayment)
    {
    	crmContractPayment.setDelFlag("0");
    	crmContractPayment.setPayStatus("0");//已保存
    	crmContractPayment.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractPaymentService.updateCrmContractPayment(crmContractPayment));
    }

    /**
     * 删除合同付款管理
     */
    @RequiresPermissions("crm:contractPayment:remove")
    @Log(title = "合同付款管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmContractPaymentService.deleteCrmContractPaymentByIds(ids));
    }
    
    /**
     * 合同付款管理 提交
     */
    @RequiresPermissions("crm:contractPayment:submit")
    @Log(title = "合同付款管理", businessType = BusinessType.UPDATE)
    @PostMapping("/submit")
    @ResponseBody
    public AjaxResult submitSave(CrmContractPayment crmContractPayment)
    {
    	crmContractPayment.setPayStatus("1");//已提交
    	crmContractPayment.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractPaymentService.updateCrmContractPayment(crmContractPayment));
    }
    
    /**
     * 合同付款管理  审核
     */
    @RequiresPermissions("crm:contractPayment:audit")
    @Log(title = "合同付款管理", businessType = BusinessType.UPDATE)
    @PostMapping("/auditOk/{paymentId}")
    @ResponseBody
    public AjaxResult auditOkSave(@PathVariable("paymentId") Long paymentId)
    {
    	CrmContractPayment crmContractPayment = crmContractPaymentService.selectCrmContractPaymentById(paymentId);    	
    	crmContractPayment.setPayStatus("2");//已审核
    	crmContractPayment.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractPaymentService.auditCrmContractPayment(crmContractPayment));
    }
    
    /**
     * 合同付款管理  审核
     */
    @RequiresPermissions("crm:contractPayment:audit")
    @Log(title = "合同付款管理", businessType = BusinessType.UPDATE)
    @PostMapping("/auditNo/{paymentId}")
    @ResponseBody
    public AjaxResult auditNoSave(@PathVariable("paymentId") Long paymentId)
    {
    	CrmContractPayment crmContractPayment = crmContractPaymentService.selectCrmContractPaymentById(paymentId);    	
    	crmContractPayment.setPayStatus("3");//已驳回    	
    	crmContractPayment.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractPaymentService.updateCrmContractPayment(crmContractPayment));
    }
}
