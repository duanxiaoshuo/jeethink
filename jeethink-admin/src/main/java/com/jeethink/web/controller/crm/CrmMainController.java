package com.jeethink.web.controller.crm;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeethink.common.core.controller.BaseController;
import com.jeethink.common.core.page.TableDataInfo;
import com.jeethink.crm.domain.CrmClue;
import com.jeethink.crm.domain.CrmCustomer;
import com.jeethink.crm.domain.CrmOrder;
import com.jeethink.crm.domain.CrmOrderPrice;
import com.jeethink.crm.service.crm1.ICrmClueService;
import com.jeethink.crm.service.crm1.ICrmCustomerService;
import com.jeethink.crm.service.crm1.ICrmOrderPriceService;
import com.jeethink.crm.service.crm1.ICrmOrderService;


/**
 * 主页使用 Controller
 * 
 * @author jeethink
 * @date 2020-04-12
 */
@Controller
@RequestMapping("/crm/main")
public class CrmMainController extends BaseController {
	
	@Autowired
    private ICrmClueService crmClueService;
	
	@Autowired
    private ICrmCustomerService crmCustomerService;
	
	@Autowired
	private ICrmOrderPriceService crmOrderPriceService;
	
	@Autowired
    private ICrmOrderService crmOrderService;   	

    /**
     * 查询线索列表 (首页使用)
     */
    @PostMapping("/listClueTop5")
    @ResponseBody
    public TableDataInfo listClueTop5(CrmClue crmClue)
    {
    	Map<String, Object> params = new HashMap<String, Object>();  
    	params.put("top",5);  
    	crmClue.setParams(params);
        List<CrmClue> list = crmClueService.selectCrmClueList(crmClue);
        return getDataTable(list);
    }
    
    /**
     * 查询当月新增线索数量 (首页使用)
     */
    @GetMapping("/listClueMonthCount")
    @ResponseBody
    public int listClueMonthCount(CrmClue crmClue)
    {
    	Map<String, Object> params =getDateParam();   
    	crmClue.setParams(params);
    	List<CrmClue> list = crmClueService.selectCrmClueList(crmClue);
        return list.size();
    }
    
    /**
     * 查询客户列表(首页使用)
     */
    @PostMapping("/listCustomerTop5")
    @ResponseBody
    public TableDataInfo listCustomerTop5(CrmCustomer crmCustomer)
    {
    	Map<String, Object> params = new HashMap<String, Object>();  
    	params.put("top",5);  
    	crmCustomer.setParams(params);
        List<CrmCustomer> list = crmCustomerService.selectCrmCustomerListMy(crmCustomer);
        return getDataTable(list);
    }
    
    /**
     * 查询当月新增客户数量(首页使用)
     */
    @GetMapping("/listCustomerMonthCount")
    @ResponseBody
    public int listCustomerMonthCount(CrmCustomer crmCustomer)
    {
    	Map<String, Object> params =getDateParam();   
    	crmCustomer.setParams(params);
        List<CrmCustomer> list = crmCustomerService.selectCrmCustomerListMy(crmCustomer);
        return list.size();
    }

	/**
     * 查询客户报价单列表 (首页使用)
     */
    @PostMapping("/listOrderPriceTop5")
    @ResponseBody
    public TableDataInfo listOrderPriceTop5(CrmOrderPrice crmOrderPrice)
    {
    	Map<String, Object> params = new HashMap<String, Object>();  
    	params.put("top",5);  
    	crmOrderPrice.setParams(params);
        List<CrmOrderPrice> list = crmOrderPriceService.selectCrmOrderPriceList(crmOrderPrice);
        return getDataTable(list);
    }
    
    /**
     * 查询当月新增客户报价单数量 (首页使用)
     */
    @GetMapping("/listOrderPriceMonthCount")
    @ResponseBody
    public int listOrderPriceMonthCount(CrmOrderPrice crmOrderPrice)
    {
    	Map<String, Object> params =getDateParam();   
    	crmOrderPrice.setParams(params);
    	List<CrmOrderPrice> list = crmOrderPriceService.selectCrmOrderPriceList(crmOrderPrice);
        return list.size();
    }
    
    /**
     * 查询客户订单列表 (首页使用)
     */
    @PostMapping("/listOrderTop5")
    @ResponseBody
    public TableDataInfo listOrderTop5(CrmOrder crmOrder)
    {
    	Map<String, Object> params = new HashMap<String, Object>();  
    	params.put("top",5);  
    	crmOrder.setParams(params);
        List<CrmOrder> list = crmOrderService.selectCrmOrderList(crmOrder);
        return getDataTable(list);
    }
    
    /**
     * 查询当月新增客户订单数量 (首页使用)
     */
    @GetMapping("/listOrderMonthCount")
    @ResponseBody
    public int listOrderMonthCount(CrmOrder crmOrder)
    {
    	Map<String, Object> params =getDateParam();    	
    	crmOrder.setParams(params);
    	List<CrmOrder> list = crmOrderService.selectCrmOrderList(crmOrder);
        return list.size();
    }
    
    /**
     * 获取当月日期参数
     * @return
     */
    public Map<String, Object> getDateParam()
    {
    	Map<String, Object> params = new HashMap<String, Object>();      	
    	Calendar c = Calendar.getInstance();
    	int year = c.get(Calendar.YEAR);  
    	int month = c.get(Calendar.MONTH)+1;  
    	int last = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    	params.put("beginTime",year+"-"+month+"-01");  
    	params.put("endTime", year+"-"+month+"-"+last); 
    	return params;
    }
}
