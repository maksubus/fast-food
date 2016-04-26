package org.zhokha.fastfood.persistence.dao;

import org.zhokha.fastfood.persistence.model.Product;

/**
 * author: maks
 * date: 25.07.15
 */

public interface ProductDao {

    Product getById(int id);
    Product create(Product product);
    Product update(Product product);
    int deleteById(int id);
}
