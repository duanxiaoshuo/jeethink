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
import com.jeethink.crm.domain.WmsStorage;
import com.jeethink.crm.service.wms.IWmsStorageService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 仓库Controller
 * 
 * @author jeethink
 * @date 2020-07-29
 */
@Controller
@RequestMapping("/crm/storage")
public class WmsStorageController extends BaseController
{
    private String prefix = "crm/storage";

    @Autowired
    private IWmsStorageService wmsStorageService;

    @RequiresPermissions("crm:storage:view")
    @GetMapping()
    public String storage()
    {
        return prefix + "/storage";
    }

    /**
     * 查询仓库列表
     */
    @RequiresPermissions("crm:storage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WmsStorage wmsStorage)
    {
        startPage();
        List<WmsStorage> list = wmsStorageService.selectWmsStorageList(wmsStorage);
        return getDataTable(list);
    }

    /**
     * 导出仓库列表
     */
    @RequiresPermissions("crm:storage:export")
    @Log(title = "仓库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WmsStorage wmsStorage)
    {
        List<WmsStorage> list = wmsStorageService.selectWmsStorageList(wmsStorage);
        ExcelUtil<WmsStorage> util = new ExcelUtil<WmsStorage>(WmsStorage.class);
        return util.exportExcel(list, "storage");
    }

    /**
     * 新增仓库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存仓库
     */
    @RequiresPermissions("crm:storage:add")
    @Log(title = "仓库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WmsStorage wmsStorage)
    {
    	wmsStorage.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStorageService.insertWmsStorage(wmsStorage));
    }

    /**
     * 修改仓库
     */
    @GetMapping("/edit/{storageId}")
    public String edit(@PathVariable("storageId") Long storageId, ModelMap mmap)
    {
        WmsStorage wmsStorage = wmsStorageService.selectWmsStorageById(storageId);
        mmap.put("wmsStorage", wmsStorage);
        return prefix + "/edit";
    }

    /**
     * 修改保存仓库
     */
    @RequiresPermissions("crm:storage:edit")
    @Log(title = "仓库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WmsStorage wmsStorage)
    {
    	wmsStorage.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStorageService.updateWmsStorage(wmsStorage));
    }

    /**
     * 删除仓库
     */
    @RequiresPermissions("crm:storage:remove")
    @Log(title = "仓库", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wmsStorageService.deleteWmsStorageByIds(ids));
    }

    /**
     * 查询选择仓库
     */
    @GetMapping("/selectStorage")
    public String selectStorage(ModelMap mmap)
    {
        return prefix + "/selectStorage";
    }

    /**
     * 查询选择调出仓库
     */
    @GetMapping("/selectOutStorage")
    public String selectOutStorage(ModelMap mmap)
    {
        return prefix + "/selectOutStorage";
    }

    /**
     * 查询选择调入仓库
     */
    @GetMapping("/selectEnterStorage")
    public String selectEnterStorage(ModelMap mmap)
    {
        return prefix + "/selectEnterStorage";
    }
}
