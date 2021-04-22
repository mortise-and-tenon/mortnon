package org.mt.mortnon.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 *
 * @author dongfangzan
 * @date 22.4.21 10:07 上午
 */
@Controller
public class HomeController {


    /**
     * 跳转到mortnon首页
     *
     * @return mortnon首页
     * @page /index/index.html
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/index/index.html";
    }
}
