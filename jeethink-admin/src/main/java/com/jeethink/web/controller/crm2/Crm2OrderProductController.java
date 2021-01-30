package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.domain.AjaxResult.Type;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.CrmOrder;
import com.jeethink.crm.domain.CrmOrderProduct;
import com.jeethink.crm.service.crm1.ICrmOrderProductService;
import com.jeethink.crm.service.crm2.ICrm2OrderProductService;
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
 * 订单关联产品信息Controller
 * 
 * @author jeethink
 * @date 2020-03-13
 */
@Controller
@RequestMapping("/crm2/orderProduct")
public class Crm2OrderProductController extends BaseController
{
    private String prefix = "crm2/orderProduct";

    @Autowired
    private ICrm2OrderProductService crmOrderProductService;

    @RequiresPermissions("crm:orderProduct:view")
    @GetMapping()
    public String product()
    {
        return prefix + "/orderProduct";
    }

    /**
     * 查询订单关联产品信息列表，已保存的订单
     */
    @RequiresPermissions("crm:orderProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmOrderProduct crmOrderProduct)
    {
        startPage();
        CrmOrder order=new CrmOrder();
        crmOrderProduct.setOrder(order);
        crmOrderProduct.setOrderProductStatus("1");
        List<CrmOrderProduct> list = crmOrderProductService.selectCrmOrderProductList(crmOrderProduct);
        return getDataTable(list);
    }
    
    /**
     * 查询订单关联产品信息列表(所有)
     */
    //@RequiresPermissions("crm:orderProduct:list")
    @PostMapping("/listAll")
    @ResponseBody
    public TableDataInfo listAll(CrmOrderProduct crmOrderProduct)
    {
        startPage();
        List<CrmOrderProduct> list = crmOrderProductService.selectCrmOrderProductList(crmOrderProduct);
        return getDataTable(list);
    }

    /**
     * 导出订单关联产品信息列表
     */
    @RequiresPermissions("crm:orderProduct:export")
    @Log(title = "订单关联产品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmOrderProduct crmOrderProduct)
    {
        List<CrmOrderProduct> list = crmOrderProductService.selectCrmOrderProductList(crmOrderProduct);
        ExcelUtil<CrmOrderProduct> util = new ExcelUtil<CrmOrderProduct>(CrmOrderProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 新增订单关联产品信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单关联产品信息
     */
    @RequiresPermissions("crm:orderProduct:add")
    @Log(title = "订单关联产品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmOrderProduct crmOrderProduct)
    {
    	crmOrderProduct.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(crmOrderProductService.insertCrmOrderProduct(crmOrderProduct));
    }

    /**
     * 修改订单关联产品信息
     */
    @GetMapping("/edit/{orderProductId}")
    public String edit(@PathVariable("orderProductId") Long orderProductId, ModelMap mmap)
    {
        CrmOrderProduct crmOrderProduct = crmOrderProductService.selectCrmOrderProductById(orderProductId);
        mmap.put("crmOrderProduct", crmOrderProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单关联产品信息
     */
    @RequiresPermissions("crm:orderProduct:edit")
    @Log(title = "订单关联产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmOrderProduct crmOrderProduct)
    {
    	crmOrderProduct.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmOrderProductService.updateCrmOrderProduct(crmOrderProduct));
    }

    /**
     * 删除订单关联产品信息
     */
    @RequiresPermissions("crm:orderProduct:remove")
    @Log(title = "订单关联产品信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmOrderProductService.deleteCrmOrderProductByIds(ids));
    }
    
    /**
     * 批量选择产品，给订单
     */
    @Log(title = "订单关联产品信息", businessType = BusinessType.INSERT)
    @PostMapping("/selectOrderProduct")
    @ResponseBody
    public AjaxResult selectOrderProduct(Long orderId, String productIds, String productPrices,String orderType)
    {
    	String loginName=ShiroUtils.getLoginName();
        return toAjax(crmOrderProductService.insertOrderProducts(loginName,orderId, productIds,productPrices,orderType));
    }
    
    /**
     * 更新订单产品数量
     */
    @Log(title = "订单关联产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderProductCount")
    @ResponseBody
    public AjaxResult updateOrderProductCount(Long orderProductId, Long productCount)
    {
    	CrmOrderProduct crmOrderProduct = crmOrderProductService.selectCrmOrderProductById(orderProductId);
    	crmOrderProduct.setUpdateBy(ShiroUtils.getLoginName());
    	crmOrderProduct.setProductCount(productCount);
    	crmOrderProduct.setRealPrice((crmOrderProduct.getSellPrice()*crmOrderProduct.getProductCount()*crmOrderProduct.getDiscount())/10);
    	crmOrderProduct.setOrderProductStatus("0");
    	return toAjax(crmOrderProductService.updateCrmOrderProduct(crmOrderProduct));
    }
    
    /**
     * 更新订单产品销售价格
     */
    @Log(title = "订单关联产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderSellPrice")
    @ResponseBody
    public AjaxResult updateOrderSellPrice(Long orderProductId, Double sellPrice)
    {
    	CrmOrderProduct crmOrderProduct = crmOrderProductService.selectCrmOrderProductById(orderProductId);
    	crmOrderProduct.setUpdateBy(ShiroUtils.getLoginName());
    	crmOrderProduct.setSellPrice(sellPrice);
    	crmOrderProduct.setRealPrice((crmOrderProduct.getSellPrice()*crmOrderProduct.getProductCount()*crmOrderProduct.getDiscount())/10);
    	crmOrderProduct.setOrderProductStatus("0");
    	return toAjax(crmOrderProductService.updateCrmOrderProduct(crmOrderProduct));
    }
    
    /**
     * 更新订单产品销售价格
     */
    @Log(title = "订单关联产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateOrderDiscount")
    @ResponseBody
    public AjaxResult updateOrderDiscount(Long orderProductId, Double discount)
    {
    	CrmOrderProduct crmOrderProduct = crmOrderProductService.selectCrmOrderProductById(orderProductId);
    	crmOrderProduct.setUpdateBy(ShiroUtils.getLoginName());
    	crmOrderProduct.setDiscount(discount);
    	crmOrderProduct.setRealPrice((crmOrderProduct.getSellPrice()*crmOrderProduct.getProductCount()*crmOrderProduct.getDiscount())/10);
    	crmOrderProduct.setOrderProductStatus("0");
    	return toAjax(crmOrderProductService.updateCrmOrderProduct(crmOrderProduct));
    }
    
    /**
     * 获取订单产品销售价格 总金额、总成本
     */
    @Log(title = "订单关联产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/selectOrderPrice")
    @ResponseBody
    public AjaxResult selectOrderPrice(Long orderId)
    {
    	Double totalPrice = crmOrderProductService.selectOrderTotalPrice(orderId);
    	Double costlPrice = crmOrderProductService.selectOrderCostPrice(orderId);
    	Map<String,Double> map= new HashMap<>();
    	map.put("totalPrice",totalPrice);
    	map.put("costPrice",costlPrice);
    	return new AjaxResult(Type.SUCCESS,"计算成功",map); 
    }
}
