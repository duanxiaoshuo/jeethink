package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.CrmComplaint;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.service.crm1.ICrmComplaintService;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm2.ICrm2ComplaintService;
import com.jeethink.crm.service.crm2.ICrm2CustomerService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户投诉Controller
 * 
 * @author jeethink
 * @date 2020-03-09
 */
@Controller
@RequestMapping("/crm2/complaint")
public class Crm2ComplaintController extends BaseController
{
    private String prefix = "crm2/complaint";

    @Autowired
    private ICrm2ComplaintService crmComplaintService;
    
    @Autowired
    private ICrm2CustomerService crmCustomerService;

    @RequiresPermissions("crm:complaint:view")
    @GetMapping()
    public String complaint()
    {
        return prefix + "/complaint";
    }

    /**
     * 查询客户投诉列表
     */
    @RequiresPermissions("crm:complaint:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmComplaint crmComplaint)
    {
        startPage();
        List<CrmComplaint> list = crmComplaintService.selectCrmComplaintList(crmComplaint);
        return getDataTable(list);
    }

    /**
     * 导出客户投诉列表
     */
    @RequiresPermissions("crm:complaint:export")
    @Log(title = "客户投诉", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmComplaint crmComplaint)
    {
        List<CrmComplaint> list = crmComplaintService.selectCrmComplaintList(crmComplaint);
        ExcelUtil<CrmComplaint> util = new ExcelUtil<CrmComplaint>(CrmComplaint.class);
        return util.exportExcel(list, "complaint");
    }
    
    /**
     * 新增客户投诉
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户投诉
     */
    @RequiresPermissions("crm:complaint:add")
    @Log(title = "客户投诉", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmComplaint crmComplaint)
    {
    	crmComplaint.setDelFlag("0");
    	crmComplaint.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(crmComplaintService.insertCrmComplaint(crmComplaint));
    }

    /**
     * 修改客户投诉
     */
    @GetMapping("/edit/{complaintId}")
    public String edit(@PathVariable("complaintId") Long complaintId, ModelMap mmap)
    {
        CrmComplaint crmComplaint = crmComplaintService.selectCrmComplaintById(complaintId);
        mmap.put("crmComplaint", crmComplaint);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户投诉
     */
    @RequiresPermissions("crm:complaint:edit")
    @Log(title = "客户投诉", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmComplaint crmComplaint)
    {
    	crmComplaint.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmComplaintService.updateCrmComplaint(crmComplaint));
    }

    /**
     * 删除客户投诉
     */
    @RequiresPermissions("crm:complaint:remove")
    @Log(title = "客户投诉", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmComplaintService.deleteCrmComplaintByIds(ids));
    }
    
    /**
     * 新增客户投诉
     * 如果来自于客户详情新增，则有customerId
     */
    @GetMapping("/addComplaint/{customerId}")
    public String addComplaint(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
    	CrmCustomer crmCustomer = crmCustomerService.selectCrmCustomerById(customerId);
        mmap.put("crmCustomer", crmCustomer);
        return prefix + "/addComplaint";
    }
}
