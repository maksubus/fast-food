package org.zhokha.fastfood.persistence.dao;

import org.zhokha.fastfood.persistence.model.Day;

import java.util.Date;

/**
 * author: maks
 * date: 16.08.15
 */

public interface DayDao {

    Day create(Day day);
    Day update(Day day);
    Day loadById(int id);
    Day getByDate(Date date);
    Day getLastExistedDay();
}
