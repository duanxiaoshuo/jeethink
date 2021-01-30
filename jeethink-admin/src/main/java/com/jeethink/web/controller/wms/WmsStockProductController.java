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
import com.jeethink.crm.domain.WmsStockProduct;
import com.jeethink.crm.service.wms.IWmsStockProductService;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 库存操作和产品对应Controller
 * 
 * @author jeethink
 * @date 2020-08-21
 */
@Controller
@RequestMapping("/crm/stockProduct")
public class WmsStockProductController extends BaseController
{
    private String prefix = "crm/stockProduct";

    @Autowired
    private IWmsStockProductService wmsStockProductService;

    @RequiresPermissions("crm:stockProduct:view")
    @GetMapping()
    public String stockProduct()
    {
        return prefix + "/stockProduct";
    }

    /**
     * 查询库存操作和产品对应列表
     */
    @RequiresPermissions("crm:stockProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WmsStockProduct wmsStockProduct)
    {
        startPage();
        List<WmsStockProduct> list = wmsStockProductService.selectWmsStockProductList(wmsStockProduct);
        return getDataTable(list);
    }

    /**
     * 导出库存操作和产品对应列表
     */
    @RequiresPermissions("crm:stockProduct:export")
    @Log(title = "库存操作和产品对应", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WmsStockProduct wmsStockProduct)
    {
        List<WmsStockProduct> list = wmsStockProductService.selectWmsStockProductList(wmsStockProduct);
        ExcelUtil<WmsStockProduct> util = new ExcelUtil<WmsStockProduct>(WmsStockProduct.class);
        return util.exportExcel(list, "stockProduct");
    }

    /**
     * 新增库存操作和产品对应
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存库存操作和产品对应
     */
    @RequiresPermissions("crm:stockProduct:add")
    @Log(title = "库存操作和产品对应", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WmsStockProduct wmsStockProduct)
    {
        return toAjax(wmsStockProductService.insertWmsStockProduct(wmsStockProduct));
    }

    /**
     * 修改库存操作和产品对应
     */
    @GetMapping("/edit/{stockProductId}")
    public String edit(@PathVariable("stockProductId") Long stockProductId, ModelMap mmap)
    {
        WmsStockProduct wmsStockProduct = wmsStockProductService.selectWmsStockProductById(stockProductId);
        mmap.put("wmsStockProduct", wmsStockProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存操作和产品对应
     */
    @RequiresPermissions("crm:stockProduct:edit")
    @Log(title = "库存操作和产品对应", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WmsStockProduct wmsStockProduct)
    {
        return toAjax(wmsStockProductService.updateWmsStockProduct(wmsStockProduct));
    }

    /**
     * 删除库存操作和产品对应
     */
    @RequiresPermissions("crm:stockProduct:remove")
    @Log(title = "库存操作和产品对应", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wmsStockProductService.deleteWmsStockProductByIds(ids));
    }
}
