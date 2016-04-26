package org.zhokha.fastfood.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhokha.fastfood.persistence.model.Day;
import org.zhokha.fastfood.service.DayService;
import org.zhokha.fastfood.service.dto.CookedAmountOperationDto;
import org.zhokha.fastfood.service.exception.BusinessLogicException;
import org.zhokha.fastfood.service.assembler.DayAssembler;
import org.zhokha.fastfood.service.dto.AddRevenueOperationDto;
import org.zhokha.fastfood.service.dto.DayDto;

import java.util.Date;

/**
 * author: maks
 * date: 16.08.15
 */

@Controller
@RequestMapping("/day")
public class DayController {

    @Autowired
    DayService dayService;

    @Autowired
    DayAssembler dayAssembler;

    @RequestMapping(value = "/today")
    public @ResponseBody
    DayDto getToday() {
        Day today = dayService.getToday();
        DayDto todayDto = dayAssembler.toDto(today);

        return todayDto;
    }

    @RequestMapping(value = "/by-date")
    public @ResponseBody
    DayDto getDay(@RequestParam(value = "date") @DateTimeFormat(pattern="dd-MM-yyyy") Date date)
            throws BusinessLogicException {

        Day day = dayService.getDayByDate(date);
        DayDto dayDto = dayAssembler.toDto(day);

        return dayDto;
    }

    @RequestMapping(value = "/add-revenue")
    public @ResponseBody
    AddRevenueOperationDto addRevenueForToday(@RequestBody AddRevenueOperationDto addRevenueOperationDto) {
        //TODO: avoid logic in controller
        Day day = dayService.getToday();
        day.addRevenue(addRevenueOperationDto.getRevenue());
        dayService.updateDay(day); //update queries for all 3 objects (day, dayProduct, product)

        addRevenueOperationDto.setRevenue(day.getRevenue());

        return addRevenueOperationDto;
    }

    @RequestMapping(value = "/add-cooked-amount")
    public @ResponseBody
    CookedAmountOperationDto addCookedAmount(@RequestBody CookedAmountOperationDto cookedAmountOperationDto) {
        cookedAmountOperationDto = dayService.addCookedAmount(cookedAmountOperationDto);

        return cookedAmountOperationDto;
    }
}
