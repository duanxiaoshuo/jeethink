package com.jeethink.web.controller.wms;

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
import com.jeethink.crm.domain.WmsStockOut;
import com.jeethink.crm.service.wms.IWmsStockOutService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 出库管理Controller
 * 
 * @author jeethink
 * @date 2020-03-16
 */
@Controller
@RequestMapping("/crm/stockOut")
public class WmsStockOutController extends BaseController {
	private String prefix = "crm/stockOut";

	@Autowired
	private IWmsStockOutService wmsStockOutService;

	@RequiresPermissions("crm:stockOut:view")
	@GetMapping()
	public String stockOut() {
		return prefix + "/stockOut";
	}

	/**
	 * 查询出库管理列表
	 */
	@RequiresPermissions("crm:stockOut:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WmsStockOut wmsStockOut) {
		startPage();
		List<WmsStockOut> list = wmsStockOutService.selectWmsStockOutList(wmsStockOut);
		return getDataTable(list);
	}

	/**
	 * 导出出库管理列表
	 */
	@RequiresPermissions("crm:stockOut:export")
	@Log(title = "出库管理", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(WmsStockOut wmsStockOut) {
		List<WmsStockOut> list = wmsStockOutService.selectWmsStockOutList(wmsStockOut);
		ExcelUtil<WmsStockOut> util = new ExcelUtil<WmsStockOut>(WmsStockOut.class);
		return util.exportExcel(list, "stockOut");
	}

	/**
	 * 新增出库管理
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存出库管理
	 */
	@RequiresPermissions("crm:stockOut:add")
	@Log(title = "出库管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WmsStockOut wmsStockOut) {
    	wmsStockOut.setOutType("0");//直接出库
		wmsStockOut.setOutStatus("0");//已保存
		wmsStockOut.setCreateBy(ShiroUtils.getLoginName());
		return toAjax(wmsStockOutService.insertWmsStockOut(wmsStockOut));
	}

	/**
	 * 修改出库管理
	 */
	@GetMapping("/edit/{outId}")
	public String edit(@PathVariable("outId") Long outId, ModelMap mmap) {
		WmsStockOut wmsStockOut = wmsStockOutService.selectWmsStockOutById(outId);
		mmap.put("wmsStockOut", wmsStockOut);
		return prefix + "/edit";
	}

	/**
	 * 修改保存出库管理
	 */
	@RequiresPermissions("crm:stockOut:edit")
	@Log(title = "出库管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WmsStockOut wmsStockOut) {
		wmsStockOut.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(wmsStockOutService.updateWmsStockOut(wmsStockOut));
	}

    /**
     * 入库管理 详情
     */
    @GetMapping("/detail/{outId}")
    public String detail(@PathVariable("outId") Long outId, ModelMap mmap)
    {
    	WmsStockOut wmsStockOut = wmsStockOutService.selectWmsStockOutById(outId);
        mmap.put("wmsStockOut", wmsStockOut);
        return prefix + "/detail";
    }

	/**
	 * 删除出库管理
	 */
	@RequiresPermissions("crm:stockOut:remove")
	@Log(title = "出库管理", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(wmsStockOutService.deleteWmsStockOutByIds(ids));
	}

	/**
	 * 提交出库管理
	 */
	@RequiresPermissions("crm:stockOut:submit")
	@Log(title = "出库管理", businessType = BusinessType.UPDATE)
	@PostMapping("/submit")
	@ResponseBody
	public AjaxResult submitSave(WmsStockOut wmsStockOut) {
		wmsStockOut.setOutStatus("1");//已提交
		wmsStockOut.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(wmsStockOutService.updateWmsStockOut(wmsStockOut));
	}

	/**
	 * 出库管理，审核出库操作
	 */
	@RequiresPermissions("crm:stockOut:audit")
	@Log(title = "出库管理", businessType = BusinessType.UPDATE)
	@PostMapping("/auditOk/{outId}")
	@ResponseBody
	public AjaxResult auditOk(@PathVariable("outId") Long outId) {
		WmsStockOut wmsStockOut = wmsStockOutService.selectWmsStockOutById(outId);
		wmsStockOut.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(wmsStockOutService.auditOkWmsStockOut(wmsStockOut));
	}
	
	/**
	 * 出库管理，审核出库 驳回
	 */
	@RequiresPermissions("crm:stockOut:audit")
	@Log(title = "出库管理", businessType = BusinessType.UPDATE)
	@PostMapping("/auditNo/{outId}")
	@ResponseBody
	public AjaxResult auditNoSave(@PathVariable("outId") Long outId) {
		WmsStockOut wmsStockOut = wmsStockOutService.selectWmsStockOutById(outId);
		wmsStockOut.setUpdateBy(ShiroUtils.getLoginName());
		return toAjax(wmsStockOutService.auditNoWmsStockOut(wmsStockOut));
	}
}
