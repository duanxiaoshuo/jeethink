package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.CrmDaily;
import com.jeethink.crm.service.crm1.ICrmDailyService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日志管理Controller
 * 
 * @author jeethink
 * @date 2020-03-15
 */
@Controller
@RequestMapping("/crm2/daily")
public class Crm2DailyController extends BaseController
{
    private String prefix = "crm2/daily";

    @Autowired
    private ICrmDailyService crmDailyService;

    @RequiresPermissions("crm:daily:view")
    @GetMapping()
    public String daily()
    {
        return prefix + "/daily";
    }

    /**
     * 查询日志管理列表
     */
    @RequiresPermissions("crm:daily:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmDaily crmDaily)
    {
        startPage();
        List<CrmDaily> list = crmDailyService.selectCrmDailyList(crmDaily);
        return getDataTable(list);
    }

    /**
     * 导出日志管理列表
     */
    @RequiresPermissions("crm:daily:export")
    @Log(title = "日志管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmDaily crmDaily)
    {
        List<CrmDaily> list = crmDailyService.selectCrmDailyList(crmDaily);
        ExcelUtil<CrmDaily> util = new ExcelUtil<CrmDaily>(CrmDaily.class);
        return util.exportExcel(list, "daily");
    }

    /**
     * 新增日志管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存日志管理
     */
    @RequiresPermissions("crm:daily:add")
    @Log(title = "日志管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmDaily crmDaily)
    {
    	crmDaily.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(crmDailyService.insertCrmDaily(crmDaily));
    }

    /**
     * 修改日志管理
     */
    @GetMapping("/edit/{dailyId}")
    public String edit(@PathVariable("dailyId") Long dailyId, ModelMap mmap)
    {
        CrmDaily crmDaily = crmDailyService.selectCrmDailyById(dailyId);
        mmap.put("crmDaily", crmDaily);
        return prefix + "/edit";
    }

    /**
     * 修改保存日志管理
     */
    @RequiresPermissions("crm:daily:edit")
    @Log(title = "日志管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmDaily crmDaily)
    {
    	crmDaily.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmDailyService.updateCrmDaily(crmDaily));
    }

    /**
     * 删除日志管理
     */
    @RequiresPermissions("crm:daily:remove")
    @Log(title = "日志管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmDailyService.deleteCrmDailyByIds(ids));
    }
    
    /**
     * 查看日志
     */
    @GetMapping("/detail/{dailyId}")
    public String detail(@PathVariable("dailyId") Long dailyId, ModelMap mmap)
    {
        CrmDaily crmDaily = crmDailyService.selectCrmDailyById(dailyId);
        mmap.put("crmDaily", crmDaily);
        return prefix + "/detail";
    }
}
