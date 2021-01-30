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
import com.jeethink.crm.domain.WmsProductCatogory;
import com.jeethink.crm.service.wms.IWmsProductCatogoryService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.utils.StringUtils;
import com.jeethink.common.core.domain.Ztree;

/**
 * 产品分类管理Controller
 * 
 * @author jeethink
 * @date 2020-04-30
 */
@Controller
@RequestMapping("/crm/productCatogory")
public class WmsProductCatogoryController extends BaseController
{
    private String prefix = "crm/productCatogory";

    @Autowired
    private IWmsProductCatogoryService wmsProductCatogoryService;

    @RequiresPermissions("crm:productCatogory:view")
    @GetMapping()
    public String productCatogory()
    {
        return prefix + "/productCatogory";
    }

    /**
     * 查询产品分类管理树列表
     */
    @RequiresPermissions("crm:productCatogory:list")
    @PostMapping("/list")
    @ResponseBody
    public List<WmsProductCatogory> list(WmsProductCatogory wmsProductCatogory)
    {
        List<WmsProductCatogory> list = wmsProductCatogoryService.selectWmsProductCatogoryList(wmsProductCatogory);
        return list;
    }

    /**
     * 导出产品分类管理列表
     */
    @RequiresPermissions("crm:productCatogory:export")
    @Log(title = "产品分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WmsProductCatogory wmsProductCatogory)
    {
        List<WmsProductCatogory> list = wmsProductCatogoryService.selectWmsProductCatogoryList(wmsProductCatogory);
        ExcelUtil<WmsProductCatogory> util = new ExcelUtil<WmsProductCatogory>(WmsProductCatogory.class);
        return util.exportExcel(list, "productCatogory");
    }

    /**
     * 新增产品分类管理
     */
    @GetMapping(value = { "/add/{productCatogoryId}", "/add/" })
    public String add(@PathVariable(value = "productCatogoryId", required = false) Long productCatogoryId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(productCatogoryId))
        {
            mmap.put("wmsProductCatogory", wmsProductCatogoryService.selectWmsProductCatogoryById(productCatogoryId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存产品分类管理
     */
    @RequiresPermissions("crm:productCatogory:add")
    @Log(title = "产品分类管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WmsProductCatogory wmsProductCatogory)
    {
    	wmsProductCatogory.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(wmsProductCatogoryService.insertWmsProductCatogory(wmsProductCatogory));
    }

    /**
     * 修改产品分类管理
     */
    @GetMapping("/edit/{productCatogoryId}")
    public String edit(@PathVariable("productCatogoryId") Long productCatogoryId, ModelMap mmap)
    {
        WmsProductCatogory wmsProductCatogory = wmsProductCatogoryService.selectWmsProductCatogoryById(productCatogoryId);
        mmap.put("wmsProductCatogory", wmsProductCatogory);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品分类管理
     */
    @RequiresPermissions("crm:productCatogory:edit")
    @Log(title = "产品分类管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WmsProductCatogory wmsProductCatogory)
    {
    	wmsProductCatogory.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(wmsProductCatogoryService.updateWmsProductCatogory(wmsProductCatogory));
    }

    /**
     * 删除
     */
    @RequiresPermissions("crm:productCatogory:remove")
    @Log(title = "产品分类管理", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{productCatogoryId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("productCatogoryId") Long productCatogoryId)
    {
        return toAjax(wmsProductCatogoryService.deleteWmsProductCatogoryById(productCatogoryId));
    }

    /**
     * 选择产品分类管理树
     */
    @GetMapping(value = { "/selectProductCatogoryTree/{productCatogoryId}", "/selectProductCatogoryTree/" })
    public String selectProductCatogoryTree(@PathVariable(value = "productCatogoryId", required = false) Long productCatogoryId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(productCatogoryId))
        {
            mmap.put("wmsProductCatogory", wmsProductCatogoryService.selectWmsProductCatogoryById(productCatogoryId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载产品分类管理树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = wmsProductCatogoryService.selectWmsProductCatogoryTree();
        return ztrees;
    }
}
