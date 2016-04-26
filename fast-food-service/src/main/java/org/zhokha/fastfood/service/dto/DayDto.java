package org.zhokha.fastfood.service.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * author: maks
 * date: 16.08.15
 */
public class DayDto implements Dto {

    private int id;
    private Date date;
    private BigDecimal revenue;
    private BigDecimal cookedAmount;
    private Set<ProductDto> products = new HashSet<ProductDto>();
    private String restoName;

    public DayDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getCookedAmount() {
        return cookedAmount;
    }

    public void setCookedAmount(BigDecimal cookedAmount) {
        this.cookedAmount = cookedAmount;
    }

    public Set<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDto> products) {
        this.products = products;
    }

    public String getRestoName() {
        return restoName;
    }

    public void setRestoName(String restoName) {
        this.restoName = restoName;
    }
}
