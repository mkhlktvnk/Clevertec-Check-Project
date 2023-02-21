package com.clevertec.clevertectesttaskrest.web.model.converter;

import com.clevertec.clevertectesttaskrest.arguments.CheckArgumentsProvider;
import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.web.model.CheckModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.*;

class CheckMapperTest {
    private CheckMapper mapper = CheckMapper.INSTANCE;

    @ParameterizedTest
    @ArgumentsSource(CheckArgumentsProvider.class)
    void toModel(Check check) {
        CheckModel model = mapper.toModel(check);
        assertAll(
                () -> assertEquals(model.getId(), check.getId()),
                () -> assertEquals(model.getTotalPrice(), check.getTotalPrice())
        );
    }
}