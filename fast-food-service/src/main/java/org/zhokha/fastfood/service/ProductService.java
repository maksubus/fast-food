package org.zhokha.fastfood.service;

import org.springframework.transaction.annotation.Transactional;
import org.zhokha.fastfood.persistence.model.Product;

/**
 * author: maks
 * date: 16.08.15
 */

@Transactional
public interface ProductService {

    Product update(Product product);
}
