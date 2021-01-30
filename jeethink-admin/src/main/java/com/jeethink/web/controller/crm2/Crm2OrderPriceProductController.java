package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.domain.AjaxResult.Type;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.CrmOrderPrice;
import com.jeethink.crm.domain.CrmOrderPriceProduct;
import com.jeethink.crm.service.crm1.ICrmOrderPriceProductService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报价单关联产品信息Controller
 * 
 * @author jeethink
 * @date 2020-03-13
 */
@Controller
@RequestMapping("/crm2/orderPriceProduct")
public class Crm2OrderPriceProductController extends BaseController
{
    private String prefix = "crm2/orderPriceProduct";

    @Autowired
    private ICrmOrderPriceProductService crmOrderPriceProductService;

    @RequiresPermissions("crm:orderPriceProduct:view")
    @GetMapping()
    public String product()
    {
        return prefix + "/orderPriceProduct";
    }

    /**
     * 查询报价单关联产品信息列表，已保存的报价单
     */
    //@RequiresPermissions("crm:orderPriceProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmOrderPriceProduct crmOrderPriceProduct)
    {
        startPage();
        CrmOrderPrice orderPrice=new CrmOrderPrice();
        crmOrderPriceProduct.setOrderPrice(orderPrice);
        crmOrderPriceProduct.setOrderProductStatus("1");
        List<CrmOrderPriceProduct> list = crmOrderPriceProductService.selectCrmOrderPriceProductList(crmOrderPriceProduct);
        return getDataTable(list);
    }
    
    /**
     * 查询报价单关联产品信息列表(所有)
     */
    //@RequiresPermissions("crm:orderPriceProduct:list")
    @PostMapping("/listAll")
    @ResponseBody
    public TableDataInfo listAll(CrmOrderPriceProduct crmOrderPriceProduct)
    {
        startPage();
        List<CrmOrderPriceProduct> list = crmOrderPriceProductService.selectCrmOrderPriceProductList(crmOrderPriceProduct);
        return getDataTable(list);
    }

