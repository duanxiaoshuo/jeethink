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
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmVisit;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm1.ICrmVisitService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 客户拜访Controller
 * 
 * @author jeethink
 * @date 2020-03-10
 */
@Controller
@RequestMapping("/crm/visit")
public class CrmVisitController extends BaseController
{
    private String prefix = "crm/visit";

    @Autowired
    private ICrmVisitService crmVisitService;
    
    @Autowired
    private ICrmCustomerService crmCustomerService;

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
        List<CrmVisit> list = crmVisitService.selectCrmVisitList(crmVisit);
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
        List<CrmVisit> list = crmVisitService.selectCrmVisitList(crmVisit);
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
    	CrmCustomer crmCustomer = crmCustomerService.selectCrmCustomerById(customerId);
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
        return toAjax(crmVisitService.insertCrmVisit(crmVisit));
    }

    /**
     * 修改客户拜访
     */
    @GetMapping("/edit/{visitId}")
    public String edit(@PathVariable("visitId") Long visitId, ModelMap mmap)
    {
        CrmVisit crmVisit = crmVisitService.selectCrmVisitById(visitId);
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
        return toAjax(crmVisitService.updateCrmVisit(crmVisit));
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
        return toAjax(crmVisitService.deleteCrmVisitByIds(ids));
    }
}
