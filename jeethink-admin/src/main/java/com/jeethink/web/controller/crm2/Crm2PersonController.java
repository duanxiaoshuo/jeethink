package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.Crm2Customer;
import com.jeethink.crm.domain.Crm2Person;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmPerson;
import com.jeethink.crm.service.crm2.ICrm2CustomerService;
import com.jeethink.crm.service.crm2.ICrm2PersonService;
import com.jeethink.crm.service.crm2.ICrm2PersonService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 联系人Controller
 * 
 * @author jeethink
 * @date 2020-03-03
 */
@Controller
@RequestMapping("/crm2/person")
public class Crm2PersonController extends BaseController {
	private String prefix = "crm2/person";

	@Autowired
	private ICrm2PersonService crm2PersonService;

	@Autowired
	private ICrm2CustomerService crm2CustomerService;

	@RequiresPermissions("crm:person:view")
	@GetMapping()
	public String person() {
		return prefix + "/person";
	}

	/**
	 * 查询联系人列表
	 */
	@RequiresPermissions("crm:person:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Crm2Person crmPerson) {
		startPage();
		List<Crm2Person> list = crm2PersonService.selectCrmPersonList(crmPerson);
		return getDataTable(list);
	}

	/**
	 * 导出联系人列表
	 */
	@RequiresPermissions("crm:person:export")
	@Log(title = "联系人", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Crm2Person crmPerson) {
		List<Crm2Person> list = crm2PersonService.selectCrmPersonList(crmPerson);
		ExcelUtil<Crm2Person> util = new ExcelUtil<Crm2Person>(Crm2Person.class);
		return util.exportExcel(list, "person");
	}
	
	/**
	 * 新增联系人
	 */
	@GetMapping("/add")
	public String add() 
	{
		return prefix + "/add";
	}

	/**
	 * 新增联系人, 如果来自于客户详情新增，则有customerId
	 */
	@GetMapping("/addPerson/{customerId}")
	public String addPerson(@PathVariable("customerId") Long customerId, ModelMap mmap) {
		if (customerId != null) {
			Crm2Customer crmCustomer = crm2CustomerService.selectCrmCustomerById(customerId);
			mmap.put("crmCustomer", crmCustomer);
		}
		return prefix + "/addPerson";
	}

	/**
	 * 新增保存联系人
	 */
	@RequiresPermissions("crm:person:add")
	@Log(title = "联系人", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Crm2Person crmPerson) {
		crmPerson.setDelFlag("0");
		crmPerson.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(crm2PersonService.insertCrmPerson(crmPerson));
	}

	/**
	 * 修改联系人
	 */
	@GetMapping("/edit/{personId}")
	public String edit(@PathVariable("personId") Long personId, ModelMap mmap) {
		Crm2Person crmPerson = crm2PersonService.selectCrmPersonById(personId);
		mmap.put("crmPerson", crmPerson);
		return prefix + "/edit";
	}

	/**
	 * 修改保存联系人
	 */
	@RequiresPermissions("crm:person:edit")
	@Log(title = "联系人", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Crm2Person crmPerson) {
		crmPerson.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(crm2PersonService.updateCrmPerson(crmPerson));
	}

	/**
	 * 删除联系人
	 */
	@RequiresPermissions("crm:person:remove")
	@Log(title = "联系人", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(crm2PersonService.deleteCrmPersonByIds(ids));
	}
}
