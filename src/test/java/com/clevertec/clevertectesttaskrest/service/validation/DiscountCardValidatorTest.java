package com.clevertec.clevertectesttaskrest.service.validation;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountCardValidatorTest {
    @Test
    void validateDiscountCard_shouldThrowIncorrectRequestException_whenDiscountIsInvalid() {
        DiscountCard discountCard = DiscountCard.builder()
                .discount(-100)
                .build();
        Assertions.assertThrows(IncorrectRequestException.class, () -> {
            DiscountCardValidator.validateDiscountCard(discountCard);
        });
    }
}