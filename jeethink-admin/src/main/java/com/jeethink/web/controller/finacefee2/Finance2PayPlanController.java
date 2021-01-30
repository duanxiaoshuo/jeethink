package com.jeethink.web.controller.finacefee2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.FinancePayPlan;
import com.jeethink.crm.service.finace2.IFinance2PayPlanService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回款计划Controller
 * 
 * @author jeethink
 * @date 2020-04-13
 */
@Controller
@RequestMapping("/crm2/payPlan")
public class Finance2PayPlanController extends BaseController
{
    private String prefix = "crm2/payPlan";

    @Autowired
    private IFinance2PayPlanService finance2PayPlanService;

    @RequiresPermissions("crm:payPlan:view")
    @GetMapping()
    public String payPlan()
    {
        return prefix + "/payPlan";
    }

    /**
     * 查询回款计划列表
     */
    @RequiresPermissions("crm:payPlan:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinancePayPlan financePayPlan)
    {
        startPage();
        List<FinancePayPlan> list = finance2PayPlanService.selectFinancePayPlanList(financePayPlan);
        return getDataTable(list);
    }

    /**
     * 导出回款计划列表
     */
    @RequiresPermissions("crm:payPlan:export")
    @Log(title = "回款计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinancePayPlan financePayPlan)
    {
        List<FinancePayPlan> list = finance2PayPlanService.selectFinancePayPlanList(financePayPlan);
        ExcelUtil<FinancePayPlan> util = new ExcelUtil<FinancePayPlan>(FinancePayPlan.class);
        return util.exportExcel(list, "payPlan");
    }

    /**
     * 新增回款计划
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存回款计划
     */
    @RequiresPermissions("crm:payPlan:add")
    @Log(title = "回款计划", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinancePayPlan financePayPlan)
    {
    	String loginName=ShiroUtils.getLoginName();
    	financePayPlan.setDelFlag("0");
    	financePayPlan.setPlanStatus("0");
    	financePayPlan.setCreateBy(loginName);
    	financePayPlan.setSourceBelongTo(loginName);
    	financePayPlan.setBelongTo(loginName);
        return toAjax(finance2PayPlanService.insertFinancePayPlan(financePayPlan));
    }

    /**
     * 修改回款计划
     */
    @GetMapping("/edit/{planId}")
    public String edit(@PathVariable("planId") Long planId, ModelMap mmap)
    {
        FinancePayPlan financePayPlan = finance2PayPlanService.selectFinancePayPlanById(planId);
        mmap.put("financePayPlan", financePayPlan);
        return prefix + "/edit";
    }

    /**
     * 修改保存回款计划
     */
    @RequiresPermissions("crm:payPlan:edit")
    @Log(title = "回款计划", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinancePayPlan financePayPlan)
    {
    	financePayPlan.setPlanStatus("0");
    	financePayPlan.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(finance2PayPlanService.updateFinancePayPlan(financePayPlan));
    }

    /**
     * 删除回款计划
     */
    @RequiresPermissions("crm:payPlan:remove")
    @Log(title = "回款计划", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(finance2PayPlanService.deleteFinancePayPlanByIds(ids));
    }
}
