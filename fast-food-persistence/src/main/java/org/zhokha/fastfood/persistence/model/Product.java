package org.zhokha.fastfood.persistence.model;

import org.zhokha.fastfood.persistence.util.MeasureUnit;
import org.zhokha.fastfood.persistence.util.Status;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * author: maks
 * date: 16.08.15
 */

@Entity
@Table(name = "product")
public class Product implements Model {

    private int id;
    private String name;
    private Status status = Status.ACTIVE;
    private MeasureUnit measureUnit;
    private BigDecimal formulaAmount;

    public Product() {
    }

    public Product(int id, String name, Status status, MeasureUnit measureUnit) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.measureUnit = measureUnit;
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

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "measure_unit", nullable = false)
    @Enumerated(EnumType.STRING)
    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    @Column(name = "formula_amount", scale = 3)
    public BigDecimal getFormulaAmount() {
        return formulaAmount;
    }

    public void setFormulaAmount(BigDecimal formulaAmount) {
        this.formulaAmount = formulaAmount;
    }
}
