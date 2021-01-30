package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.CrmContractInvoice;
import com.jeethink.crm.service.crm1.ICrmContractInvoiceService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 合同发票管理Controller
 * 
 * @author jeethink
 * @date 2020-03-25
 */
@Controller
@RequestMapping("/crm2/contractInvoice")
public class Crm2ContractInvoiceController extends BaseController
{
    private String prefix = "crm2/contractInvoice";

    @Autowired
    private ICrmContractInvoiceService crmContractInvoiceService;

    @RequiresPermissions("crm:contractInvoice:view")
    @GetMapping()
    public String contractInvoice()
    {
        return prefix + "/contractInvoice";
    }

    /**
     * 查询合同发票管理列表
     */
    @RequiresPermissions("crm:contractInvoice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmContractInvoice crmContractInvoice)
    {
        startPage();
        List<CrmContractInvoice> list = crmContractInvoiceService.selectCrmContractInvoiceList(crmContractInvoice);
        return getDataTable(list);
    }

    /**
     * 导出合同发票管理列表
     */
    @RequiresPermissions("crm:contractInvoice:export")
    @Log(title = "合同发票管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmContractInvoice crmContractInvoice)
    {
        List<CrmContractInvoice> list = crmContractInvoiceService.selectCrmContractInvoiceList(crmContractInvoice);
        ExcelUtil<CrmContractInvoice> util = new ExcelUtil<CrmContractInvoice>(CrmContractInvoice.class);
        return util.exportExcel(list, "contractInvoice");
    }

    /**
     * 新增合同发票管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合同发票管理
     */
    @RequiresPermissions("crm:contractInvoice:add")
    @Log(title = "合同发票管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmContractInvoice crmContractInvoice)
    {
    	crmContractInvoice.setInvoiceStatus("0");//已保存
    	crmContractInvoice.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractInvoiceService.insertCrmContractInvoice(crmContractInvoice));
    }

    /**
     * 修改合同发票管理
     */
    @GetMapping("/edit/{invoiceId}")
    public String edit(@PathVariable("invoiceId") Long invoiceId, ModelMap mmap)
    {
        CrmContractInvoice crmContractInvoice = crmContractInvoiceService.selectCrmContractInvoiceById(invoiceId);
        mmap.put("crmContractInvoice", crmContractInvoice);
        return prefix + "/edit";
    }

    /**
     * 修改保存合同发票管理
     */
    @RequiresPermissions("crm:contractInvoice:edit")
    @Log(title = "合同发票管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmContractInvoice crmContractInvoice)
    {
    	crmContractInvoice.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractInvoiceService.updateCrmContractInvoice(crmContractInvoice));
    }

    /**
     * 删除合同发票管理
     */
    @RequiresPermissions("crm:contractInvoice:remove")
    @Log(title = "合同发票管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmContractInvoiceService.deleteCrmContractInvoiceByIds(ids));
    }
    
    /**
     * 合同发票管理 提交
     */
    @RequiresPermissions("crm:contractInvoice:submit")
    @Log(title = "合同发票管理", businessType = BusinessType.UPDATE)
    @PostMapping("/submit")
    @ResponseBody
    public AjaxResult submitSave(CrmContractInvoice crmContractInvoice)
    {
    	crmContractInvoice.setInvoiceStatus("1");//已提交
    	crmContractInvoice.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractInvoiceService.updateCrmContractInvoice(crmContractInvoice));
    }
    
    /**
     * 合同发票管理  审核
     */
    @RequiresPermissions("crm:contractPayment:audit")
    @Log(title = "合同发票管理", businessType = BusinessType.UPDATE)
    @PostMapping("/auditOk/{invoiceId}")
    @ResponseBody
    public AjaxResult auditOkSave(@PathVariable("invoiceId") Long invoiceId)
    {
    	CrmContractInvoice crmContractInvoice = crmContractInvoiceService.selectCrmContractInvoiceById(invoiceId);    	
    	crmContractInvoice.setInvoiceStatus("2");//已审核
    	crmContractInvoice.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractInvoiceService.updateCrmContractInvoice(crmContractInvoice));
    }
    
    /**
     * 合同发票管理  审核
     */
    @RequiresPermissions("crm:contractInvoice:audit")
    @Log(title = "合同发票管理", businessType = BusinessType.UPDATE)
    @PostMapping("/auditNo/{invoiceId}")
    @ResponseBody
    public AjaxResult auditNoSave(@PathVariable("invoiceId") Long invoiceId)
    {
    	CrmContractInvoice crmContractInvoice = crmContractInvoiceService.selectCrmContractInvoiceById(invoiceId);    	
    	crmContractInvoice.setInvoiceStatus("3");//已驳回    	
    	crmContractInvoice.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractInvoiceService.updateCrmContractInvoice(crmContractInvoice));
    }
    
    /**
     * 合同发票管理  开发票
     */
    @RequiresPermissions("crm:contractInvoice:invoice")
    @Log(title = "合同发票管理", businessType = BusinessType.UPDATE)
    @PostMapping("/invoice/{invoiceId}")
    @ResponseBody
    public AjaxResult invoice(@PathVariable("invoiceId") Long invoiceId)
    {
    	CrmContractInvoice crmContractInvoice = crmContractInvoiceService.selectCrmContractInvoiceById(invoiceId);    	
    	crmContractInvoice.setInvoiceStatus("4");//已开票  	
    	crmContractInvoice.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractInvoiceService.updateCrmContractInvoice(crmContractInvoice));
    }
}
