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
import com.jeethink.crm.domain.WmsStock;
import com.jeethink.crm.service.wms.IWmsStockService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 库存管理Controller
 * 
 * @author jeethink
 * @date 2020-03-15
 */
@Controller
@RequestMapping("/crm/stock")
public class WmsStockController extends BaseController
{
    private String prefix = "crm/stock";

    @Autowired
    private IWmsStockService wmsStockService;

    @RequiresPermissions("crm:stock:view")
    @GetMapping()
    public String stock()
    {
        return prefix + "/stock";
    }

    /**
     * 查询库存管理列表
     */
    @RequiresPermissions("crm:stock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WmsStock wmsStock)
    {
        startPage();
        List<WmsStock> list = wmsStockService.selectWmsStockList(wmsStock);
        return getDataTable(list);
    }

    /**
     * 导出库存管理列表
     */
    @RequiresPermissions("crm:stock:export")
    @Log(title = "库存管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WmsStock wmsStock)
    {
        List<WmsStock> list = wmsStockService.selectWmsStockList(wmsStock);
        ExcelUtil<WmsStock> util = new ExcelUtil<WmsStock>(WmsStock.class);
        return util.exportExcel(list, "stock");
    }

    /**
     * 新增库存管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存库存管理
     */
    @RequiresPermissions("crm:stock:add")
    @Log(title = "库存管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WmsStock wmsStock)
    {
    	wmsStock.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockService.insertWmsStock(wmsStock));
    }

    /**
     * 修改库存管理
     */
    @GetMapping("/edit/{stockId}")
    public String edit(@PathVariable("stockId") Long stockId, ModelMap mmap)
    {
        WmsStock wmsStock = wmsStockService.selectWmsStockById(stockId);
        mmap.put("wmsStock", wmsStock);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存管理
     */
    @RequiresPermissions("crm:stock:edit")
    @Log(title = "库存管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WmsStock wmsStock)
    {
    	wmsStock.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsStockService.updateWmsStock(wmsStock));
    }

    /**
     * 删除库存管理
     */
    @RequiresPermissions("crm:stock:remove")
    @Log(title = "库存管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wmsStockService.deleteWmsStockByIds(ids));
    }

    /**
     * 查询选择仓库-商品-出库使用
     */
    @GetMapping("/selectStorageProductOut/{storageId}/{columnId}")
    public String selectStorageProduct(@PathVariable("storageId") Long storageId,@PathVariable("columnId") Long columnId,ModelMap mmap)
    {
    	mmap.put("storageId", storageId);
    	mmap.put("columnId", columnId);
        return prefix + "/selectStorageProduct_out";
    }

    /**
     * 查询选择仓库-商品-调拨使用
     */
    @GetMapping("/selectStorageProductAllocation/{storageId}/{columnId}")
    public String selectStorageProductAllocation(@PathVariable("storageId") Long storageId,@PathVariable("columnId") Long columnId,ModelMap mmap)
    {
    	mmap.put("storageId", storageId);
    	mmap.put("columnId", columnId);
        return prefix + "/selectStorageProduct_allocation";
    }

    /**
     * 查询选择仓库-商品-盘点使用
     */
    @GetMapping("/selectStorageProductCheck/{storageId}/{columnId}")
    public String selectStorageProductCheck(@PathVariable("storageId") Long storageId,@PathVariable("columnId") Long columnId,ModelMap mmap)
    {
    	mmap.put("storageId", storageId);
    	mmap.put("columnId", columnId);
        return prefix + "/selectStorageProduct_check";
    }
    

    /**
     * 检验出库商品是否大于库存商品
     */
    @PostMapping("/checkStockNum")
    @ResponseBody
    public Boolean checkStockNum(Long storageId,Long productId,Long num)
    {
    	WmsStock wmsStock=wmsStockService.selectWmsStock(storageId, productId);    	
    	return wmsStock.getStockNum()>=num;
    }
}
