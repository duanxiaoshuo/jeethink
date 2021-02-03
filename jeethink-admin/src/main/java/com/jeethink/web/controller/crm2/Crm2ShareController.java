package com.jeethink.web.controller.crm2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 共享Controller
 *
 * @author duan
 * @date 2021-01-30
 */
@Controller
@RequestMapping("/crm2/share")
public class Crm2ShareController {
    private String prefix = "/crm2/share";

    /**
     * 转交为个人页面      填写原因
     */
    @GetMapping("/sharePerson")
    public String transferPerson(String customerId, String isShare , ModelMap mmap) {
        mmap.put("customerId", customerId);
        mmap.put("isShare", isShare);

        return prefix + "/sharePerson";
    }
}
