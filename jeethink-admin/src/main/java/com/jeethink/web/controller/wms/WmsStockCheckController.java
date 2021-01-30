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
import com.jeethink.crm.domain.WmsStockCheck;
import com.jeethink.crm.service.wms.IWmsStockCheckService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 库存盘点Controller
 * 
 * @author jeethink
 * @date 2020-08-14
 */
@Controller
@RequestMapping("/crm/stockCheck")
public class WmsStockCheckController extends BaseController
{
    private String prefix = "crm/stockCheck";

    @Autowired
    private IWmsStockCheckService wmsStockCheckService;

    @RequiresPermissions("crm:stockCheck:view")
    @GetMapping()
    public String stockCheck()
    {
        return prefix + "/stockCheck";
    }

    /**
     * 查询库存盘点列表
     */
    @RequiresPermissions("crm:stockCheck:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WmsStockCheck wmsStockCheck)
    {
        startPage();
        List<WmsStockCheck> list = wmsStockCheckService.selectWmsStockCheckList(wmsStockCheck);
        return getDataTable(list);
    }

    /**
     * 导出库存盘点列表
     */
    @RequiresPermissions("crm:stockCheck:export")
    @Log(title = "库存盘点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WmsStockCheck wmsStockCheck)
    {
        List<WmsStockCheck> list = wmsStockCheckService.selectWmsStockCheckList(wmsStockCheck);
        ExcelUtil<WmsStockCheck> util = new ExcelUtil<WmsStockCheck>(WmsStockCheck.class);
        return util.exportExcel(list, "stockCheck");
    }

    /**
     * 新增库存盘点
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存库存盘点
     */
    @RequiresPermissions("crm:stockCheck:add")
    @Log(title = "库存盘点", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WmsStockCheck wmsStockCheck)
    {
    	wmsStockCheck.setCheckStatus("0");//已保存
    	wmsStockCheck.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockCheckService.insertWmsStockCheck(wmsStockCheck));
    }

    /**
     * 修改库存盘点
     */
    @GetMapping("/edit/{checkId}")
    public String edit(@PathVariable("checkId") Long checkId, ModelMap mmap)
    {
        WmsStockCheck wmsStockCheck = wmsStockCheckService.selectWmsStockCheckById(checkId);
        mmap.put("wmsStockCheck", wmsStockCheck);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存盘点
     */
    @RequiresPermissions("crm:stockCheck:edit")
    @Log(title = "库存盘点", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WmsStockCheck wmsStockCheck)
    {
    	wmsStockCheck.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockCheckService.updateWmsStockCheck(wmsStockCheck));
    }

    /**
     * 库存盘点 详情
     */
    @GetMapping("/detail/{checkId}")
    public String detail(@PathVariable("checkId") Long checkId, ModelMap mmap)
    {
    	WmsStockCheck wmsStockCheck = wmsStockCheckService.selectWmsStockCheckById(checkId);
        mmap.put("wmsStockCheck", wmsStockCheck);
        return prefix + "/detail";
    }

    /**
     * 删除库存盘点
     */
    @RequiresPermissions("crm:stockCheck:remove")
    @Log(title = "库存盘点", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wmsStockCheckService.deleteWmsStockCheckByIds(ids));
    }

    /**
     * 提交保存库存盘点
     */
    @RequiresPermissions("crm:stockCheck:submit")
    @Log(title = "库存盘点提交", businessType = BusinessType.UPDATE)
    @PostMapping("/submit")
    @ResponseBody
    public AjaxResult submit(WmsStockCheck wmsStockCheck)
    {   	    	
    	wmsStockCheck.setCheckStatus("1");//已提交
    	wmsStockCheck.setUpdateBy(ShiroUtils.getLoginName());   	    	
        return toAjax(wmsStockCheckService.updateWmsStockCheck(wmsStockCheck));
    }

    /**
     * 库存盘点-审核通过
     */
    @RequiresPermissions("crm:stockCheck:audit")
    @Log(title = "库存盘点审核", businessType = BusinessType.UPDATE)
    @PostMapping("/auditOk/{checkId}")
    @ResponseBody
    public AjaxResult auditOk(@PathVariable("checkId") Long checkId)
    {   	    	
    	WmsStockCheck wmsStockCheck = wmsStockCheckService.selectWmsStockCheckById(checkId);
    	wmsStockCheck.setUpdateBy(ShiroUtils.getLoginName());   	    	
        return toAjax(wmsStockCheckService.auditOkWmsStockCheck(wmsStockCheck));
    }

    /**
     * 库存盘点-审核拒绝
     */
    @RequiresPermissions("crm:stockCheck:audit")
    @Log(title = "库存盘点审核", businessType = BusinessType.UPDATE)
    @PostMapping("/auditNo/{checkId}")
    @ResponseBody
    public AjaxResult auditNo(@PathVariable("checkId") Long checkId)
    {   	    	
    	WmsStockCheck wmsStockCheck = wmsStockCheckService.selectWmsStockCheckById(checkId);
        wmsStockCheck.setUpdateBy(ShiroUtils.getLoginName());   	    	
        return toAjax(wmsStockCheckService.auditNoWmsStockCheck(wmsStockCheck));
    }
}
