package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.Crm2Customer;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmVisit;
import com.jeethink.crm.service.crm2.ICrm2CustomerService;
import com.jeethink.crm.service.crm2.ICrm2VisitService;
import com.jeethink.crm.service.crm2.ICrm2VisitService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户拜访Controller
 * 
 * @author jeethink
 * @date 2020-03-10
 */
@Controller
@RequestMapping("/crm2/visit")
public class Crm2VisitController extends BaseController
{
    private String prefix = "crm2/visit";

    @Autowired
    private ICrm2VisitService crm2VisitService;
    
    @Autowired
    private ICrm2CustomerService crm2CustomerService;

    @RequiresPermissions("crm:visit:view")
    @GetMapping()
    public String visit()
    {
        return prefix + "/visit";
    }

    /**
     * 查询客户拜访列表
     */
    @RequiresPermissions("crm:visit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmVisit crmVisit)
    {
        startPage();
        List<CrmVisit> list = crm2VisitService.selectCrmVisitList(crmVisit);
        return getDataTable(list);
    }

    /**
     * 导出客户拜访列表
     */
    @RequiresPermissions("crm:visit:export")
    @Log(title = "客户拜访", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmVisit crmVisit)
    {
        List<CrmVisit> list = crm2VisitService.selectCrmVisitList(crmVisit);
        ExcelUtil<CrmVisit> util = new ExcelUtil<CrmVisit>(CrmVisit.class);
        return util.exportExcel(list, "visit");
    }

    /**
     * 新增客户拜访
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
    
    /**
     * 新增客户拜访
     * 如果来自于客户详情新增，则有customerId
     */
    @GetMapping("/addVisit/{customerId}")
    public String addVisit(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
    	Crm2Customer crmCustomer = crm2CustomerService.selectCrmCustomerById(customerId);
        mmap.put("crmCustomer", crmCustomer);
        return prefix + "/addVisit";
    }

    /**
     * 新增保存客户拜访
     */
    @RequiresPermissions("crm:visit:add")
    @Log(title = "客户拜访", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmVisit crmVisit)
    {
    	crmVisit.setDelFlag("0");
    	crmVisit.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(crm2VisitService.insertCrmVisit(crmVisit));
    }

    /**
     * 修改客户拜访
     */
    @GetMapping("/edit/{visitId}")
    public String edit(@PathVariable("visitId") Long visitId, ModelMap mmap)
    {
        CrmVisit crmVisit = crm2VisitService.selectCrmVisitById(visitId);
        mmap.put("crmVisit", crmVisit);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户拜访
     */
    @RequiresPermissions("crm:visit:edit")
    @Log(title = "客户拜访", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmVisit crmVisit)
    {
    	crmVisit.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crm2VisitService.updateCrmVisit(crmVisit));
    }

    /**
     * 删除客户拜访
     */
    @RequiresPermissions("crm:visit:remove")
    @Log(title = "客户拜访", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crm2VisitService.deleteCrmVisitByIds(ids));
    }
}
