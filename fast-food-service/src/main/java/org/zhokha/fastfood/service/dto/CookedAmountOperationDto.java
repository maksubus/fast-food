package org.zhokha.fastfood.service.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by maks on 24.08.15.
 */
public class CookedAmountOperationDto {

    private Date date;
    private int dayId;
    private BigDecimal amount;

    public CookedAmountOperationDto() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
