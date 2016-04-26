package org.zhokha.fastfood.service.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * author: maks
 * date: 02.08.15
 */
public class ProductAmountOperationDto {

    private AmountOperation operation;
    private int dayProductId;
    private BigDecimal amount = new BigDecimal(0);
    private BigDecimal sum;
    private Date date;

    public ProductAmountOperationDto() {
    }

    public AmountOperation getOperation() {
        return operation;
    }

    public void setOperation(AmountOperation operation) {
        this.operation = operation;
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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public enum AmountOperation {
        ADD,
        WRITEOFF
    }
}
