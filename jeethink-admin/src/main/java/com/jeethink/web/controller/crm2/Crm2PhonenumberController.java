package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.CrmPhonenumber;
import com.jeethink.crm.service.crm2.ICrm2PhonenumberService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 电话本Controller
 * 
 * @author jeethink
 * @date 2020-03-15
 */
@Controller
@RequestMapping("/crm2/phonenumber")
public class Crm2PhonenumberController extends BaseController
{
    private String prefix = "crm2/phonenumber";

    @Autowired
    private ICrm2PhonenumberService crmPhonenumberService;

    @RequiresPermissions("crm:phonenumber:view")
    @GetMapping()
    public String phonenumber()
    {
        return prefix + "/phonenumber";
    }

    /**
     * 查询电话本列表
     */
    @RequiresPermissions("crm:phonenumber:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmPhonenumber crmPhonenumber)
    {
        startPage();
        crmPhonenumber.setCreateBy(ShiroUtils.getLoginName());
        List<CrmPhonenumber> list = crmPhonenumberService.selectCrmPhonenumberList(crmPhonenumber);
        return getDataTable(list);
    }

    /**
     * 导出电话本列表
     */
    @RequiresPermissions("crm:phonenumber:export")
    @Log(title = "电话本", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmPhonenumber crmPhonenumber)
    {
        List<CrmPhonenumber> list = crmPhonenumberService.selectCrmPhonenumberList(crmPhonenumber);
        ExcelUtil<CrmPhonenumber> util = new ExcelUtil<CrmPhonenumber>(CrmPhonenumber.class);
        return util.exportExcel(list, "phonenumber");
    }

    /**
     * 新增电话本
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存电话本
     */
    @RequiresPermissions("crm:phonenumber:add")
    @Log(title = "电话本", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmPhonenumber crmPhonenumber)
    {
    	crmPhonenumber.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(crmPhonenumberService.insertCrmPhonenumber(crmPhonenumber));
    }

    /**
     * 修改电话本
     */
    @GetMapping("/edit/{phonenumberId}")
    public String edit(@PathVariable("phonenumberId") Long phonenumberId, ModelMap mmap)
    {
        CrmPhonenumber crmPhonenumber = crmPhonenumberService.selectCrmPhonenumberById(phonenumberId);
        mmap.put("crmPhonenumber", crmPhonenumber);
        return prefix + "/edit";
    }

    /**
     * 修改保存电话本
     */
    @RequiresPermissions("crm:phonenumber:edit")
    @Log(title = "电话本", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmPhonenumber crmPhonenumber)
    {
    	crmPhonenumber.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmPhonenumberService.updateCrmPhonenumber(crmPhonenumber));
    }

    /**
     * 删除电话本
     */
    @RequiresPermissions("crm:phonenumber:remove")
    @Log(title = "电话本", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmPhonenumberService.deleteCrmPhonenumberByIds(ids));
    }
}
