package com.jeethink.web.controller.wms;

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
import com.jeethink.crm.domain.WmsSupplier;
import com.jeethink.crm.service.wms.IWmsSupplierService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 产品供应商Controller
 * 
 * @author jeethink
 * @date 2020-03-12
 */
@Controller
@RequestMapping("/crm/supplier")
public class WmsSupplierController extends BaseController
{
    private String prefix = "crm/supplier";

    @Autowired
    private IWmsSupplierService wmsSupplierService;

    @RequiresPermissions("crm:supplier:view")
    @GetMapping()
    public String supplier()
    {
        return prefix + "/supplier";
    }

    /**
     * 查询产品供应商列表
     */
    @RequiresPermissions("crm:supplier:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WmsSupplier wmsSupplier)
    {
        startPage();
        List<WmsSupplier> list = wmsSupplierService.selectWmsSupplierList(wmsSupplier);
        return getDataTable(list);
    }

    /**
     * 导出产品供应商列表
     */
    @RequiresPermissions("crm:supplier:export")
    @Log(title = "产品供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WmsSupplier wmsSupplier)
    {
        List<WmsSupplier> list = wmsSupplierService.selectWmsSupplierList(wmsSupplier);
        ExcelUtil<WmsSupplier> util = new ExcelUtil<WmsSupplier>(WmsSupplier.class);
        return util.exportExcel(list, "supplier");
    }

    /**
     * 新增产品供应商
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品供应商
     */
    @RequiresPermissions("crm:supplier:add")
    @Log(title = "产品供应商", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WmsSupplier wmsSupplier)
    {
    	wmsSupplier.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(wmsSupplierService.insertWmsSupplier(wmsSupplier));
    }

    /**
     * 修改产品供应商
     */
    @GetMapping("/edit/{supplierIid}")
    public String edit(@PathVariable("supplierIid") Long supplierIid, ModelMap mmap)
    {
        WmsSupplier wmsSupplier = wmsSupplierService.selectWmsSupplierById(supplierIid);
        mmap.put("wmsSupplier", wmsSupplier);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品供应商
     */
    @RequiresPermissions("crm:supplier:edit")
    @Log(title = "产品供应商", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WmsSupplier wmsSupplier)
    {
    	wmsSupplier.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsSupplierService.updateWmsSupplier(wmsSupplier));
    }

    /**
     * 删除产品供应商
     */
    @RequiresPermissions("crm:supplier:remove")
    @Log(title = "产品供应商", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wmsSupplierService.deleteWmsSupplierByIds(ids));
    }
    
    /**
     * 查询选择供应商
     */
    @GetMapping("/selectSupplier")
    public String selectSupplier()
    {
        return prefix + "/selectSupplier";
    }    
}
