package com.clevertec.clevertectesttaskrest.converter;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.web.model.DiscountCardModel;
import com.clevertec.clevertectesttaskrest.web.model.converter.DiscountCardMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiscountCardMapperTest {
    private final DiscountCardMapper discountCardMapper = DiscountCardMapper.INSTANCE;

    @Test
    public void shouldConvertToDiscountCard() {
        DiscountCardModel discountCardModel = new DiscountCardModel();
        discountCardModel.setId(1L);
        discountCardModel.setDiscount(20);
        DiscountCard discountCard = discountCardMapper.toEntity(discountCardModel);

        Assertions.assertEquals(discountCardModel.getId(), discountCard.getId());
        Assertions.assertEquals(discountCardModel.getDiscount(), discountCard.getDiscount());
        Assertions.assertNull(discountCard.getChecks());
    }
}
