package com.clevertec.clevertectesttaskrest.builder.impl;

import com.clevertec.clevertectesttaskrest.builder.TestDataBuilder;
import com.clevertec.clevertectesttaskrest.domain.DiscountCard;

public class DiscountCardTestBuilder implements TestDataBuilder<DiscountCard> {
    private Integer discount = 0;

    private DiscountCardTestBuilder() { }

    public DiscountCardTestBuilder discount(Integer discount) {
        this.discount = discount;
        return this;
    }

    public static DiscountCardTestBuilder aDiscountCard() {
        return new DiscountCardTestBuilder();
    }

    @Override
    public DiscountCard build() {
        return DiscountCard.builder()
                .discount(discount)
                .build();
    }
}
