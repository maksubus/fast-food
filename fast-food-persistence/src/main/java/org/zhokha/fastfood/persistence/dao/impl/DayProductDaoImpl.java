package org.zhokha.fastfood.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhokha.fastfood.persistence.dao.DayProductDao;
import org.zhokha.fastfood.persistence.model.DayProduct;

/**
 * author: maks
 * date: 21.08.15
 */

@Repository
public class DayProductDaoImpl implements DayProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int deleteById(int id) {
        return sessionFactory.getCurrentSession()
                .createSQLQuery("delete from day_product where id = :id")
                .setInteger("id", id)
                .executeUpdate();
    }

    @Override
    public DayProduct loadById(int id) {
        return (DayProduct) sessionFactory.getCurrentSession().load(DayProduct.class, id);
    }
}
