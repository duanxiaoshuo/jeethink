package com.jeethink.web.controller.wms;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.config.Global;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.crm.domain.WmsProduct;
import com.jeethink.crm.service.wms.IWmsProductService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.web.controller.system.SysProfileController;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.file.FileUploadUtils;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 产品Controller
 * 
 * @author jeethink
 * @date 2020-03-12
 */
@Controller
@RequestMapping("/crm/product")
public class WmsProductController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(SysProfileController.class);
    
    private String prefix = "crm/product";

    @Autowired
    private IWmsProductService wmsProductService;

    @RequiresPermissions("crm:product:view")
    @GetMapping()
    public String product()
    {
        return prefix + "/product";
    }

    /**
     * 查询产品列表
     */
    @RequiresPermissions("crm:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WmsProduct wmsProduct)
    {
        startPage();
        List<WmsProduct> list = wmsProductService.selectWmsProductList(wmsProduct);
        return getDataTable(list);
    }

    /**
     * 导出产品列表
     */
    @RequiresPermissions("crm:product:export")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WmsProduct wmsProduct)
    {
        List<WmsProduct> list = wmsProductService.selectWmsProductList(wmsProduct);
        ExcelUtil<WmsProduct> util = new ExcelUtil<WmsProduct>(WmsProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 新增产品
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品
     */
    @RequiresPermissions("crm:product:add")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WmsProduct wmsProduct)
    {
    	wmsProduct.setDelFlag("0");
    	wmsProduct.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(wmsProductService.insertWmsProduct(wmsProduct));
    }

    /**
     * 修改产品
     */
    @GetMapping("/edit/{productId}")
    public String edit(@PathVariable("productId") Long productId, ModelMap mmap)
    {
        WmsProduct wmsProduct = wmsProductService.selectWmsProductById(productId);
        mmap.put("wmsProduct", wmsProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品
     */
    @RequiresPermissions("crm:product:edit")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WmsProduct wmsProduct)
    {
    	wmsProduct.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsProductService.updateWmsProduct(wmsProduct));
    }

    /**
     * 删除产品
     */
    @RequiresPermissions("crm:product:remove")
    @Log(title = "产品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wmsProductService.deleteWmsProductByIds(ids));
    }
    
    /**
     * 修改产品图片
     */
    @GetMapping("/image/{productId}")
    public String image(@PathVariable("productId") Long productId,ModelMap mmap)
    {
    	WmsProduct wmsProduct = wmsProductService.selectWmsProductById(productId);
        mmap.put("wmsProduct", wmsProduct);
        return prefix + "/image";
    }
    
    /**
     * 保存产品图片
     */
    @Log(title = "修改产品图片", businessType = BusinessType.UPDATE)
    @PostMapping("/updateImage/{productId}")
    @ResponseBody
    public AjaxResult updateImage(@PathVariable("productId") Long productId,@RequestParam("imgfile") MultipartFile file)
    {
         try
         {
             if (!file.isEmpty())
             {
                 String image = FileUploadUtils.upload(Global.getUploadPath(), file);
                 WmsProduct wmsProduct = wmsProductService.selectWmsProductById(productId);
                 wmsProduct.setProductImage(image);
                 if (wmsProductService.updateWmsProduct(wmsProduct) > 0)
                 {
                     return success();
                 }
             }
             return error();
         }
         catch (Exception e)
         {
             log.error("修改图片失败！", e);
             return error(e.getMessage());
         }
    }

    /**
     * 查询选择产品 报价单
     */
    @GetMapping("/selectOrderPriceProduct/{orderId}")
    public String selectOrderPriceProduct(@PathVariable("orderId") Long orderId,ModelMap mmap)
    {
    	mmap.put("orderId", orderId);
        return prefix + "/selectOrderPriceProduct";
    }
    
    /**
     * 查询产品列表 报价单
     * 1、在售的
     * 2、该报价单已经有了，就不再列表中显示了
     */
    //@RequiresPermissions("crm:product:list")
    @PostMapping("/listOrderPriceProduct")
    @ResponseBody
    public TableDataInfo listOrderPriceProduct(WmsProduct wmsProduct)
    {
        startPage();
        List<WmsProduct> list = wmsProductService.selectOrderPriceWmsProductList(wmsProduct);
        return getDataTable(list);
    }
    
    /**
     * 查询选择产品 订单
     */
    @GetMapping("/selectOrderProduct/{orderId}")
    public String selectOrderProduct(@PathVariable("orderId") Long orderId,ModelMap mmap)
    {
    	mmap.put("orderId", orderId);
        return prefix + "/selectOrderProduct";
    }

    /**
     * 查询产品列表
     * 1、在售的
     * 2、库存有数量的
     * 3、该订单已经有了，就不再列表中显示了
     */
    //@RequiresPermissions("crm:product:listProduct")
    @PostMapping("/listOrderProduct")
    @ResponseBody
    public TableDataInfo listOrderProduct(WmsProduct wmsProduct)
    {
        startPage();
        List<WmsProduct> list = wmsProductService.selectOrderWmsProductList(wmsProduct);
        return getDataTable(list);
    }
    
    /**
     * 查询选择产品
     */
    @GetMapping("/selectProduct")
    public String selectProduct()
    {
        return prefix + "/selectProduct";
    }
    
    /**
     * 查询产品列表
     */
    @PostMapping("/listProduct")
    @ResponseBody
    public TableDataInfo listProduct(WmsProduct wmsProduct)
    {
        startPage();
        List<WmsProduct> list = wmsProductService.selectWmsProductList(wmsProduct);
        return getDataTable(list);
    }
    
    /**
     * 查询选择产品-入库
     */
    @GetMapping("/selectStockEnterProduct/{columnId}")
    public String selectStockEnterProduct(@PathVariable("columnId") Long columnId,ModelMap mmap)
    {
        mmap.put("columnId", columnId);
        return prefix + "/selectStockEnterProduct";
    }
}
