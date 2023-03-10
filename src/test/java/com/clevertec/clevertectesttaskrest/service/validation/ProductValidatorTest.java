package com.clevertec.clevertectesttaskrest.service.validation;

import com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder;
import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductValidatorTest {
    @Test
    void checkValidateProductShouldThrowIncorrectRequestExceptionWhenNameIsInvalid() {
        Product product = aProduct()
                .name("")
                .price(BigDecimal.valueOf(99.99))
                .build();

        assertThrows(IncorrectRequestException.class, () -> ProductValidator.validateProduct(product));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.00, 0.000, 999.9999, 12345.12345, 99999999999999999999999.99, -1.0, -324})
    void checkValidateProductShouldThrowIncorrectRequestExceptionWhenPriceIsInvalid(double price) {
        Product product = aProduct()
                .name("")
                .price(BigDecimal.valueOf(price))
                .build();

        assertThrows(IncorrectRequestException.class, () -> ProductValidator.validateProduct(product));
    }
}