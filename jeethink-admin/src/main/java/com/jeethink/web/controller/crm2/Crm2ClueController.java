package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.Crm2Clue;
import com.jeethink.crm.domain.CrmClue;
import com.jeethink.crm.service.crm2.ICrm2ClueService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 线索管理Controller
 *
 * @author jeethink
 * @date 2020-04-06
 */
@Controller
@RequestMapping("/crm2/clue")
public class Crm2ClueController extends BaseController {
    private String prefix = "crm2/clue";

    @Autowired
    private ICrm2ClueService crm2ClueService;

    @RequiresPermissions("crm:clue:view")
    @GetMapping()
    public String clue() {
        return prefix + "/clue";
    }

    /**
     * 查询线索管理列表
     */
    @RequiresPermissions("crm:clue:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Crm2Clue crm2Clue) {
        startPage();
        List<Crm2Clue> list = crm2ClueService.selectCrmClueList(crm2Clue);
        return getDataTable(list);
    }

    /**
     * 查询线索管理列表 线索池
     */
    @RequiresPermissions("crm:clue:viewPublic")
    @GetMapping("/public")
    public String cluePublic() {
        return prefix + "/cluePublic";
    }

    /**
     * 查询线索管理列表 线索池
     */
    @RequiresPermissions("crm:clue:listPublic")
    @PostMapping("/listPublic")
    @ResponseBody
    public TableDataInfo listPublic(Crm2Clue Crm2Clue) {
        startPage();
        List<Crm2Clue> list = crm2ClueService.selectCrmClueListPublic(Crm2Clue);
        return getDataTable(list);
    }

    /**
     * 导出线索管理列表
     */
    @RequiresPermissions("crm:clue:export")
    @Log(title = "线索管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Crm2Clue crm2Clue) {
        List<Crm2Clue> list = crm2ClueService.selectCrmClueList(crm2Clue);
        ExcelUtil<Crm2Clue> util = new ExcelUtil<Crm2Clue>(Crm2Clue.class);
        return util.exportExcel(list, "clue");
    }

    /**
     * 新增线索管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存线索管理
     */
    @RequiresPermissions("crm:clue:add")
    @Log(title = "线索管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Crm2Clue crm2Clue) {
        String loginName = ShiroUtils.getLoginName();
        crm2Clue.setDelFlag("0");
        crm2Clue.setClueStatus("0");// 已保存
        crm2Clue.setCreateBy(loginName);
        crm2Clue.setSourceBelongTo(loginName);
        crm2Clue.setBelongTo(loginName);
        return toAjax(crm2ClueService.insertCrmClue(crm2Clue));
    }

    /**
     * 修改线索管理
     */
    @GetMapping("/edit/{clueId}")
    public String edit(@PathVariable("clueId") Long clueId, ModelMap mmap) {
        Crm2Clue crm2Clue = crm2ClueService.selectCrmClueById(clueId);
        mmap.put("crmClue", crm2Clue);
        return prefix + "/edit";
    }

    /**
     * 修改保存线索管理
     */
    @RequiresPermissions("crm:clue:edit")
    @Log(title = "线索管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Crm2Clue crm2Clue) {
        crm2Clue.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crm2ClueService.updateCrmClue(crm2Clue));
    }

    /**
     * 查看客户
     */
    @GetMapping("/detail/{clueId}")
    public String detail(@PathVariable("clueId") Long clueId, ModelMap mmap) {
        Crm2Clue crm2Clue = crm2ClueService.selectCrmClueById(clueId);
        mmap.put("crmClue", crm2Clue);
        return prefix + "/detail";
    }

    /**
     * 删除线索管理
     */
    @RequiresPermissions("crm:clue:remove")
    @Log(title = "线索管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(crm2ClueService.deleteCrmClueByIds(ids));
    }

    /**
     * 线索管理 作废、取消作废
     */
    @RequiresPermissions("crm:clue:share")
    @Log(title = "线索管理", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel")
    @ResponseBody
    public AjaxResult cancel(Long clueId, String isCancel) {
        return toAjax(crm2ClueService.cancelCrmClueById(clueId, isCancel));
    }

    /**
     * 线索管理 转成客户
     */
    @RequiresPermissions("crm:clue:convert")
    @Log(title = "线索管理", businessType = BusinessType.UPDATE)
    @PostMapping("/convert/{clueId}")
    @ResponseBody
    public AjaxResult convert(@PathVariable("clueId") Long clueId) {
        return toAjax(crm2ClueService.convertCrmClueById(clueId, ShiroUtils.getLoginName()));
    }

    /**
     * 导入线索
     */
    @Log(title = "线索管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("crm:clue:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<Crm2Clue> util = new ExcelUtil<Crm2Clue>(Crm2Clue.class);
        List<Crm2Clue> crm2ClueList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = crm2ClueService.importClue(crm2ClueList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 下载模版
     */
    @RequiresPermissions("crm:clue:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<CrmClue> util = new ExcelUtil<CrmClue>(CrmClue.class);
        return util.importTemplateExcel("线索模版");
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkMobileUnique")
    @ResponseBody
    public String checkMobileUnique(Crm2Clue crm2Clue) {
        return crm2ClueService.checkMobileUnique(crm2Clue);
    }
}
