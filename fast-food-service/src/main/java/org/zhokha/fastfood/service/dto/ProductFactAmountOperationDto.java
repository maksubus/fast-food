package org.zhokha.fastfood.service.dto;

import java.math.BigDecimal;

/**
 * author: maks
 * date: 09.08.15
 */
public class ProductFactAmountOperationDto {

    private int dayProductId;
    private BigDecimal amount = new BigDecimal(0);

    public ProductFactAmountOperationDto() {
    }

    public int getDayProductId() {
        return dayProductId;
    }

    public void setDayProductId(int dayProductId) {
        this.dayProductId = dayProductId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
