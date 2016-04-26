package org.zhokha.fastfood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhokha.fastfood.persistence.dao.ProductDao;
import org.zhokha.fastfood.persistence.model.Product;
import org.zhokha.fastfood.service.ProductService;

/**
 * author: maks
 * date: 17.08.15
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }
}
