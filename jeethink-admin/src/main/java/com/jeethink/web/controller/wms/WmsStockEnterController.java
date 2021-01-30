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
import com.jeethink.crm.domain.WmsStockEnter;
import com.jeethink.crm.service.wms.IWmsStockEnterService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 入库管理Controller
 * 
 * @author jeethink
 * @date 2020-03-16
 */
@Controller
@RequestMapping("/crm/stockEnter")
public class WmsStockEnterController extends BaseController
{
    private String prefix = "crm/stockEnter";

    @Autowired
    private IWmsStockEnterService wmsStockEnterService;

    @RequiresPermissions("crm:stockEnter:view")
    @GetMapping()
    public String stockEnter()
    {
        return prefix + "/stockEnter";
    }

    /**
     * 查询入库管理列表
     */
    @RequiresPermissions("crm:stockEnter:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WmsStockEnter wmsStockEnter)
    {
        startPage();
        List<WmsStockEnter> list = wmsStockEnterService.selectWmsStockEnterList(wmsStockEnter);
        return getDataTable(list);
    }

    /**
     * 导出入库管理列表
     */
    @RequiresPermissions("crm:stockEnter:export")
    @Log(title = "入库管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WmsStockEnter wmsStockEnter)
    {
        List<WmsStockEnter> list = wmsStockEnterService.selectWmsStockEnterList(wmsStockEnter);
        ExcelUtil<WmsStockEnter> util = new ExcelUtil<WmsStockEnter>(WmsStockEnter.class);
        return util.exportExcel(list, "stockEnter");
    }

    /**
     * 新增入库管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存入库管理
     */
    @RequiresPermissions("crm:stockEnter:add")
    @Log(title = "入库管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WmsStockEnter wmsStockEnter)
    {
		wmsStockEnter.setEnterType("0");//直接入库
    	wmsStockEnter.setEnterStatus("0");//已保存
    	wmsStockEnter.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockEnterService.insertWmsStockEnter(wmsStockEnter));        
    }

    /**
     * 修改入库管理
     */
    @GetMapping("/edit/{enterId}")
    public String edit(@PathVariable("enterId") Long enterId, ModelMap mmap)
    {
        WmsStockEnter wmsStockEnter = wmsStockEnterService.selectWmsStockEnterById(enterId);
        mmap.put("wmsStockEnter", wmsStockEnter);
        return prefix + "/edit";
    }

    /**
     * 修改保存入库管理
     */
    @RequiresPermissions("crm:stockEnter:edit")
    @Log(title = "入库管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WmsStockEnter wmsStockEnter)
    {
    	wmsStockEnter.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockEnterService.updateWmsStockEnter(wmsStockEnter));
    }

    /**
     * 入库管理 详情
     */
    @GetMapping("/detail/{enterId}")
    public String detail(@PathVariable("enterId") Long enterId, ModelMap mmap)
    {
        WmsStockEnter wmsStockEnter = wmsStockEnterService.selectWmsStockEnterById(enterId);
        mmap.put("wmsStockEnter", wmsStockEnter);
        return prefix + "/detail";
    }

    /**
     * 删除入库管理
     */
    @RequiresPermissions("crm:stockEnter:remove")
    @Log(title = "入库管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wmsStockEnterService.deleteWmsStockEnterByIds(ids));
    }
    
    /**
     * 提交入库管理
     */
    @RequiresPermissions("crm:stockEnter:submit")
    @Log(title = "入库管理", businessType = BusinessType.UPDATE)
    @PostMapping("/submit")
    @ResponseBody
    public AjaxResult submitSave(WmsStockEnter wmsStockEnter)
    {
    	wmsStockEnter.setEnterStatus("1");//已提交
    	wmsStockEnter.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockEnterService.updateWmsStockEnter(wmsStockEnter));
    }
    
    /**
     * 入库管理，审核入库操作
     */
    @RequiresPermissions("crm:stockEnter:audit")
    @Log(title = "入库管理", businessType = BusinessType.UPDATE)
    @PostMapping("/auditOk/{enterId}")
    @ResponseBody
    public AjaxResult auditOk(@PathVariable("enterId") Long enterId)
    {
    	WmsStockEnter wmsStockEnter = wmsStockEnterService.selectWmsStockEnterById(enterId);
    	wmsStockEnter.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockEnterService.auditOkWmsStockEnter(wmsStockEnter));
    }
    
    /**
     * 入库管理  审核
     */
    @RequiresPermissions("crm:stockEnter:audit")
    @Log(title = "入库管理", businessType = BusinessType.UPDATE)
    @PostMapping("/auditNo/{enterId}")
    @ResponseBody
    public AjaxResult auditNoSave(@PathVariable("enterId") Long enterId)
    {
    	WmsStockEnter wmsStockEnter = wmsStockEnterService.selectWmsStockEnterById(enterId);
    	wmsStockEnter.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockEnterService.auditNoWmsStockEnter(wmsStockEnter));
    }
}
