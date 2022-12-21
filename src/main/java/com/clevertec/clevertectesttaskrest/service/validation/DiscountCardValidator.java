package com.clevertec.clevertectesttaskrest.service.validation;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.service.exception.ErrorMessage;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscountCardValidator {
    private static final Integer MIN_DISCOUNT = 1;
    private static final Integer MAX_DISCOUNT = 100;

    public void validateDiscountCard(DiscountCard discountCard) {
        validateDiscount(discountCard.getDiscount());
    }

    private void validateDiscount(Integer discount) {
        if (discount < MIN_DISCOUNT || discount > MAX_DISCOUNT) {
            throw new IncorrectRequestException(ErrorMessage.INVALID_DISCOUNT.getValue());
        }
    }
}
