package org.zhokha.fastfood.web.controller;

import org.zhokha.fastfood.persistence.util.MeasureUnit;

import java.util.Arrays;
import java.util.List;

/**
 * author: maks
 * date: 08.08.15
 */
public class EnumMain {

    public static void main(String[] args) {
        MeasureUnit[] measureUnits = MeasureUnit.values();
        List<MeasureUnit> measureUnitList = Arrays.asList(measureUnits);
        System.out.println(measureUnits);
        System.out.print(measureUnitList);

    }
}
