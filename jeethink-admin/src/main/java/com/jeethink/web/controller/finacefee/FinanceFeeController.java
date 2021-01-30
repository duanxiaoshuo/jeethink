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
import com.jeethink.crm.domain.FinanceFee;
import com.jeethink.crm.service.finace.IFinanceFeeService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 费用收支Controller
 * 
 * @author jeethink
 * @date 2020-04-15
 */
@Controller
@RequestMapping("/crm/fee")
public class FinanceFeeController extends BaseController
{
    private String prefix = "crm/fee";

    @Autowired
    private IFinanceFeeService financeFeeService;

    @RequiresPermissions("crm:fee:view")
    @GetMapping()
    public String fee()
    {
        return prefix + "/fee";
    }

    /**
     * 查询费用收支列表
     */
    @RequiresPermissions("crm:fee:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FinanceFee financeFee)
    {
        startPage();
        List<FinanceFee> list = financeFeeService.selectFinanceFeeList(financeFee);
        return getDataTable(list);
    }

    /**
     * 导出费用收支列表
     */
    @RequiresPermissions("crm:fee:export")
    @Log(title = "费用收支", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FinanceFee financeFee)
    {
        List<FinanceFee> list = financeFeeService.selectFinanceFeeList(financeFee);
        ExcelUtil<FinanceFee> util = new ExcelUtil<FinanceFee>(FinanceFee.class);
        return util.exportExcel(list, "fee");
    }

    /**
     * 新增费用收支
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存费用收支
     */
    @RequiresPermissions("crm:fee:add")
    @Log(title = "费用收支", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FinanceFee financeFee)
    {
    	financeFee.setDelFlag("0");
    	financeFee.setFeeStatus("0");//已保存
    	financeFee.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(financeFeeService.insertFinanceFee(financeFee));
    }

    /**
     * 修改费用收支
     */
    @GetMapping("/edit/{feeId}")
    public String edit(@PathVariable("feeId") Long feeId, ModelMap mmap)
    {
        FinanceFee financeFee = financeFeeService.selectFinanceFeeById(feeId);
        mmap.put("financeFee", financeFee);
        return prefix + "/edit";
    }

    /**
     * 修改保存费用收支
     */
    @RequiresPermissions("crm:fee:edit")
    @Log(title = "费用收支", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FinanceFee financeFee)
    {
    	financeFee.setFeeStatus("0");//已保存
    	financeFee.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(financeFeeService.updateFinanceFee(financeFee));
    }

    /**
     * 删除费用收支
     */
    @RequiresPermissions("crm:fee:remove")
    @Log(title = "费用收支", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(financeFeeService.deleteFinanceFeeByIds(ids));
    }
}
