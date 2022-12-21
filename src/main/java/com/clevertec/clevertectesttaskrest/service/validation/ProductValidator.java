package com.clevertec.clevertectesttaskrest.service.validation;

import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.service.exception.ErrorMessage;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class ProductValidator {
    private final BigDecimal MIN_PRICE = new BigDecimal("0.01");
    private final BigDecimal MAX_PRICE = new BigDecimal("9999999999999999999999.99");
    private final Integer MAX_NAME_LENGTH = 255;
    private final Integer MAX_SCALE = 2;

    public void validateProduct(Product product) {
        validateName(product.getName());
        validatePrice(product.getPrice());
    }

    private void validateName(String name) {
        if (name.length() > MAX_NAME_LENGTH || name.length() == 0) {
            throw new IncorrectRequestException(ErrorMessage.INVALID_PRODUCT_NAME_LENGTH.getValue());
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price.compareTo(MIN_PRICE) < 0 || price.compareTo(MAX_PRICE) > 0 || price.scale() > MAX_SCALE) {
            throw new IncorrectRequestException(ErrorMessage.INVALID_PRODUCT_PRICE.getValue());
        }
    }
}
