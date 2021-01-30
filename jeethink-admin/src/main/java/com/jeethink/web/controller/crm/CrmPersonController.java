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
import com.jeethink.crm.domain.CrmPerson;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm1.ICrmPersonService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 联系人Controller
 * 
 * @author jeethink
 * @date 2020-03-03
 */
@Controller
@RequestMapping("/crm/person")
public class CrmPersonController extends BaseController {
	private String prefix = "crm/person";

	@Autowired
	private ICrmPersonService crmPersonService;

	@Autowired
	private ICrmCustomerService crmCustomerService;

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
	public TableDataInfo list(CrmPerson crmPerson) {
		startPage();
		List<CrmPerson> list = crmPersonService.selectCrmPersonList(crmPerson);
		return getDataTable(list);
	}

	/**
	 * 导出联系人列表
	 */
	@RequiresPermissions("crm:person:export")
	@Log(title = "联系人", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(CrmPerson crmPerson) {
		List<CrmPerson> list = crmPersonService.selectCrmPersonList(crmPerson);
		ExcelUtil<CrmPerson> util = new ExcelUtil<CrmPerson>(CrmPerson.class);
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
			CrmCustomer crmCustomer = crmCustomerService.selectCrmCustomerById(customerId);
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
	public AjaxResult addSave(CrmPerson crmPerson) {
		crmPerson.setDelFlag("0");
		crmPerson.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(crmPersonService.insertCrmPerson(crmPerson));
	}

	/**
	 * 修改联系人
	 */
	@GetMapping("/edit/{personId}")
	public String edit(@PathVariable("personId") Long personId, ModelMap mmap) {
		CrmPerson crmPerson = crmPersonService.selectCrmPersonById(personId);
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
	public AjaxResult editSave(CrmPerson crmPerson) {
		crmPerson.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(crmPersonService.updateCrmPerson(crmPerson));
	}

	/**
	 * 删除联系人
	 */
	@RequiresPermissions("crm:person:remove")
	@Log(title = "联系人", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(crmPersonService.deleteCrmPersonByIds(ids));
	}
}
