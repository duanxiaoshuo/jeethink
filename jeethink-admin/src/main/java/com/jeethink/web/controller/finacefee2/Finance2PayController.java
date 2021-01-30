package com.jeethink.web.controller.finacefee2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.FinancePay;
import com.jeethink.crm.service.finace2.IFinance2PayService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回款管理Controller
 * 
 * @author jeethink
 * @date 2020-04-12
 */
@Controller
@RequestMapping("/crm2/pay")
public class Finance2PayController extends BaseController
{
    private String prefix = "crm2/pay";

    @Autowired
    private IFinance2PayService finance2PayService;

    @RequiresPermissions("crm:pay:view")
    @GetMapping()
    public String pay()
    {
        return prefix + "/pay";
    }

    /**
     * 查询回款管理列表
     */
    @RequiresPermissions("crm:pay:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinancePay financePay)
    {
        startPage();
        List<FinancePay> list = finance2PayService.selectFinancePayList(financePay);
        return getDataTable(list);
    }

    /**
     * 导出回款管理列表
     */
    @RequiresPermissions("crm:pay:export")
    @Log(title = "回款管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinancePay financePay)
    {
        List<FinancePay> list = finance2PayService.selectFinancePayList(financePay);
        ExcelUtil<FinancePay> util = new ExcelUtil<FinancePay>(FinancePay.class);
        return util.exportExcel(list, "pay");
    }

    /**
     * 新增回款管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存回款管理
     */
    @RequiresPermissions("crm:pay:add")
    @Log(title = "回款管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinancePay financePay)
    {
    	String loginName=ShiroUtils.getLoginName();
    	financePay.setDelFlag("0");
    	financePay.setPayStatus("0");
    	financePay.setCreateBy(loginName);
    	financePay.setSourceBelongTo(loginName);
    	financePay.setBelongTo(loginName);
        return toAjax(finance2PayService.insertFinancePay(financePay));
    }

    /**
     * 修改回款管理
     */
    @GetMapping("/edit/{payId}")
    public String edit(@PathVariable("payId") Long payId, ModelMap mmap)
    {
        FinancePay financePay = finance2PayService.selectFinancePayById(payId);
        mmap.put("financePay", financePay);
        return prefix + "/edit";
    }

    /**
     * 修改保存回款管理
     */
    @RequiresPermissions("crm:pay:edit")
    @Log(title = "回款管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinancePay financePay)
    {
    	financePay.setPayStatus("0");
    	financePay.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(finance2PayService.updateFinancePay(financePay));
    }

    /**
     * 删除回款管理
     */
    @RequiresPermissions("crm:pay:remove")
    @Log(title = "回款管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(finance2PayService.deleteFinancePayByIds(ids));
    }
}
