package com.clevertec.clevertectesttaskrest.service.validation;

import com.clevertec.clevertectesttaskrest.service.exception.ErrorMessage;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequest;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequestPosition;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@UtilityClass
public class CheckRequestValidator {
    public void validateCheckRequest(CheckRequest checkRequest) {
        validatePositionsIdUniqueness(checkRequest.getItems());
        validatePositionsQuantity(checkRequest.getItems());
    }

    private void validatePositionsIdUniqueness(List<CheckRequestPosition> positions) {
        if (positions.stream().filter(distinctByKey(CheckRequestPosition::getProductId)).count() != positions.size()) {
            throw new IncorrectRequestException(ErrorMessage.DUPLICATE_POSITIONS_ID.getValue());
        }
    }

    private void validatePositionsQuantity(List<CheckRequestPosition> positions) {
        if (positions.stream().anyMatch(p -> p.getQuantity() <= 0)) {
            throw new IncorrectRequestException(ErrorMessage.INCORRECT_QUANTITY.getValue());
        }
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
