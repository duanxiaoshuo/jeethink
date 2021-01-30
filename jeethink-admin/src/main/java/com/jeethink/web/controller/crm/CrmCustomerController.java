package com.jeethink.web.controller.crm;


import java.util.List;

import com.jeethink.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmCustomerTemplate;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 客户Controller
 *
 * @author jeethink
 * @date 2020-03-01
 */
@Controller
@RequestMapping("/crm/customer")
public class CrmCustomerController extends BaseController
{
    private String prefix = "crm/customer";

    @Autowired
    private ICrmCustomerService crmCustomerService;

    @RequiresPermissions("crm:customer:view")
    @GetMapping()
    public String customer()
    {
        return prefix + "/customer";
    }

    /**
     * 查询客户列表
     */
    @RequiresPermissions("crm:customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmCustomer crmCustomer)
    {
        startPage();
        List<CrmCustomer> list = crmCustomerService.selectCrmCustomerList(crmCustomer);
        return getDataTable(list);
    }

    /**
     * 查询客户列表 我的
     */
    @RequiresPermissions("crm:customer:viewMy")
    @GetMapping("/my")
    public String customerMy()
    {
        return prefix + "/customerMy";
    }

    /**
     * 查询客户列表 我的
     */
    @RequiresPermissions("crm:customer:listMy")
    @PostMapping("/listMy")
    @ResponseBody
    public TableDataInfo listMy(CrmCustomer crmCustomer)
    {
        startPage();
        List<CrmCustomer> list = crmCustomerService.selectCrmCustomerListMy(crmCustomer);
        return getDataTable(list);
    }

    /**
     * 查询客户列表 共享
     */
    @RequiresPermissions("crm:customer:viewShare")
    @GetMapping("/share")
    public String customerShare()
    {
        return prefix + "/customerShare";
    }

    /**
     * 查询客户列表 共享
     */
    @RequiresPermissions("crm:customer:listShare")
    @PostMapping("/listShare")
    @ResponseBody
    public TableDataInfo listShare(CrmCustomer crmCustomer)
    {
        Long userId = ShiroUtils.getUserId();
        startPage();
        List<CrmCustomer> list = crmCustomerService.selectCrmCustomerListShare(crmCustomer,userId);
        return getDataTable(list);
    }

    /**
     * 查询客户列表 公共
     */
    @RequiresPermissions("crm:customer:viewPublic")
    @GetMapping("/public")
    public String customerPublic()
    {
        return prefix + "/customerPublic";
    }

    /**
     * 查询客户列表 公共
     */
    @RequiresPermissions("crm:customer:listPublic")
    @PostMapping("/listPublic")
    @ResponseBody
    public TableDataInfo listPublic(CrmCustomer crmCustomer)
    {
        startPage();
        List<CrmCustomer> list = crmCustomerService.selectCrmCustomerListPublic(crmCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    @RequiresPermissions("crm:customer:export")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmCustomer crmCustomer)
    {
        List<CrmCustomer> list = crmCustomerService.selectCrmCustomerList(crmCustomer);
        ExcelUtil<CrmCustomer> util = new ExcelUtil<CrmCustomer>(CrmCustomer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 新增客户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户
     */
    @RequiresPermissions("crm:customer:add")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmCustomer crmCustomer)
    {
    	String loginName=ShiroUtils.getLoginName();
    	crmCustomer.setDelFlag("0");
    	crmCustomer.setCreateBy(loginName);
    	crmCustomer.setSourceBelongTo(loginName);
    	crmCustomer.setBelongTo(loginName);
        return toAjax(crmCustomerService.insertCrmCustomer(crmCustomer));
    }

    /**
     * 修改客户
     */
    @GetMapping("/edit/{customerId}")
    public String edit(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
        CrmCustomer crmCustomer = crmCustomerService.selectCrmCustomerById(customerId);
        mmap.put("crmCustomer", crmCustomer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户
     */
    @RequiresPermissions("crm:customer:edit")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmCustomer crmCustomer)
    {
    	crmCustomer.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmCustomerService.updateCrmCustomer(crmCustomer));
    }

    /**
     * 查看客户
     */
    @GetMapping("/detail/{customerId}")
    public String detail(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
        CrmCustomer crmCustomer = crmCustomerService.selectCrmCustomerById(customerId);
        mmap.put("crmCustomer", crmCustomer);
        return prefix + "/detail";
    }

    /**
     * 删除客户
     */
    @RequiresPermissions("crm:customer:remove")
    @Log(title = "客户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmCustomerService.deleteCrmCustomerByIds(ids));
    }

    /**
     * 查询选择客户
     */
    @GetMapping("/selectCustomer")
    public String selectCustomer()
    {
        return prefix + "/selectCustomer";
    }

    /**
     * 查询客户列表(all)
     */
    @PostMapping("/listAll")
    @ResponseBody
    public TableDataInfo listAll(CrmCustomer crmCustomer)
    {
        startPage();
        List<CrmCustomer> list = crmCustomerService.selectCrmCustomerList(crmCustomer);
        return getDataTable(list);
    }

    /**
     * 共享客户
     */
    @RequiresPermissions("crm:customer:share")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PostMapping( "/share")
    @ResponseBody
    public AjaxResult share(String customerIds,String isShare,Long shared)
    {
        Long share = ShiroUtils.getUserId();
        String loginName = ShiroUtils.getLoginName();
        return toAjax(crmCustomerService.shareCrmCustomerByIds(customerIds,isShare,ShiroUtils.getLoginName(),share,shared, loginName));
    }
    
    /**
     * 导入客户
     */
    @Log(title = "客户", businessType = BusinessType.IMPORT)
    @RequiresPermissions("crm:customer:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<CrmCustomerTemplate> util = new ExcelUtil<CrmCustomerTemplate>(CrmCustomerTemplate.class);
        List<CrmCustomerTemplate> customerList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = crmCustomerService.importCustomer(customerList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 下载模版
     */
    @RequiresPermissions("crm:customer:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<CrmCustomerTemplate> util = new ExcelUtil<CrmCustomerTemplate>(CrmCustomerTemplate.class);
        return util.importTemplateExcel("客户模版");
    }

	/**
	 * 校验客户名称
	 */
	@PostMapping("/checkCustomerNameUnique")
	@ResponseBody
	public String checkCustomerNameUnique(CrmCustomer crmCustomer) {
		return crmCustomerService.checkCustomerNameUnique(crmCustomer);
	}

	/**
	 * 校验手机号码
	 */
	@PostMapping("/checkMobileUnique")
	@ResponseBody
	public String checkMobileUnique(CrmCustomer crmCustomer) {
		return crmCustomerService.checkMobileUnique(crmCustomer);
	}
	
	/**
	 * 校验座机
	 */
	@PostMapping("/checkTelephoneUnique")
	@ResponseBody
	public String checkTelephoneUnique(CrmCustomer crmCustomer) {
		return crmCustomerService.checkTelephoneUnique(crmCustomer);
	}

	/**
	 * 校验座机
	 */
	@PostMapping("/checkEmailUnique")
	@ResponseBody
	public String checkEmailUnique(CrmCustomer crmCustomer) {
		return crmCustomerService.checkEmailUnique(crmCustomer);
	}
}
