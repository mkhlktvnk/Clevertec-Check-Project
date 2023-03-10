package com.clevertec.clevertectesttaskrest.web.model.converter;

import com.clevertec.clevertectesttaskrest.arguments.DiscountCardArgumentsProvider;
import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.web.model.DiscountCardModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

class DiscountCardMapperTest {
    private final DiscountCardMapper mapper = DiscountCardMapper.INSTANCE;

    @ParameterizedTest
    @ArgumentsSource(DiscountCardArgumentsProvider.class)
    void toModel(DiscountCard discountCard) {
        DiscountCardModel model = mapper.toModel(discountCard);
        assertAll(
                () -> assertThat(model.getId()).isEqualTo(discountCard.getId()),
                () -> assertThat(model.getDiscount()).isEqualTo(discountCard.getDiscount())
        );
    }
}