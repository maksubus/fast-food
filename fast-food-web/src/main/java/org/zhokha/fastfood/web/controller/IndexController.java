package org.zhokha.fastfood.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: maks
 * Date:  19.07.15
 */

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String getMainPage() {
        return "index";
    }
}
