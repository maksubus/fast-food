package org.zhokha.fastfood.service.dto;

import java.math.BigDecimal;

/**
 * author: maks
 * date: 19.08.15
 */
public class AddRevenueOperationDto {

    private int dayId;
    private BigDecimal revenue;

    public AddRevenueOperationDto() {
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
}
