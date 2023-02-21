package com.clevertec.clevertectesttaskrest.service.validation;

import com.clevertec.clevertectesttaskrest.builder.impl.DiscountCardTestBuilder;
import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountCardValidatorTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 101, 110, 220, 2342, 532})
    void checkValidateDiscountCardShouldThrowIncorrectRequestExceptionWhenPriceIsInvalid(int discount) {
        DiscountCard discountCard = DiscountCardTestBuilder.aDiscountCard()
                .discount(discount)
                .build();

        assertThrows(IncorrectRequestException.class, () -> DiscountCardValidator.validateDiscountCard(discountCard));
    }
}