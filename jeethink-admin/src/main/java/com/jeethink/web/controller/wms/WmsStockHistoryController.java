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
import com.jeethink.crm.domain.WmsStockHistory;
import com.jeethink.crm.service.wms.IWmsStockHistoryService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 库存记录Controller
 * 
 * @author jeethink
 * @date 2020-08-13
 */
@Controller
@RequestMapping("/crm/stockHistory")
public class WmsStockHistoryController extends BaseController
{
    private String prefix = "crm/stockHistory";

    @Autowired
    private IWmsStockHistoryService wmsStockHistoryService;

    @RequiresPermissions("crm:stockHistory:view")
    @GetMapping()
    public String stockHistory()
    {
        return prefix + "/stockHistory";
    }

    /**
     * 查询库存记录列表
     */
    @RequiresPermissions("crm:stockHistory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WmsStockHistory wmsStockHistory)
    {
        startPage();
        List<WmsStockHistory> list = wmsStockHistoryService.selectWmsStockHistoryList(wmsStockHistory);
        return getDataTable(list);
    }

    /**
     * 导出库存记录列表
     */
    @RequiresPermissions("crm:stockHistory:export")
    @Log(title = "库存记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WmsStockHistory wmsStockHistory)
    {
        List<WmsStockHistory> list = wmsStockHistoryService.selectWmsStockHistoryList(wmsStockHistory);
        ExcelUtil<WmsStockHistory> util = new ExcelUtil<WmsStockHistory>(WmsStockHistory.class);
        return util.exportExcel(list, "stockHistory");
    }

    /**
     * 新增库存记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存库存记录
     */
    @RequiresPermissions("crm:stockHistory:add")
    @Log(title = "库存记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WmsStockHistory wmsStockHistory)
    {
        return toAjax(wmsStockHistoryService.insertWmsStockHistory(wmsStockHistory));
    }

    /**
     * 修改库存记录
     */
    @GetMapping("/edit/{historyId}")
    public String edit(@PathVariable("historyId") Long historyId, ModelMap mmap)
    {
        WmsStockHistory wmsStockHistory = wmsStockHistoryService.selectWmsStockHistoryById(historyId);
        mmap.put("wmsStockHistory", wmsStockHistory);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存记录
     */
    @RequiresPermissions("crm:stockHistory:edit")
    @Log(title = "库存记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WmsStockHistory wmsStockHistory)
    {
        return toAjax(wmsStockHistoryService.updateWmsStockHistory(wmsStockHistory));
    }

    /**
     * 删除库存记录
     */
    @RequiresPermissions("crm:stockHistory:remove")
    @Log(title = "库存记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wmsStockHistoryService.deleteWmsStockHistoryByIds(ids));
    }
}
