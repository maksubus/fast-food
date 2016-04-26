package org.zhokha.fastfood.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zhokha.fastfood.persistence.util.MeasureUnit;

/**
 * author: maks
 * date: 08.08.15
 */

@Controller
@RequestMapping("/measureUnit")
public class MeasureUnitController {

    @RequestMapping("/list")
    public @ResponseBody
    MeasureUnit[] getMeasureUnits() {
        return MeasureUnit.values();
    }
}
