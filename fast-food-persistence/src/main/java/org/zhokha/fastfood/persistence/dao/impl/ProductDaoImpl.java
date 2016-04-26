package org.zhokha.fastfood.persistence.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zhokha.fastfood.persistence.dao.ProductDao;
import org.zhokha.fastfood.persistence.model.Product;
import org.zhokha.fastfood.persistence.model.Resto;
import org.zhokha.fastfood.persistence.model.session.RestoDetails;
import org.zhokha.fastfood.persistence.model.session.UserDetails;

import java.util.List;

/**
 * author: maks
 * date: 25.07.15
 */

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;
    private UserDetails userDetails;
    private RestoDetails restoDetails;

    public ProductDaoImpl() {
    }

    public ProductDaoImpl(SessionFactory sessionFactory,
                          UserDetails userDetails,
                          RestoDetails restoDetails) {
        this.sessionFactory = sessionFactory;
        this.userDetails = userDetails;
        this.restoDetails = restoDetails;
    }

    @Override
    public Product getById(int id) {
        return (Product) sessionFactory.getCurrentSession()
                .createQuery("from Product where id = :id")
                .setInteger("id", id)
                .uniqueResult();
    }

    @Override
    public Product create(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        sessionFactory.getCurrentSession().update(product);
        return product;
    }

    @Override
    public int deleteById(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("delete from Product where id = :id")
                .setInteger("id", id)
                .executeUpdate();
    }
}
