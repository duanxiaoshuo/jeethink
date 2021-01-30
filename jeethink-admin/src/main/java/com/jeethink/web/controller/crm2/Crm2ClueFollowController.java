package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.CrmClue;
import com.jeethink.crm.domain.CrmClueFollow;
import com.jeethink.crm.service.crm2.ICrm2ClueService;
import com.jeethink.crm.service.crm2.ICrm2ClueFollowService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 线索跟进记录Controller
 *
 * @author jeethink
 * @date 2020-09-2
 */
@Controller
@RequestMapping("/crm2/clueFollow")
public class Crm2ClueFollowController extends BaseController {
    private String prefix = "crm2/clueFollow";

    @Autowired
    private ICrm2ClueFollowService crm2ClueFollowService;

    @Autowired
    private ICrm2ClueService iCrm2ClueService;

    @RequiresPermissions("crm:clueFollow:view")
    @GetMapping()
    public String clueFollow() {
        return prefix + "/clueFollow";
    }

    /**
     * 查询线索跟进记录列表
     */
    @RequiresPermissions("crm:clueFollow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmClueFollow crmFollow) {
        startPage();
        List<CrmClueFollow> list = crm2ClueFollowService.selectCrmClueFollowList(crmFollow);
        return getDataTable(list);
    }

    /**
     * 导出线索跟进记录列表
     */
    @RequiresPermissions("crm:clueFollow:export")
    @Log(title = "线索跟进记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmClueFollow crmFollow) {
        List<CrmClueFollow> list = crm2ClueFollowService.selectCrmClueFollowList(crmFollow);
        ExcelUtil<CrmClueFollow> util = new ExcelUtil<CrmClueFollow>(CrmClueFollow.class);
        return util.exportExcel(list, "clueFollow");
    }

    /**
     * 新增线索跟进记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增线索跟进记录
     * 如果来自于线索详情新增，则有clueId
     */
    @GetMapping("/addFollow/{clueId}")
    public String addFollow(@PathVariable("clueId") Long clueId, ModelMap mmap) {
        CrmClue crmCLue = iCrm2ClueService.selectCrmClueById(clueId);
        mmap.put("crmCLue", crmCLue);
        return prefix + "/addFollow";
    }

    /**
     * 新增保存线索跟进记录
     */
    @RequiresPermissions("crm:clueFollow:add")
    @Log(title = "线索跟进记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmClueFollow crmFollow) {
        crmFollow.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(crm2ClueFollowService.insertCrmClueFollow(crmFollow));
    }

    /**
     * 修改线索跟进记录
     */
    @GetMapping("/edit/{followId}")
    public String edit(@PathVariable("followId") Long followId, ModelMap mmap) {
        CrmClueFollow crmClueFollow = crm2ClueFollowService.selectCrmClueFollowById(followId);
        mmap.put("crmClueFollow", crmClueFollow);
        return prefix + "/edit";
    }

    /**
     * 修改保存线索跟进记录
     */
    @RequiresPermissions("crm:clueFollow:edit")
    @Log(title = "线索跟进记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmClueFollow crmFollow) {
        crmFollow.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crm2ClueFollowService.updateCrmClueFollow(crmFollow));
    }

    /**
     * 删除线索跟进记录
     */
    @RequiresPermissions("crm:clueFollow:remove")
    @Log(title = "线索跟进记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(crm2ClueFollowService.deleteCrmClueFollowByIds(ids));
    }
}
