package org.zhokha.fastfood.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhokha.fastfood.persistence.dao.DayDao;
import org.zhokha.fastfood.persistence.dao.DayProductDao;
import org.zhokha.fastfood.persistence.dao.ProductDao;
import org.zhokha.fastfood.persistence.model.Day;
import org.zhokha.fastfood.persistence.model.DayProduct;
import org.zhokha.fastfood.persistence.model.Product;
import org.zhokha.fastfood.service.DayService;
import org.zhokha.fastfood.service.dto.CookedAmountOperationDto;
import org.zhokha.fastfood.service.dto.ProductAmountOperationDto;
import org.zhokha.fastfood.service.dto.ProductFactAmountOperationDto;
import org.zhokha.fastfood.service.exception.BusinessLogicException;

import java.math.BigDecimal;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * author: maks
 * date: 16.08.15
 */

@Service
public class DayServiceImpl implements DayService {

    @Autowired
    DayDao dayDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    DayProductDao dayProductDao;

    @Override
    public Day getToday() {
        //get today
        DateTime todayDT = new DateTime().toDateMidnight().toDateTime();
        Day today = dayDao.getByDate(todayDT.toDate());

        //if null then create it
        if (today == null) {
            today = new Day(todayDT.toDate());

            //if it's not first time of app use then copy all products from last existed day
            Day lastExistedDay = dayDao.getLastExistedDay();
            if (lastExistedDay != null) {
                for (DayProduct dayProduct : lastExistedDay.getDayProducts()) {
                    today.addDayProduct(dayProduct);
                }
            }


            dayDao.create(today);
        }

        return today;
    }

    @Override
    public Day getDayByDate(Date date) throws BusinessLogicException {
        checkNotNull(date, "Date must not be null");

        Day day = dayDao.getByDate(date);

        if (day == null) {
            throw new BusinessLogicException("There is no Day with specified date");
        }

        return day;
    }

    @Override
    public Product createProduct(Product product, int dayId) {
        Day day = dayDao.loadById(dayId);
        day.getDayProducts().add(new DayProduct(day, product));

        return product;
    }

    @Override
    public int deleteDayProduct(int dayProductId) {
        return dayProductDao.deleteById(dayProductId);
    }

    @Override
    public DayProduct operateAddedOrSubtrAmount(ProductAmountOperationDto productAmountOperationDto) {
        DayProduct dayProduct = dayProductDao.loadById(productAmountOperationDto.getDayProductId());

        if (ProductAmountOperationDto.AmountOperation.ADD.equals(productAmountOperationDto.getOperation())) {
            dayProduct.addAmount(productAmountOperationDto.getAmount());
        } else {
            dayProduct.subtractAmount(productAmountOperationDto.getAmount());
        }

        return dayProduct;
    }

    @Override
    public DayProduct operateProductFactAmount(ProductFactAmountOperationDto productFactAmountOperationDto) {
        DayProduct dayProduct = dayProductDao.loadById(productFactAmountOperationDto.getDayProductId());
        dayProduct.setFactAmount(productFactAmountOperationDto.getAmount());

        return dayProduct;
    }

    @Override
    public CookedAmountOperationDto addCookedAmount(CookedAmountOperationDto cookedAmountOperationDto) {
        Day day = dayDao.getByDate(cookedAmountOperationDto.getDate());
        day.setCookedAmount(cookedAmountOperationDto.getAmount());

        Date yesterdayDate = new DateTime(cookedAmountOperationDto.getDate()).minusDays(1).toDate();
        Day yesterday = dayDao.getByDate(yesterdayDate);

        for (DayProduct yDayProduct: yesterday.getDayProducts()) {
            for (DayProduct tDayProduct: day.getDayProducts()) {
                //todo: add some check if formula_amount is not null
                if (yDayProduct.getProduct().getId() == tDayProduct.getProduct().getId()) {
                    BigDecimal calculatedAmount = yDayProduct.getFactAmount()
                            .add(yDayProduct.getAddedAmount())
                            .subtract(yDayProduct.getSubtrAmount())
                            .subtract(yDayProduct.getProduct().getFormulaAmount().multiply(cookedAmountOperationDto.getAmount()));

                    tDayProduct.setCalcAmount(calculatedAmount);
                }
            }
        }

            return cookedAmountOperationDto;
    }

    @Override
    //todo: delete this method when delete usage from controller
    public void updateDay(Day day) {
        dayDao.update(day);
    }
}
