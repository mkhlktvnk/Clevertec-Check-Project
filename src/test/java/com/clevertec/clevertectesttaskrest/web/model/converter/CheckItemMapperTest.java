package com.clevertec.clevertectesttaskrest.web.model.converter;

import com.clevertec.clevertectesttaskrest.arguments.CheckItemArgumentsProvider;
import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.web.model.CheckItemModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckItemMapperTest {
    private final CheckItemMapper mapper = CheckItemMapper.INSTANCE;

    @ParameterizedTest
    @ArgumentsSource(CheckItemArgumentsProvider.class)
    void toModelShouldConvert(CheckItem checkItem) {
        CheckItemModel model = mapper.toModel(checkItem);
        assertAll(
                () -> assertEquals(model.getId(), checkItem.getId()),
                () -> assertAll(
                        () -> assertEquals(model.getProduct().getId(), checkItem.getProduct().getId()),
                        () -> assertEquals(model.getProduct().getName(), checkItem.getProduct().getName()),
                        () -> assertEquals(model.getProduct().getPrice(), checkItem.getProduct().getPrice())
                ),
                () -> assertEquals(model.getQuantity(), checkItem.getQuantity()),
                () -> assertEquals(model.getTotalPrice(), checkItem.getTotalPrice())
        );
    }

}