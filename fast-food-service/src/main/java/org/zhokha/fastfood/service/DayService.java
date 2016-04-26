package org.zhokha.fastfood.service;

import org.springframework.transaction.annotation.Transactional;
import org.zhokha.fastfood.persistence.model.Day;
import org.zhokha.fastfood.persistence.model.DayProduct;
import org.zhokha.fastfood.persistence.model.Product;
import org.zhokha.fastfood.service.dto.CookedAmountOperationDto;
import org.zhokha.fastfood.service.dto.ProductAmountOperationDto;
import org.zhokha.fastfood.service.dto.ProductFactAmountOperationDto;
import org.zhokha.fastfood.service.exception.BusinessLogicException;

import java.util.Date;

/**
 * author: maks
 * date: 16.08.15
 */

@Transactional
public interface DayService {

    Day getToday();
    Day getDayByDate(Date date) throws BusinessLogicException;
    Product createProduct(Product product, int dayId);
    void updateDay(Day day);
    int deleteDayProduct(int dayProductId);
    DayProduct operateAddedOrSubtrAmount(ProductAmountOperationDto productAmountOperationDto);
    DayProduct operateProductFactAmount(ProductFactAmountOperationDto productFactAmountOperationDto);
    CookedAmountOperationDto addCookedAmount(CookedAmountOperationDto cookedAmountOperationDto);
}
