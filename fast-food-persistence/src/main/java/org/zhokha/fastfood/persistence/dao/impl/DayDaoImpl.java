package org.zhokha.fastfood.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhokha.fastfood.persistence.dao.DayDao;
import org.zhokha.fastfood.persistence.model.Day;
import org.zhokha.fastfood.persistence.model.Resto;
import org.zhokha.fastfood.persistence.model.session.RestoDetails;
import org.zhokha.fastfood.persistence.model.session.UserDetails;

import java.util.Date;

/**
 * author: maks
 * date: 16.08.15
 */

@Repository
public class DayDaoImpl  implements DayDao {

    @Autowired
    private SessionFactory sessionFactory;
    private UserDetails userDetails;
    private RestoDetails restoDetails;

    @Override
    public Day create(Day day) {
        day.setResto((Resto) sessionFactory.getCurrentSession().get(Resto.class, 1));
        sessionFactory.getCurrentSession().save(day);
        return day;
    }

    @Override
    public Day update(Day day) {
        day.setResto((Resto) sessionFactory.getCurrentSession().get(Resto.class, 1));
        sessionFactory.getCurrentSession().update(day);
        return day;
    }

    @Override
    public Day loadById(int id) {
        return (Day) sessionFactory.getCurrentSession().load(Day.class, id);
    }

    @Override
    public Day getByDate(Date date) {
        return (Day) sessionFactory.getCurrentSession()
                .createCriteria(Day.class)
                .add(Restrictions.eq("resto.id", 1))
                .add(Restrictions.eq("date", date))
                .uniqueResult();
    }

    @Override
    public Day getLastExistedDay() {
        DetachedCriteria maxDate = DetachedCriteria.forClass(Day.class)
                .setProjection(Projections.max("date"));

        return (Day) sessionFactory.getCurrentSession()
                .createCriteria(Day.class)
                .add(Restrictions.eq("resto.id", 1))
                .add(Property.forName("date").eq(maxDate))
                .uniqueResult();
    }

}
