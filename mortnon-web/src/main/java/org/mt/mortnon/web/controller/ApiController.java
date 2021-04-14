package org.mt.mortnon.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * api工具
 *
 * @date 2021-04-13 22:23:10
 * @author dongfangzan
 */
@Controller
public class ApiController {

    /**
     * 跳转到api页面
     *
     * @return api页面地址
     * @page /api/debug-all.html
     */
    @GetMapping("/api")
    public String api() {
        return "redirect:/api/debug-all.html";
    }

    /**
     * 跳转到swagger页面
     *
     * @return swagger页面地址
     * @page /swagger-ui-custom.html
     */
    @GetMapping("/swagger")
    public String swagger() {
        return "redirect:/swagger-ui-custom.html";
    }
}
