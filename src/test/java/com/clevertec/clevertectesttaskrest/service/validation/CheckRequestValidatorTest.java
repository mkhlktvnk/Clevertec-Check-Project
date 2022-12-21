package com.clevertec.clevertectesttaskrest.service.validation;

import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequest;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequestPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CheckRequestValidatorTest {

    @Test
    void validateCheckRequest_shouldThrowIncorrectRequestException_whenProductIdIsNotUnique() {
        CheckRequest checkRequest = new CheckRequest();
        List<CheckRequestPosition> positions = List.of(
                new CheckRequestPosition(1L, 10),
                new CheckRequestPosition(1L, 15)
        );
        checkRequest.setItems(positions);
        Assertions.assertThrows(IncorrectRequestException.class, () -> {
            CheckRequestValidator.validateCheckRequest(checkRequest);
        });
    }
}