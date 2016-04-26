package org.zhokha.fastfood.service.assembler;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.zhokha.fastfood.persistence.model.DayProduct;
import org.zhokha.fastfood.persistence.model.Product;
import org.zhokha.fastfood.service.dto.ProductDto;

/**
 * author: maks
 * date: 16.08.15
 */

@Component
public class ProductAssembler {

    public ProductDto toDto(DayProduct dayProduct) {
        ProductDto productDto = new ProductDto();
        return toDto(dayProduct, productDto);
    }

    public ProductDto toDto(DayProduct dayProduct, ProductDto productDto) {
        productDto.setId(dayProduct.getProduct().getId());
        productDto.setDayProductId(dayProduct.getId());

        productDto.setName(dayProduct.getProduct().getName());
        productDto.setMeasureUnit(dayProduct.getProduct().getMeasureUnit());

        productDto.setCalcAmount(dayProduct.getCalcAmount());
        productDto.setFactAmount(dayProduct.getFactAmount());
        productDto.setAddedAmount(dayProduct.getAddedAmount());
        productDto.setSubtrAmount(dayProduct.getSubtrAmount());

        return productDto;
    }

    public Product toModel(ProductDto productDto) {
        Product product = new Product();
        return toModel(productDto, product);
    }

    public Product toModel(ProductDto productDto, Product product) {
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
