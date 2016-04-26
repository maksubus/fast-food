package org.zhokha.fastfood.persistence.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * author: maks
 * date: 16.08.15
 */

@Entity
@Table(name = "day_product")
public class DayProduct  implements Model {

    private int id;
    private Day day;
    private Product product;
    private BigDecimal calcAmount = new BigDecimal(0);
    private BigDecimal factAmount = new BigDecimal(0);
    private BigDecimal addedAmount = new BigDecimal(0);
    private BigDecimal subtrAmount = new BigDecimal(0);

    public DayProduct() {
    }

    public DayProduct(Day day, Product product) {
        this.day = day;
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id")
    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "calc_amount")
    public BigDecimal getCalcAmount() {
        return calcAmount;
    }

    public void setCalcAmount(BigDecimal calcAmount) {
        this.calcAmount = calcAmount;
    }

    @Column(name = "fact_amount")
    public BigDecimal getFactAmount() {
        return factAmount;
    }

    public void setFactAmount(BigDecimal factAmount) {
        this.factAmount = factAmount;
    }

    @Column(name = "added_amount")
    public BigDecimal getAddedAmount() {
        return addedAmount;
    }

    public void setAddedAmount(BigDecimal addedAmount) {
        this.addedAmount = addedAmount;
    }

    @Column(name = "subtr_amount")
    public BigDecimal getSubtrAmount() {
        return subtrAmount;
    }

    public void setSubtrAmount(BigDecimal subtrAmount) {
        this.subtrAmount = subtrAmount;
    }

    public void addAmount(BigDecimal amount) {
        this.addedAmount = this.addedAmount.add(amount);
    }

    public void subtractAmount(BigDecimal amount) {
        this.subtrAmount = this.subtrAmount.add(amount);
    }
}
