package com.clevertec.clevertectesttaskrest.web.model.converter;

import com.clevertec.clevertectesttaskrest.arguments.CheckItemArgumentsProvider;
import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.web.model.CheckItemModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CheckItemMapperTest {
    private final CheckItemMapper mapper = CheckItemMapper.INSTANCE;

    @ParameterizedTest
    @ArgumentsSource(CheckItemArgumentsProvider.class)
    void toModelShouldConvert(CheckItem checkItem) {
        CheckItemModel model = mapper.toModel(checkItem);
        assertAll(
                () -> assertThat(model.getId())
                        .isEqualTo(checkItem.getId()),
                () -> assertAll(
                        () -> assertThat(model.getProduct().getId()).isEqualTo(checkItem.getProduct().getId()),
                        () -> assertThat(model.getProduct().getName()).isEqualTo(checkItem.getProduct().getName()),
                        () -> assertThat(model.getProduct().getPrice()).isEqualTo(checkItem.getProduct().getPrice())
                ),
                () -> assertThat(model.getQuantity()).isEqualTo(checkItem.getQuantity()),
                () -> assertThat(model.getTotalPrice()).isEqualTo(checkItem.getTotalPrice())
        );
    }

}