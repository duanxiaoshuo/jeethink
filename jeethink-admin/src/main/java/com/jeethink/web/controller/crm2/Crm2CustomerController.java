package com.jeethink.web.controller.crm2;


import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.Crm2Customer;
import com.jeethink.crm.domain.Crm2CustomerTemplate;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmCustomerTemplate;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm2.ICrm2CustomerService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 客户Controller
 *
 * @author jeethink
 * @date 2020-03-01
 */
@Controller
@RequestMapping("/crm2/customer")
public class Crm2CustomerController extends BaseController {
    private String prefix = "crm2/customer";

    @Autowired
    private ICrm2CustomerService crm2CustomerService;

    @RequiresPermissions("crm:customer:view")
    @GetMapping()
    public String customer() {
        return prefix + "/customer";
    }

    /**
     * 查询客户列表
     */
    @RequiresPermissions("crm:customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Crm2Customer crmCustomer) {
        startPage();
        List<Crm2Customer> list = crm2CustomerService.selectCrmCustomerList(crmCustomer);
        return getDataTable(list);
    }

    /**
     * 查询客户列表 我的
     */
    @RequiresPermissions("crm:customer:viewMy")
    @GetMapping("/my")
    public String customerMy() {
        return prefix + "/customerMy";
    }

    /**
     * 查询客户列表 我的
     */
    @RequiresPermissions("crm:customer:listMy")
    @PostMapping("/listMy")
    @ResponseBody
    public TableDataInfo listMy(Crm2Customer crmCustomer) {
        startPage();
        List<Crm2Customer> list = crm2CustomerService.selectCrmCustomerListMy(crmCustomer);
        return getDataTable(list);
    }

    /**
     * 查询客户列表 共享
     */
    @RequiresPermissions("crm:customer:viewShare")
    @GetMapping("/share")
    public String customerShare() {
        return prefix + "/customerShare";
    }

    /**
     * 查询客户列表 共享
     */
    @RequiresPermissions("crm:customer:listShare")
    @PostMapping("/listShare")
    @ResponseBody
    public TableDataInfo listShare(Crm2Customer crmCustomer)
    {
        Long userId = ShiroUtils.getUserId();
        startPage();
        List<Crm2Customer> list = crm2CustomerService.selectCrmCustomerListShare(crmCustomer,userId);
        return getDataTable(list);
    }

    /**
     * 查询客户列表 公共
     */
    @RequiresPermissions("crm:customer:viewPublic")
    @GetMapping("/public")
    public String customerPublic() {
        return prefix + "/customerPublic";
    }

    /**
     * 查询客户列表 公共
     */
    @RequiresPermissions("crm:customer:listPublic")
    @PostMapping("/listPublic")
    @ResponseBody
    public TableDataInfo listPublic(Crm2Customer crmCustomer) {
        startPage();
        List<Crm2Customer> list = crm2CustomerService.selectCrmCustomerListPublic(crmCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    @RequiresPermissions("crm:customer:export")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Crm2Customer crmCustomer) {
        List<Crm2Customer> list = crm2CustomerService.selectCrmCustomerList(crmCustomer);
        ExcelUtil<Crm2Customer> util = new ExcelUtil<Crm2Customer>(Crm2Customer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 新增客户
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存客户
     */
    @RequiresPermissions("crm:customer:add")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Crm2Customer crmCustomer) {
        String loginName = ShiroUtils.getLoginName();
        crmCustomer.setDelFlag("0");
        crmCustomer.setCreateBy(loginName);
        crmCustomer.setSourceBelongTo(loginName);
        crmCustomer.setBelongTo(loginName);
        return toAjax(crm2CustomerService.insertCrmCustomer(crmCustomer));
    }

    /**
     * 修改客户
     */
    @GetMapping("/edit/{customerId}")
    public String edit(@PathVariable("customerId") Long customerId, ModelMap mmap) {
        Crm2Customer crmCustomer = crm2CustomerService.selectCrmCustomerById(customerId);
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
    public AjaxResult editSave(Crm2Customer crmCustomer) {
        crmCustomer.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crm2CustomerService.updateCrmCustomer(crmCustomer));
    }

    /**
     * 查看客户
     */
    @GetMapping("/detail/{customerId}")
    public String detail(@PathVariable("customerId") Long customerId, ModelMap mmap) {
        Crm2Customer crmCustomer = crm2CustomerService.selectCrmCustomerById(customerId);
        mmap.put("crmCustomer", crmCustomer);
        return prefix + "/detail";
    }

    /**
     * 删除客户
     */
    @RequiresPermissions("crm:customer:remove")
    @Log(title = "客户", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(crm2CustomerService.deleteCrmCustomerByIds(ids));
    }

    /**
     * 查询选择客户
     */
    @GetMapping("/selectCustomer")
    public String selectCustomer() {
        return prefix + "/selectCustomer";
    }

    /**
     * 查询客户列表(all)
     */
    @PostMapping("/listAll")
    @ResponseBody
    public TableDataInfo listAll(Crm2Customer crmCustomer) {
        startPage();
        List<Crm2Customer> list = crm2CustomerService.selectCrmCustomerList(crmCustomer);
        return getDataTable(list);
    }


    /**
     * 共享客户
     */
    @RequiresPermissions("crm:customer:share")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PostMapping("/share")
    @ResponseBody
    public AjaxResult share(Long customerId, String isShare, Long shared) {
        Long share = ShiroUtils.getUserId();
        String loginName = ShiroUtils.getLoginName();
        return toAjax(crm2CustomerService.shareCrmCustomerByIds(customerId, isShare, ShiroUtils.getLoginName(), share, shared, loginName));
    }

    /**
     * 导入客户
     */
    @Log(title = "客户", businessType = BusinessType.IMPORT)
    @RequiresPermissions("crm:customer:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Crm2CustomerTemplate> util = new ExcelUtil<Crm2CustomerTemplate>(Crm2CustomerTemplate.class);
        List<Crm2CustomerTemplate> customerList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = crm2CustomerService.importCustomer(customerList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 下载模版
     */
    @RequiresPermissions("crm:customer:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<CrmCustomerTemplate> util = new ExcelUtil<CrmCustomerTemplate>(CrmCustomerTemplate.class);
        return util.importTemplateExcel("客户模版");
    }

    /**
     * 校验客户名称
     */
    @PostMapping("/checkCustomerNameUnique")
    @ResponseBody
    public String checkCustomerNameUnique(Crm2Customer crmCustomer) {
        return crm2CustomerService.checkCustomerNameUnique(crmCustomer);
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkMobileUnique")
    @ResponseBody
    public String checkMobileUnique(Crm2Customer crmCustomer) {
        return crm2CustomerService.checkMobileUnique(crmCustomer);
    }

    /**
     * 校验座机
     */
    @PostMapping("/checkTelephoneUnique")
    @ResponseBody
    public String checkTelephoneUnique(Crm2Customer crmCustomer) {
        return crm2CustomerService.checkTelephoneUnique(crmCustomer);
    }

    /**
     * 校验座机
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(Crm2Customer crmCustomer) {
        return crm2CustomerService.checkEmailUnique(crmCustomer);
    }
}
