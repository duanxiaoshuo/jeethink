package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.Crm2Customer;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmFollow;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm1.ICrmFollowService;
import com.jeethink.crm.service.crm2.ICrm2CustomerService;
import com.jeethink.crm.service.crm2.ICrm2FollowService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户跟进记录Controller
 * 
 * @author jeethink
 * @date 2020-03-10
 */
@Controller
@RequestMapping("/crm2/follow")
public class Crm2FollowController extends BaseController
{
    private String prefix = "crm2/follow";

    @Autowired
    private ICrm2FollowService crmFollowService;
    
    @Autowired
    private ICrm2CustomerService crmCustomerService;

    @RequiresPermissions("crm:follow:view")
    @GetMapping()
    public String follow()
    {
        return prefix + "/follow";
    }

    /**
     * 查询客户跟进记录列表
     */
    @RequiresPermissions("crm:follow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmFollow crmFollow)
    {
        startPage();
        List<CrmFollow> list = crmFollowService.selectCrmFollowList(crmFollow);
        return getDataTable(list);
    }

    /**
     * 导出客户跟进记录列表
     */
    @RequiresPermissions("crm:follow:export")
    @Log(title = "客户跟进记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmFollow crmFollow)
    {
        List<CrmFollow> list = crmFollowService.selectCrmFollowList(crmFollow);
        ExcelUtil<CrmFollow> util = new ExcelUtil<CrmFollow>(CrmFollow.class);
        return util.exportExcel(list, "follow");
    }

    /**
     * 新增客户跟进记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
    
    /**
     * 新增客户跟进记录
     * 如果来自于客户详情新增，则有customerId
     */
    @GetMapping("/addFollow/{customerId}")
    public String addFollow(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
    	Crm2Customer crmCustomer = crmCustomerService.selectCrmCustomerById(customerId);
        mmap.put("crmCustomer", crmCustomer);
        return prefix + "/addFollow";
    }

    /**
     * 新增保存客户跟进记录
     */
    @RequiresPermissions("crm:follow:add")
    @Log(title = "客户跟进记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmFollow crmFollow)
    {
    	crmFollow.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(crmFollowService.insertCrmFollow(crmFollow));
    }

    /**
     * 修改客户跟进记录
     */
    @GetMapping("/edit/{followId}")
    public String edit(@PathVariable("followId") Long followId, ModelMap mmap)
    {
        CrmFollow crmFollow = crmFollowService.selectCrmFollowById(followId);
        mmap.put("crmFollow", crmFollow);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户跟进记录
     */
    @RequiresPermissions("crm:follow:edit")
    @Log(title = "客户跟进记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmFollow crmFollow)
    {
    	crmFollow.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmFollowService.updateCrmFollow(crmFollow));
    }

    /**
     * 删除客户跟进记录
     */
    @RequiresPermissions("crm:follow:remove")
    @Log(title = "客户跟进记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmFollowService.deleteCrmFollowByIds(ids));
    }
}
