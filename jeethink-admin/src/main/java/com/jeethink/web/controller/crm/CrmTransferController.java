package com.jeethink.web.controller.crm;

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
import com.jeethink.crm.domain.CrmTransfer;
import com.jeethink.crm.service.crm1.ICrmTransferService;
import com.jeethink.framework.util.ShiroUtils;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.common.core.page.TableDataInfo;

/**
 * 转交记录Controller
 * 
 * @author jeethink
 * @date 2020-04-19
 */
@Controller
@RequestMapping("/crm/transfer")
public class CrmTransferController extends BaseController
{
    private String prefix = "crm/transfer";

    @Autowired
    private ICrmTransferService crmTransferService;

    @RequiresPermissions("crm:transfer:view")
    @GetMapping()
    public String transfer()
    {
        return prefix + "/transfer";
    }

    /**
     * 查询转交记录列表
     */
    @RequiresPermissions("crm:transfer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CrmTransfer crmTransfer)
    {
        startPage();
        List<CrmTransfer> list = crmTransferService.selectCrmTransferList(crmTransfer);
        return getDataTable(list);
    }
    
    @RequiresPermissions("crm:transfer:view")
    @GetMapping("/transferPublicHistory")
    public String transferPublicHistory(String businessType, ModelMap mmap)
    {
        mmap.put("businessType", businessType);
        return prefix + "/transferPublicHistory";
    }
    
    /**
     * 查询转交记录列表  转公客
     */
    @RequiresPermissions("crm:transfer:list")
    @PostMapping("/listPublicHistory")
    @ResponseBody
    public TableDataInfo listPublic(CrmTransfer crmTransfer)
    {
    	crmTransfer.setTransferType("0");
        startPage();
        List<CrmTransfer> list = crmTransferService.selectCrmTransferList(crmTransfer);
        return getDataTable(list);
    }
    
    @RequiresPermissions("crm:transfer:view")
    @GetMapping("/transferPersonHistory")
    public String transferPersonHistory(String businessType, ModelMap mmap)
    {
        mmap.put("businessType", businessType);
        return prefix + "/transferPersonHistory";
    }
    
    /**
     * 查询转交记录列表  转个人
     */
    @RequiresPermissions("crm:transfer:list")
    @PostMapping("/listPersonHistory")
    @ResponseBody
    public TableDataInfo listPersonHistory(CrmTransfer crmTransfer)
    {
    	crmTransfer.setTransferType("1");
        startPage();
        List<CrmTransfer> list = crmTransferService.selectCrmTransferList(crmTransfer);
        return getDataTable(list);
    }

    @RequiresPermissions("crm:transfer:view")
    @GetMapping("/transferGetHistory")
    public String transferGetHistory(String businessType, ModelMap mmap)
    {
        mmap.put("businessType", businessType);
        return prefix + "/transferGetHistory";
    }
    
    /**
     * 查询转交记录列表  领取公客
     */
    @RequiresPermissions("crm:transfer:list")
    @PostMapping("/listGetHistory")
    @ResponseBody
    public TableDataInfo listGet(CrmTransfer crmTransfer)
    {
    	crmTransfer.setTransferType("2");
        startPage();
        List<CrmTransfer> list = crmTransferService.selectCrmTransferList(crmTransfer);
        return getDataTable(list);
    }

    /**
     * 导出转交记录列表
     */
    @RequiresPermissions("crm:transfer:export")
    @Log(title = "转交记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CrmTransfer crmTransfer)
    {
        List<CrmTransfer> list = crmTransferService.selectCrmTransferList(crmTransfer);
        ExcelUtil<CrmTransfer> util = new ExcelUtil<CrmTransfer>(CrmTransfer.class);
        return util.exportExcel(list, "transfer");
    }

    /**
     * 新增转交记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存转交记录
     */
    @RequiresPermissions("crm:transfer:add")
    @Log(title = "转交记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmTransfer crmTransfer)
    {
        return toAjax(crmTransferService.insertCrmTransfer(crmTransfer));
    }

    /**
     * 修改转交记录
     */
    @GetMapping("/edit/{transferId}")
    public String edit(@PathVariable("transferId") Long transferId, ModelMap mmap)
    {
        CrmTransfer crmTransfer = crmTransferService.selectCrmTransferById(transferId);
        mmap.put("crmTransfer", crmTransfer);
        return prefix + "/edit";
    }

    /**
     * 修改保存转交记录
     */
    @RequiresPermissions("crm:transfer:edit")
    @Log(title = "转交记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmTransfer crmTransfer)
    {
        return toAjax(crmTransferService.updateCrmTransfer(crmTransfer));
    }

    /**
     * 删除转交记录
     */
    @RequiresPermissions("crm:transfer:remove")
    @Log(title = "转交记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmTransferService.deleteCrmTransferByIds(ids));
    }
    
    /**
     * 转为公共客户页面      填写原因
     */
    @GetMapping("/transferPublic")
    public String transferPublic(String ids,String businessType, ModelMap mmap)
    {
        mmap.put("ids", ids);
        mmap.put("businessType", businessType);
        return prefix + "/transferPublic";
    }
    
    /**
     * 转交为个人页面      填写原因
     */
    @GetMapping("/transferPerson")
    public String transferPerson(String ids,String businessType, ModelMap mmap)
    {
    	mmap.put("ids", ids);
        mmap.put("businessType", businessType);
        return prefix + "/transferPerson";
    }
    
    /**
     * 公客领取 页面
     */
    @GetMapping("/getPublic")
    public String getPublic(String ids,String businessType, ModelMap mmap)
    {
    	mmap.put("ids", ids);
        mmap.put("businessType", businessType);
        return prefix + "/getPublic";
    }
    
    /**
        * 转交给个人    
        * 转为公共
     */
    @Log(title = "转交记录", businessType = BusinessType.INSERT)
    @PostMapping("/transfer")
    @ResponseBody
    public AjaxResult transfer(String ids,String belongTo,String businessType,String reason)
    {
    	return toAjax(crmTransferService.transferByIds(businessType,ids,belongTo,reason,ShiroUtils.getLoginName()));
    }

    /**
     * 公共客户  公共线索     领取
     */
    @Log(title = "转交记录", businessType = BusinessType.INSERT)
    @PostMapping("/get")
    @ResponseBody
    public AjaxResult get(String ids,String businessType,String reason)
    {
    	return toAjax(crmTransferService.getByIds(businessType,ids,reason,ShiroUtils.getLoginName()));
    }
}
