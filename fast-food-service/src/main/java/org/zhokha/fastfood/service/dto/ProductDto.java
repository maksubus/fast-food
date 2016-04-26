package org.zhokha.fastfood.service.dto;

import org.zhokha.fastfood.persistence.util.MeasureUnit;

import java.math.BigDecimal;

/**
 * author: maks
 * Date: 21.07.2015
 */
public class ProductDto implements Dto {

    private int id;
    private String name;
    private MeasureUnit measureUnit;
    private BigDecimal calcAmount = new BigDecimal(0);
    private BigDecimal factAmount = new BigDecimal(0);
    private BigDecimal addedAmount = new BigDecimal(0);
    private BigDecimal subtrAmount = new BigDecimal(0);

    /**
     * used for creating product
     * when day id is known it is not needed to hit database to link new product and day
     */
    private int dayId;
    private int dayProductId;

    public ProductDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public BigDecimal getCalcAmount() {
        return calcAmount;
    }

    public void setCalcAmount(BigDecimal calcAmount) {
        this.calcAmount = calcAmount;
    }

    public BigDecimal getFactAmount() {
        return factAmount;
    }

    public void setFactAmount(BigDecimal factAmount) {
        this.factAmount = factAmount;
    }

    public BigDecimal getAddedAmount() {
        return addedAmount;
    }

    public void setAddedAmount(BigDecimal addedAmount) {
        this.addedAmount = addedAmount;
    }

    public BigDecimal getSubtrAmount() {
        return subtrAmount;
    }

    public void setSubtrAmount(BigDecimal subtrAmount) {
        this.subtrAmount = subtrAmount;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public int getDayProductId() {
        return dayProductId;
    }

    public void setDayProductId(int dayProductId) {
        this.dayProductId = dayProductId;
    }
}
