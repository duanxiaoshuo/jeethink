package com.jeethink.web.controller.crm2;


import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmOrderPrice;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm1.ICrmOrderPriceService;
import com.jeethink.crm.service.crm2.ICrm2OrderPriceService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户报价单Controller
 * 
 * @author jeethink
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/crm2/orderPrice")
public class Crm2OrderPriceController extends BaseController {
	private String prefix = "crm2/orderPrice";

	@Autowired
	private ICrm2OrderPriceService crmOrderPriceService;
	
	@Autowired
    private ICrmCustomerService crmCustomerService;

	@RequiresPermissions("crm:orderPrice:view")
	@GetMapping()
	public String orderPrice() {
		return prefix + "/orderPrice";
	}

	/**
	 * 查询客户报价单列表
	 */
	@RequiresPermissions("crm:orderPrice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(CrmOrderPrice crmOrderPrice) {
		startPage();
		List<CrmOrderPrice> list = crmOrderPriceService.selectCrmOrderPriceList(crmOrderPrice);
		return getDataTable(list);
	}

	/**
	 * 导出客户报价单列表
	 */
	@RequiresPermissions("crm:orderPrice:export")
	@Log(title = "客户报价单", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(CrmOrderPrice crmOrderPrice) {
		List<CrmOrderPrice> list = crmOrderPriceService.selectCrmOrderPriceList(crmOrderPrice);
		ExcelUtil<CrmOrderPrice> util = new ExcelUtil<CrmOrderPrice>(CrmOrderPrice.class);
		return util.exportExcel(list, "orderPrice");
	}

	/**
	 * 新增客户报价单
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存客户报价单
	 */
	@RequiresPermissions("crm:orderPrice:add")
	@Log(title = "客户报价单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(CrmOrderPrice crmOrderPrice) {		
		String loginName=ShiroUtils.getLoginName();
    	crmOrderPrice.setOrderStatus("0");
    	crmOrderPrice.setDelFlag("0");
    	crmOrderPrice.setCreateBy(loginName);
    	crmOrderPrice.setSourceBelongTo(loginName);
    	crmOrderPrice.setBelongTo(loginName);	
		return toAjax(crmOrderPriceService.insertCrmOrderPrice(crmOrderPrice));
	}

	/**
	 * 修改客户报价单
	 */
	@GetMapping("/edit/{orderId}")
	public String edit(@PathVariable("orderId") Long orderId, ModelMap mmap) {
		CrmOrderPrice crmOrderPrice = crmOrderPriceService.selectCrmOrderPriceById(orderId);
		mmap.put("crmOrderPrice", crmOrderPrice);
		return prefix + "/edit";
	}

	/**
	 * 修改保存客户报价单
	 */
	@RequiresPermissions("crm:orderPrice:edit")
	@Log(title = "客户报价单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(CrmOrderPrice crmOrderPrice) {
		crmOrderPrice.setOrderStatus("0");
		crmOrderPrice.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(crmOrderPriceService.updateCrmOrderPrice(crmOrderPrice));
	}

	/**
	 * 删除客户报价单
	 */
	@RequiresPermissions("crm:orderPrice:remove")
	@Log(title = "客户报价单", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(crmOrderPriceService.deleteCrmOrderPriceByIds(ids));
	}	

    /**
     * 查看报价单
     */
    @GetMapping("/detail/{orderId}")
    public String detail(@PathVariable("orderId") Long orderId, ModelMap mmap)
    {
    	CrmOrderPrice crmOrderPrice = crmOrderPriceService.selectCrmOrderPriceById(orderId);
        mmap.put("crmOrderPrice", crmOrderPrice);
        return prefix + "/detail";
    }
	
	/**
	 * 提交保存客户报价单
	 */
	@RequiresPermissions("crm:orderPrice:submit")
	@Log(title = "客户报价单", businessType = BusinessType.UPDATE)
	@PostMapping("/submit")
	@ResponseBody
	public AjaxResult submitSave(CrmOrderPrice crmOrderPrice) {
		crmOrderPrice.setOrderStatus("1");
		crmOrderPrice.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(crmOrderPriceService.updateCrmOrderPrice(crmOrderPrice));
	}

	/**
	 * 保存客户报价单 作废
	 */
	@RequiresPermissions("crm:orderPrice:cancel")
	@Log(title = "客户报价单", businessType = BusinessType.UPDATE)
	@PostMapping("/cancelOk/{orderId}")
	@ResponseBody
	public AjaxResult cancelOk(@PathVariable("orderId") Long orderId) {
		CrmOrderPrice crmOrderPrice = crmOrderPriceService.selectCrmOrderPriceById(orderId);
		crmOrderPrice.setOrderStatus("4");
		crmOrderPrice.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(crmOrderPriceService.updateCrmOrderPrice(crmOrderPrice));
	}

	/**
	 * 保存客户报价单 取消作废
	 */
	@RequiresPermissions("crm:orderPrice:cancel")
	@Log(title = "客户报价单", businessType = BusinessType.UPDATE)
	@PostMapping("/cancelNo/{orderId}")
	@ResponseBody
	public AjaxResult cancelNo(@PathVariable("orderId") Long orderId) {
		CrmOrderPrice crmOrderPrice = crmOrderPriceService.selectCrmOrderPriceById(orderId);
		crmOrderPrice.setOrderStatus("0");
		crmOrderPrice.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(crmOrderPriceService.updateCrmOrderPrice(crmOrderPrice));
	}

	/**
	 * 保存客户报价单 审核通过
	 */
	@RequiresPermissions("crm:orderPrice:audit")
	@Log(title = "客户报价单", businessType = BusinessType.UPDATE)
	@PostMapping("/auditOk/{orderId}")
	@ResponseBody
	public AjaxResult auditOk(@PathVariable("orderId") Long orderId) {
		CrmOrderPrice crmOrderPrice = crmOrderPriceService.selectCrmOrderPriceById(orderId);
		crmOrderPrice.setOrderStatus("2");
		crmOrderPrice.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(crmOrderPriceService.updateCrmOrderPrice(crmOrderPrice));
	}

	/**
	 * 保存客户报价单 审核驳回
	 */
	@RequiresPermissions("crm:orderPrice:audit")
	@Log(title = "客户报价单", businessType = BusinessType.UPDATE)
	@PostMapping("/auditNo/{orderId}")
	@ResponseBody
	public AjaxResult auditNo(@PathVariable("orderId") Long orderId)  {
		CrmOrderPrice crmOrderPrice = crmOrderPriceService.selectCrmOrderPriceById(orderId);
		crmOrderPrice.setOrderStatus("3");
		crmOrderPrice.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(crmOrderPriceService.updateCrmOrderPrice(crmOrderPrice));
	}
	
	/**
	 * 保存客户报价单 报价
	 */
	@RequiresPermissions("crm:orderPrice:price")
	@Log(title = "客户报价单", businessType = BusinessType.UPDATE)
	@PostMapping("/price/{orderId}")
	@ResponseBody
	public AjaxResult price(@PathVariable("orderId") Long orderId)  {
		CrmOrderPrice crmOrderPrice = crmOrderPriceService.selectCrmOrderPriceById(orderId);
		crmOrderPrice.setOrderStatus("5");
		crmOrderPrice.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(crmOrderPriceService.updateCrmOrderPrice(crmOrderPrice));
	}
	
	/**
     * 转成订单
     */
    @RequiresPermissions("crm:orderPrice:convert")
    @Log(title = "转成订单", businessType = BusinessType.UPDATE)
    @PostMapping( "/convert/{orderId}")
    @ResponseBody
    public AjaxResult convert(@PathVariable("orderId")Long orderId)
    {
        return toAjax(crmOrderPriceService.convertCrmOrderPriceById(orderId,ShiroUtils.getLoginName()));
    }
    
    /**
     * 新增报价单
     * 如果来自于客户详情新增，则有customerId
     */
    @GetMapping("/addOrderPrice/{customerId}")
    public String addOrderPrice(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
    	CrmCustomer crmCustomer = crmCustomerService.selectCrmCustomerById(customerId);
        mmap.put("crmCustomer", crmCustomer);
        return prefix + "/addOrderPrice";
    }

}
