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
import com.jeethink.crm.domain.WmsStockAllocation;
import com.jeethink.crm.service.wms.IWmsStockAllocationService;
import com.jeethink.crm.service.wms.IWmsStockService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 调拨单Controller
 * 
 * @author jeethink
 * @date 2020-08-12
 */
@Controller
@RequestMapping("/crm/stockAllocation")
public class WmsStockAllocationController extends BaseController
{
    private String prefix = "crm/stockAllocation";

    @Autowired
    private IWmsStockAllocationService wmsStockAllocationService;
    
    @Autowired
    private IWmsStockService wmsStockService;

    @RequiresPermissions("crm:stockAllocation:view")
    @GetMapping()
    public String stockAllocation()
    {
        return prefix + "/stockAllocation";
    }

    /**
     * 查询调拨单列表
     */
    @RequiresPermissions("crm:stockAllocation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WmsStockAllocation wmsStockAllocation)
    {
        startPage();
        List<WmsStockAllocation> list = wmsStockAllocationService.selectWmsStockAllocationList(wmsStockAllocation);
        return getDataTable(list);
    }

    /**
     * 导出调拨单列表
     */
    @RequiresPermissions("crm:stockAllocation:export")
    @Log(title = "调拨单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WmsStockAllocation wmsStockAllocation)
    {
        List<WmsStockAllocation> list = wmsStockAllocationService.selectWmsStockAllocationList(wmsStockAllocation);
        ExcelUtil<WmsStockAllocation> util = new ExcelUtil<WmsStockAllocation>(WmsStockAllocation.class);
        return util.exportExcel(list, "stockAllocation");
    }

    /**
     * 新增调拨单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存调拨单
     */
    @RequiresPermissions("crm:stockAllocation:add")
    @Log(title = "调拨单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WmsStockAllocation wmsStockAllocation)
    {
    	wmsStockAllocation.setAllocationStatus("0");
    	wmsStockAllocation.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockAllocationService.insertWmsStockAllocation(wmsStockAllocation));
    }

    /**
     * 修改调拨单
     */
    @GetMapping("/edit/{allocationId}")
    public String edit(@PathVariable("allocationId") Long allocationId, ModelMap mmap)
    {
        WmsStockAllocation wmsStockAllocation = wmsStockAllocationService.selectWmsStockAllocationById(allocationId);
        mmap.put("wmsStockAllocation", wmsStockAllocation);
        return prefix + "/edit";
    }

    /**
     * 修改保存调拨单
     */
    @RequiresPermissions("crm:stockAllocation:edit")
    @Log(title = "调拨单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WmsStockAllocation wmsStockAllocation)
    {
    	wmsStockAllocation.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockAllocationService.updateWmsStockAllocation(wmsStockAllocation));
    }

    /**
     * 调拨单 详情
     */
    @GetMapping("/detail/{allocationId}")
    public String detail(@PathVariable("allocationId") Long allocationId, ModelMap mmap)
    {
    	WmsStockAllocation wmsStockAllocation = wmsStockAllocationService.selectWmsStockAllocationById(allocationId);
        mmap.put("wmsStockAllocation", wmsStockAllocation);
        return prefix + "/detail";
    }

    /**
     * 删除调拨单
     */
    @RequiresPermissions("crm:stockAllocation:remove")
    @Log(title = "调拨单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wmsStockAllocationService.deleteWmsStockAllocationByIds(ids));
    }

    /**
     * 提交保存调拨单
     */
    @RequiresPermissions("crm:stockAllocation:submit")
    @Log(title = "调拨单提交", businessType = BusinessType.UPDATE)
    @PostMapping("/submit")
    @ResponseBody
    public AjaxResult submit(WmsStockAllocation wmsStockAllocation)
    {   	    	
    	wmsStockAllocation.setAllocationStatus("1");
    	wmsStockAllocation.setUpdateBy(ShiroUtils.getLoginName());   	    	
        return toAjax(wmsStockAllocationService.updateWmsStockAllocation(wmsStockAllocation));
    }

    /**
     * 调拨单-审核通过
     */
    @RequiresPermissions("crm:stockAllocation:audit")
    @Log(title = "调拨单审核", businessType = BusinessType.UPDATE)
    @PostMapping("/auditOk/{allocationId}")
    @ResponseBody
    public AjaxResult auditOk(@PathVariable("allocationId") Long allocationId)
    {   	    	
        WmsStockAllocation wmsStockAllocation = wmsStockAllocationService.selectWmsStockAllocationById(allocationId);
    	wmsStockAllocation.setUpdateBy(ShiroUtils.getLoginName());   	    	
        return toAjax(wmsStockAllocationService.auditOkWmsStockAllocation(wmsStockAllocation));
    }

    /**
     * 调拨单-审核拒绝
     */
    @RequiresPermissions("crm:stockAllocation:audit")
    @Log(title = "调拨单审核", businessType = BusinessType.UPDATE)
    @PostMapping("/auditNo/{allocationId}")
    @ResponseBody
    public AjaxResult auditNo(@PathVariable("allocationId") Long allocationId)
    {   	    	
        WmsStockAllocation wmsStockAllocation = wmsStockAllocationService.selectWmsStockAllocationById(allocationId);
    	wmsStockAllocation.setUpdateBy(ShiroUtils.getLoginName());   	    	
        return toAjax(wmsStockAllocationService.auditNoWmsStockAllocation(wmsStockAllocation));
    }
}
