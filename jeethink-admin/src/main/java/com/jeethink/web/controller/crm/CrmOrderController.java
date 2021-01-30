package com.jeethink.web.controller.crm;

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
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmOrder;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm1.ICrmOrderService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.core.text.Convert;

/**
 * 客户订单 Controller
 * 
 * @author jeethink
 * @date 2020-03-12
 */
@Controller
@RequestMapping("/crm/order")
public class CrmOrderController extends BaseController
{
    private String prefix = "crm/order";

    @Autowired
    private ICrmOrderService crmOrderService;    

	@Autowired
    private ICrmCustomerService crmCustomerService;

    @RequiresPermissions("crm:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询客户订单）列表
     */
    @RequiresPermissions("crm:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmOrder crmOrder)
    {
        startPage();
        List<CrmOrder> list = crmOrderService.selectCrmOrderList(crmOrder);
        return getDataTable(list);
    }
    
    /**
     * 导出客户订单）列表
     */
    @RequiresPermissions("crm:order:export")
    @Log(title = "客户订单）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmOrder crmOrder)
    {
        List<CrmOrder> list = crmOrderService.selectCrmOrderList(crmOrder);
        ExcelUtil<CrmOrder> util = new ExcelUtil<CrmOrder>(CrmOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增客户订单）
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户订单）
     */
    @RequiresPermissions("crm:order:add")
    @Log(title = "客户订单）", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmOrder crmOrder)
    {
    	String loginName=ShiroUtils.getLoginName();
    	crmOrder.setDelFlag("0");
    	crmOrder.setCreateBy(loginName);
    	crmOrder.setSourceBelongTo(loginName);
    	crmOrder.setBelongTo(loginName);
        return toAjax(crmOrderService.insertCrmOrder(crmOrder));
    }

    /**
     * 修改客户订单）
     */
    @GetMapping("/edit/{orderId}")
    public String edit(@PathVariable("orderId") Long orderId, ModelMap mmap)
    {
        CrmOrder crmOrder = crmOrderService.selectCrmOrderById(orderId);
        mmap.put("crmOrder", crmOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户订单）
     */
    @RequiresPermissions("crm:order:edit")
    @Log(title = "客户订单）", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmOrder crmOrder)
    {
    	crmOrder.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmOrderService.updateCrmOrder(crmOrder));
    }

    /**
     * 删除客户订单）
     */
    @RequiresPermissions("crm:order:remove")
    @Log(title = "客户订单）", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmOrderService.deleteCrmOrderByIds(ids));
    }
    

    /**
     * 查询选择订单 （回款管理使用）
     */
    @GetMapping("/selectOrder/{customerId}")
    public String selectOrder(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
    	 mmap.put("customerId", customerId);
        return prefix + "/selectOrder";
    }
    
    /**
     * 查询客户订单）列表
     */
    @PostMapping("/listSelectOrder")
    @ResponseBody
    public TableDataInfo listSelectOrder(CrmOrder crmOrder)
    {
        startPage();
        List<CrmOrder> list = crmOrderService.selectCrmOrderList(crmOrder);
        return getDataTable(list);
    }
    
    /**
     * 查看订单
     */
    @GetMapping("/detail/{orderId}")
    public String detail(@PathVariable("orderId") Long orderId, ModelMap mmap)
    {
    	CrmOrder crmOrder = crmOrderService.selectCrmOrderById(orderId);
        mmap.put("crmOrder", crmOrder);
        return prefix + "/detail";
    }

    /**
     * 新增订单
     * 如果来自于客户详情新增，则有customerId
     */
    @GetMapping("/addOrder/{customerId}")
    public String addOrderPrice(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
    	CrmCustomer crmCustomer = crmCustomerService.selectCrmCustomerById(customerId);
        mmap.put("crmCustomer", crmCustomer);
        return prefix + "/addOrder";
    }    

    /**
     * 订单 检测回款金额是否合理 
     */
    @GetMapping("/checkPaymentMoney")
    @ResponseBody
    public Boolean checkPaymentMoney(Long orderId,String payMoney)
    {
    	CrmOrder crmOrder = crmOrderService.selectCrmOrderById(orderId);
    	Double totalMoney=crmOrder.getTotalPrice();	
    	boolean isRight=(totalMoney-Convert.toDouble(payMoney))>=0 &&(totalMoney-Convert.toDouble(payMoney))<totalMoney?true:false;
    	return isRight;
    }
}
