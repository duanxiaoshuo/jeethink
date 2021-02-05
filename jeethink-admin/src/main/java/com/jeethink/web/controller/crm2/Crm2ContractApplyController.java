package com.jeethink.web.controller.crm2;

import com.jeethink.common.annotation.Log;
import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.domain.AjaxResult;
import com.jeethink.common.core.domain.AjaxResult.Type;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.common.enums.BusinessType;
import com.jeethink.common.utils.poi.ExcelUtil;
import com.jeethink.crm.domain.Crm2ContractApply;
import com.jeethink.crm.domain.CrmContractApply;
import com.jeethink.crm.service.crm1.ICrmContractApplyService;
import com.jeethink.crm.service.crm2.ICrm2ContractApplyService;
import com.jeethink.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合同申请Controller
 * 
 * @author jeethink
 * @date 2020-04-10
 */
@Controller
@RequestMapping("/crm2/contractApply")
public class Crm2ContractApplyController extends BaseController
{
    private String prefix = "crm2/contractApply";

    @Autowired
    private ICrm2ContractApplyService crmContractApplyService;

    @RequiresPermissions("crm:contractApply:view")
    @GetMapping()
    public String contractApply()
    {
        return prefix + "/contractApply";
    }

    /**
     * 查询合同申请列表
     */
    @RequiresPermissions("crm:contractApply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Crm2ContractApply crmContractApply)
    {
        startPage();
        List<Crm2ContractApply> list = crmContractApplyService.selectCrmContractApplyList(crmContractApply);
        return getDataTable(list);
    }

    /**
     * 导出合同申请列表
     */
    @RequiresPermissions("crm:contractApply:export")
    @Log(title = "合同申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Crm2ContractApply crmContractApply)
    {
        List<Crm2ContractApply> list = crmContractApplyService.selectCrmContractApplyList(crmContractApply);
        ExcelUtil<Crm2ContractApply> util = new ExcelUtil<Crm2ContractApply>(Crm2ContractApply.class);
        return util.exportExcel(list, "contractApply");
    }

    /**
     * 新增合同申请
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存合同申请
     */
    @RequiresPermissions("crm:contractApply:add")
    @Log(title = "合同申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CrmContractApply crmContractApply)
    {    	
    	String loginName=ShiroUtils.getLoginName();
    	crmContractApply.setDelFlag("0");
    	crmContractApply.setContractStatus("0");//已保存
    	crmContractApply.setCreateBy(loginName);
    	crmContractApply.setSourceBelongTo(loginName);
    	crmContractApply.setBelongTo(loginName);	
        return toAjax(crmContractApplyService.insertCrmContractApply(crmContractApply));
    }

    /**
     * 修改合同申请
     */
    @GetMapping("/edit/{contractId}")
    public String edit(@PathVariable("contractId") Long contractId, ModelMap mmap)
    {
        CrmContractApply crmContractApply = crmContractApplyService.selectCrmContractApplyById(contractId);
        mmap.put("crmContractApply", crmContractApply);
        return prefix + "/edit";
    }

    /**
     * 修改保存合同申请
     */
    @RequiresPermissions("crm:contractApply:edit")
    @Log(title = "合同申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CrmContractApply crmContractApply)
    {
    	crmContractApply.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractApplyService.updateCrmContractApply(crmContractApply));
    }

    /**
     * 删除合同申请
     */
    @RequiresPermissions("crm:contractApply:remove")
    @Log(title = "合同申请", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(crmContractApplyService.deleteCrmContractApplyByIds(ids));
    }
    /**
     * 合同申请  提交
     */
    @RequiresPermissions("crm:contractApply:submit")
    @Log(title = "合同申请", businessType = BusinessType.UPDATE)
    @PostMapping("/submit")
    @ResponseBody
    public AjaxResult submitSave(CrmContractApply crmContractApply)
    {
    	crmContractApply.setContractStatus("1");//已提交
    	crmContractApply.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractApplyService.updateCrmContractApply(crmContractApply));
    }
    
    /**
     * 合同申请  审核
     */
    @RequiresPermissions("crm:contractApply:audit")
    @Log(title = "合同申请", businessType = BusinessType.UPDATE)
    @PostMapping("/auditOk/{contractApplyId}")
    @ResponseBody
    public AjaxResult auditOkSave(@PathVariable("contractApplyId") Long contractApplyId)
    {
    	CrmContractApply crmContractApply = crmContractApplyService.selectCrmContractApplyById(contractApplyId);    	
    	crmContractApply.setContractStatus("2");//已审核
    	crmContractApply.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractApplyService.updateCrmContractApply(crmContractApply));
    }
    
    /**
     * 合同申请  审核
     */
    @RequiresPermissions("crm:contractApply:audit")
    @Log(title = "合同申请", businessType = BusinessType.UPDATE)
    @PostMapping("/auditNo/{contractApplyId}")
    @ResponseBody
    public AjaxResult auditNoSave(@PathVariable("contractApplyId") Long contractApplyId)
    {
    	CrmContractApply crmContractApply = crmContractApplyService.selectCrmContractApplyById(contractApplyId);    	
    	crmContractApply.setContractStatus("3");//已驳回    	
    	crmContractApply.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(crmContractApplyService.updateCrmContractApply(crmContractApply ));
    }
    
    /**
     * 合同申请  获取合同内容打印
     */
    @Log(title = "合同申请", businessType = BusinessType.OTHER)
    @PostMapping("/selectContractContent")
    @ResponseBody
    public AjaxResult selectContractContent(Long contractApplyId)
    {
    	CrmContractApply crmContractApply = crmContractApplyService.selectCrmContractApplyById(contractApplyId);    
    	Map<String,String> map= new HashMap<>();
    	map.put("contractContent",crmContractApply.getContractContent());
    	return new AjaxResult(Type.SUCCESS,"获取成功",map); 
    }
    
    /**
     * 合同申请  归档
     */
    @Log(title = "合同申请", businessType = BusinessType.UPDATE)
    @PostMapping("/file/{contractApplyId}")
    @ResponseBody
    public AjaxResult file(@PathVariable("contractApplyId")Long contractApplyId)
    {
        return toAjax(crmContractApplyService.fileCrmContractApplyById(contractApplyId,ShiroUtils.getLoginName()));
    }
    
}