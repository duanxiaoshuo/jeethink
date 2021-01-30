package com.jeethink.web.controller.crm;

import java.util.List;

import io.swagger.annotations.Api;
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
import com.jeethink.crm.domain.CrmClue;
import com.jeethink.crm.service.crm1.ICrmClueService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 线索管理Controller
 * 
 * @author jeethink
 * @date 2020-04-06
 */
@Controller
@RequestMapping("/crm/clue")
public class CrmClueController extends BaseController {
	private String prefix = "crm/clue";

	@Autowired
	private ICrmClueService crmClueService;

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
	public TableDataInfo list(CrmClue crmClue) {
		startPage();
		List<CrmClue> list = crmClueService.selectCrmClueList(crmClue);
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
	public TableDataInfo listPublic(CrmClue crmClue) {
		startPage();
		List<CrmClue> list = crmClueService.selectCrmClueListPublic(crmClue);
		return getDataTable(list);
	}

	/**
	 * 导出线索管理列表
	 */
	@RequiresPermissions("crm:clue:export")
	@Log(title = "线索管理", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(CrmClue crmClue) {
		List<CrmClue> list = crmClueService.selectCrmClueList(crmClue);
		ExcelUtil<CrmClue> util = new ExcelUtil<CrmClue>(CrmClue.class);
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
	public AjaxResult addSave(CrmClue crmClue) {
		String loginName = ShiroUtils.getLoginName();
		crmClue.setDelFlag("0");
		crmClue.setClueStatus("0");// 已保存
		crmClue.setCreateBy(loginName);
		crmClue.setSourceBelongTo(loginName);
		crmClue.setBelongTo(loginName);
		return toAjax(crmClueService.insertCrmClue(crmClue));
	}

	/**
	 * 修改线索管理
	 */
	@GetMapping("/edit/{clueId}")
	public String edit(@PathVariable("clueId") Long clueId, ModelMap mmap) {
		CrmClue crmClue = crmClueService.selectCrmClueById(clueId);
		mmap.put("crmClue", crmClue);
		return prefix + "/edit";
	}

	/**
	 * 修改保存线索管理
	 */
	@RequiresPermissions("crm:clue:edit")
	@Log(title = "线索管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(CrmClue crmClue) {
		crmClue.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(crmClueService.updateCrmClue(crmClue));
	}

	/**
	 * 查看客户
	 */
	@GetMapping("/detail/{clueId}")
	public String detail(@PathVariable("clueId") Long clueId, ModelMap mmap) {
		CrmClue crmClue = crmClueService.selectCrmClueById(clueId);
		mmap.put("crmClue", crmClue);
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
		return toAjax(crmClueService.deleteCrmClueByIds(ids));
	}

	/**
	 * 线索管理 作废、取消作废
	 */
	@RequiresPermissions("crm:clue:share")
	@Log(title = "线索管理", businessType = BusinessType.UPDATE)
	@PostMapping("/cancel")
	@ResponseBody
	public AjaxResult cancel(Long clueId, String isCancel) {
		return toAjax(crmClueService.cancelCrmClueById(clueId, isCancel));
	}

	/**
	 * 线索管理 转成客户
	 */
	@RequiresPermissions("crm:clue:convert")
	@Log(title = "线索管理", businessType = BusinessType.UPDATE)
	@PostMapping("/convert/{clueId}")
	@ResponseBody
	public AjaxResult convert(@PathVariable("clueId") Long clueId) {
		return toAjax(crmClueService.convertCrmClueById(clueId, ShiroUtils.getLoginName()));
	}

	/**
	 * 导入线索
	 */
	@Log(title = "线索管理", businessType = BusinessType.IMPORT)
	@RequiresPermissions("crm:clue:import")
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
		ExcelUtil<CrmClue> util = new ExcelUtil<CrmClue>(CrmClue.class);
		List<CrmClue> crmClueList = util.importExcel(file.getInputStream());
		String operName = ShiroUtils.getSysUser().getLoginName();
		String message = crmClueService.importClue(crmClueList, updateSupport, operName);
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
	public String checkMobileUnique(CrmClue crmClue) {
		return crmClueService.checkMobileUnique(crmClue);
	}
}
