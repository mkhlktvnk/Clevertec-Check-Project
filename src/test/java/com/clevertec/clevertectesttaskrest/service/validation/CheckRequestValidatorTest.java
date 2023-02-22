package com.clevertec.clevertectesttaskrest.service.validation;

import com.clevertec.clevertectesttaskrest.builder.impl.CheckRequestTestBuilder;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequest;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequestPosition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.clevertec.clevertectesttaskrest.builder.impl.CheckRequestTestBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

class CheckRequestValidatorTest {

    @Test
    void checkValidateCheckRequestShouldThrowIncorrectRequestExceptionWhenNotUniqueItemIsPresent() {
        CheckRequest checkRequest = aCheckRequest()
                .discountCardId(3L)
                .positions(List.of(
                        new CheckRequestPosition(1L, 2),
                        new CheckRequestPosition(1L, 5),
                        new CheckRequestPosition(2L, 6)
                ))
                .build();

        assertThrows(IncorrectRequestException.class, () -> CheckRequestValidator.validateCheckRequest(checkRequest));
    }

    @Test
    void checkValidateCheckRequestShouldThrowIncorrectRequestExceptionWhenPositionWithZeroQuantityIsPresent() {
        CheckRequest checkRequest = aCheckRequest()
                .discountCardId(3L)
                .positions(List.of(
                        new CheckRequestPosition(1L, 2),
                        new CheckRequestPosition(2L, 0)
                ))
                .build();

        assertThrows(IncorrectRequestException.class, () -> CheckRequestValidator.validateCheckRequest(checkRequest));
    }
}