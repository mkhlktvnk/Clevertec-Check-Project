package com.clevertec.clevertectesttaskrest.builder.impl;

import com.clevertec.clevertectesttaskrest.builder.TestDataBuilder;
import com.clevertec.clevertectesttaskrest.domain.DiscountCard;

public class DiscountCardTestBuilder implements TestDataBuilder<DiscountCard> {
    private Long id = 0L;
    private Integer discount = 0;

    private DiscountCardTestBuilder() { }

    public DiscountCardTestBuilder id(Long id) {
        this.id = id;
        return this;
    }

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
                .id(id)
                .discount(discount)
                .build();
    }
}