    /**
     * 导出报价单关联产品信息列表
     */
    //@RequiresPermissions("crm:orderPriceProduct:export")
    @Log(title = "报价单关联产品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmOrderPriceProduct crmOrderPriceProduct)
    {
        List<CrmOrderPriceProduct> list = crmOrderPriceProductService.selectCrmOrderPriceProductList(crmOrderPriceProduct);
        ExcelUtil<CrmOrderPriceProduct> util = new ExcelUtil<CrmOrderPriceProduct>(CrmOrderPriceProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 新增报价单关联产品信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存报价单关联产品信息
     */
    //@RequiresPermissions("crm:orderPriceProduct:add")
    @Log(title = "报价单关联产品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmOrderPriceProduct crmOrderPriceProduct)
    {
    	crmOrderPriceProduct.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(crmOrderPriceProductService.insertCrmOrderPriceProduct(crmOrderPriceProduct));
    }

    /**
     * 修改报价单关联产品信息
     */
    @GetMapping("/edit/{orderPriceProductId}")
    public String edit(@PathVariable("orderPriceProductId") Long orderPriceProductId, ModelMap mmap)
    {
        CrmOrderPriceProduct crmOrderPriceProduct = crmOrderPriceProductService.selectCrmOrderPriceProductById(orderPriceProductId);
        mmap.put("crmOrderPriceProduct", crmOrderPriceProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存报价单关联产品信息
     */
    //@RequiresPermissions("crm:orderPriceProduct:edit")
    @Log(title = "报价单关联产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmOrderPriceProduct crmOrderPriceProduct)
    {
    	crmOrderPriceProduct.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmOrderPriceProductService.updateCrmOrderPriceProduct(crmOrderPriceProduct));
    }

    /**
     * 删除报价单关联产品信息
     */
    //@RequiresPermissions("crm:orderPriceProduct:remove")
    @Log(title = "报价单关联产品信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmOrderPriceProductService.deleteCrmOrderPriceProductByIds(ids));
    }
    
    /**
     * 批量选择产品，给报价单
     */
    @Log(title = "报价单关联产品信息", businessType = BusinessType.INSERT)
    @PostMapping("/selectOrderPriceProduct")
    @ResponseBody
    public AjaxResult selectOrderPriceProduct(Long orderId, String productIds, String productPrices,String orderType)
    {
    	String loginName=ShiroUtils.getLoginName();
        return toAjax(crmOrderPriceProductService.insertOrderPriceProducts(loginName,orderId, productIds,productPrices,orderType));
    }
    
    /**
     * 更新报价单产品数量
     */
    @Log(title = "报价单关联产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderPriceProductCount")
    @ResponseBody
    public AjaxResult updateOrderPriceProductCount(Long orderPriceProductId, Long productCount)
    {
    	CrmOrderPriceProduct crmOrderPriceProduct = crmOrderPriceProductService.selectCrmOrderPriceProductById(orderPriceProductId);
    	crmOrderPriceProduct.setUpdateBy(ShiroUtils.getLoginName());
    	crmOrderPriceProduct.setProductCount(productCount);
    	crmOrderPriceProduct.setRealPrice((crmOrderPriceProduct.getSellPrice()*crmOrderPriceProduct.getProductCount()*crmOrderPriceProduct.getDiscount())/10);
    	crmOrderPriceProduct.setOrderProductStatus("0");
    	return toAjax(crmOrderPriceProductService.updateCrmOrderPriceProduct(crmOrderPriceProduct));
    }
    
    /**
     * 更新报价单产品销售价格
     */
    @Log(title = "报价单关联产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderPriceSellPrice")
    @ResponseBody
    public AjaxResult updateOrderPriceSellPrice(Long orderPriceProductId, Double sellPrice)
    {
    	CrmOrderPriceProduct crmOrderPriceProduct = crmOrderPriceProductService.selectCrmOrderPriceProductById(orderPriceProductId);
    	crmOrderPriceProduct.setUpdateBy(ShiroUtils.getLoginName());
    	crmOrderPriceProduct.setSellPrice(sellPrice);
    	crmOrderPriceProduct.setRealPrice((crmOrderPriceProduct.getSellPrice()*crmOrderPriceProduct.getProductCount()*crmOrderPriceProduct.getDiscount())/10);
    	crmOrderPriceProduct.setOrderProductStatus("0");
    	return toAjax(crmOrderPriceProductService.updateCrmOrderPriceProduct(crmOrderPriceProduct));
    }
    
    /**
     * 更新报价单产品销售价格
     */
    @Log(title = "报价单关联产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderPriceDiscount")
    @ResponseBody
    public AjaxResult updateOrderPriceDiscount(Long orderPriceProductId, Double discount)
    {
    	CrmOrderPriceProduct crmOrderPriceProduct = crmOrderPriceProductService.selectCrmOrderPriceProductById(orderPriceProductId);
    	crmOrderPriceProduct.setUpdateBy(ShiroUtils.getLoginName());
    	crmOrderPriceProduct.setDiscount(discount);
    	crmOrderPriceProduct.setRealPrice((crmOrderPriceProduct.getSellPrice()*crmOrderPriceProduct.getProductCount()*crmOrderPriceProduct.getDiscount())/10);
    	crmOrderPriceProduct.setOrderProductStatus("0");
    	return toAjax(crmOrderPriceProductService.updateCrmOrderPriceProduct(crmOrderPriceProduct));
    }
    
    /**
     * 获取报价单产品销售价格 总金额
     */
    @Log(title = "报价单关联产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/selectOrderPrice")
    @ResponseBody
    public AjaxResult selectOrderPrice(Long orderPriceId)
    {
    	Double totalPrice = crmOrderPriceProductService.selectOrderPriceTotalPrice(orderPriceId);
    	Double costlPrice = crmOrderPriceProductService.selectOrderPriceCostPrice(orderPriceId);
    	Map<String,Double> map= new HashMap<>();
    	map.put("totalPrice",totalPrice);
    	map.put("costPrice",costlPrice);
    	return new AjaxResult(Type.SUCCESS,"计算成功",map); 
    }
}
