package com.clevertec.clevertectesttaskrest.service.validation;

import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductValidatorTest {
    @Test
    void validateProduct_shouldThrowIncorrectRequestException_whenPriceIsNotValid() {
        Product product = Product.builder()
                .name("name")
                .price(BigDecimal.valueOf(999.999))
                .promotional(true)
                .build();
        Assertions.assertThrows(IncorrectRequestException.class, () -> {
            ProductValidator.validateProduct(product);
        });
    }

    @Test
    void validateProduct_shouldThrowIncorrectRequestException_whenNameLengthIsNotValid() {
        Product product = Product.builder()
                .name("")
                .price(BigDecimal.valueOf(999.999))
                .promotional(true)
                .build();
        Assertions.assertThrows(IncorrectRequestException.class, () -> {
            ProductValidator.validateProduct(product);
        });
    }
}