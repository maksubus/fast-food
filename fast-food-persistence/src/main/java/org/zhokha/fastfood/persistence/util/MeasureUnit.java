package org.zhokha.fastfood.persistence.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * author: maks
 * date: 08.08.15
 */

@JsonSerialize(using = MeasureUnitJsonSerializer.class)
@JsonDeserialize(using = MeasureUnitJsonDeserializer.class)
public enum MeasureUnit {

    KILOGRAM("кг"),
    GRAM("г"),
    LITER("л"),
    MILLILITER("мл"),
    ITEM("шт"),
    PACK("уп");

    private final String shortCode;

    MeasureUnit(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getShortCode() {
        return shortCode;
    }
}
