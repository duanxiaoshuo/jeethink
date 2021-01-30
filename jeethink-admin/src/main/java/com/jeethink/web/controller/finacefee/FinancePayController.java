package com.jeethink.web.controller.finacefee;

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
import com.jeethink.crm.domain.FinancePay;
import com.jeethink.crm.service.finace.IFinancePayService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 回款管理Controller
 * 
 * @author jeethink
 * @date 2020-04-12
 */
@Controller
@RequestMapping("/crm/pay")
public class FinancePayController extends BaseController
{
    private String prefix = "crm/pay";

    @Autowired
    private IFinancePayService financePayService;

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
        List<FinancePay> list = financePayService.selectFinancePayList(financePay);
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
        List<FinancePay> list = financePayService.selectFinancePayList(financePay);
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
        return toAjax(financePayService.insertFinancePay(financePay));
    }

    /**
     * 修改回款管理
     */
    @GetMapping("/edit/{payId}")
    public String edit(@PathVariable("payId") Long payId, ModelMap mmap)
    {
        FinancePay financePay = financePayService.selectFinancePayById(payId);
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
        return toAjax(financePayService.updateFinancePay(financePay));
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
        return toAjax(financePayService.deleteFinancePayByIds(ids));
    }
}
