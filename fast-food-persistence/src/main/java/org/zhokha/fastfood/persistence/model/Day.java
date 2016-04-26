package org.zhokha.fastfood.persistence.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * author: maks
 * date: 16.08.15
 */

@Entity
@Table(name = "day")
public class Day implements Model {

    private int id;
    private Date date;
    private BigDecimal revenue = new BigDecimal(0);
    private BigDecimal expense = new BigDecimal(0);
    private BigDecimal cookedAmount = new BigDecimal(0);
    private Set<DayProduct> dayProducts = new HashSet<DayProduct>();
    private Resto resto;

    public Day() {
    }

    public Day(Date date) {
        this.date = date;
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

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "revenue")
    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    @Column(name = "expense")
    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    @Column(name = "cooked_amount")
    public BigDecimal getCookedAmount() {
        return cookedAmount;
    }

    public void setCookedAmount(BigDecimal cookedAmount) {
        this.cookedAmount = cookedAmount;
    }

    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<DayProduct> getDayProducts() {
        return dayProducts;
    }

    public void setDayProducts(Set<DayProduct> dayProducts) {
        this.dayProducts = dayProducts;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "resto_id")
    public Resto getResto() {
        return resto;
    }

    public void setResto(Resto resto) {
        this.resto = resto;
    }

    public void addDayProduct(DayProduct dayProduct) {
        DayProduct newDayProduct = new DayProduct();
        newDayProduct.setDay(this);
        newDayProduct.setProduct(dayProduct.getProduct());
        newDayProduct.setCalcAmount(dayProduct.getFactAmount()
                        .add(dayProduct.getAddedAmount())
                        .subtract(dayProduct.getSubtrAmount())
        );

        dayProducts.add(newDayProduct);
    }

    public BigDecimal addRevenue(BigDecimal revenue) {
        this.revenue = this.revenue.add(revenue);
        return this.revenue;
    }
}
