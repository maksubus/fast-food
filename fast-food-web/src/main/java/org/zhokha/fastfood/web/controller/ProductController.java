package org.zhokha.fastfood.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zhokha.fastfood.persistence.model.DayProduct;
import org.zhokha.fastfood.persistence.model.Product;
import org.zhokha.fastfood.service.DayService;
import org.zhokha.fastfood.service.ProductService;
import org.zhokha.fastfood.service.assembler.ProductAssembler;
import org.zhokha.fastfood.service.dto.ProductAmountOperationDto;
import org.zhokha.fastfood.service.dto.ProductDto;
import org.zhokha.fastfood.service.dto.ProductFactAmountOperationDto;

/**
 * author: maks
 * Date:  21.07.15
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    DayService dayService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductAssembler productAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        Product product = productAssembler.toModel(productDto);
        productDto.setId(dayService.createProduct(product, productDto.getDayId()).getId());

        return productDto;
    }

    @RequestMapping(value = "/update")
    public @ResponseBody
    ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productAssembler.toModel(productDto);
        productService.update(product);
        return productDto;
    }

    @RequestMapping(value = "/delete")
    public @ResponseBody String deleteProduct(@RequestBody ProductDto productDto) {
        int deletedItems = dayService.deleteDayProduct(productDto.getDayProductId());
        if (deletedItems != 1) {
            return "{'fail'}";
        }
        return "{\"success\": \"success\"}";
    }

    @RequestMapping(value = "/operateAmount")
    public @ResponseBody ProductDto operateAmount(@RequestBody ProductAmountOperationDto productAmountOperationDto) {
        DayProduct dayProduct = dayService.operateAddedOrSubtrAmount(productAmountOperationDto);
        ProductDto productDto = productAssembler.toDto(dayProduct);

        return productDto;
    }
    @RequestMapping(value = "/editByFact")
    public @ResponseBody ProductDto operateFactAmount(@RequestBody ProductFactAmountOperationDto productFactAmountOperationDto) {
        DayProduct dayProduct = dayService.operateProductFactAmount(productFactAmountOperationDto);
        ProductDto productDto = productAssembler.toDto(dayProduct);
        return productDto;
    }
}
