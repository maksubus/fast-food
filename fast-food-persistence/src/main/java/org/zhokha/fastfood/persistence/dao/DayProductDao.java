package org.zhokha.fastfood.persistence.dao;

import org.zhokha.fastfood.persistence.model.DayProduct;

/**
 * author: maks
 * date: 21.08.15
 */
public interface DayProductDao {

    int deleteById(int id);
    DayProduct loadById(int id);
}
