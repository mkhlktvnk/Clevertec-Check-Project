package com.clevertec.clevertectesttaskrest.builder.impl;

import com.clevertec.clevertectesttaskrest.builder.TestDataBuilder;
import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.domain.DiscountCard;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CheckTestBuilder implements TestDataBuilder<Check> {
    private Long id = 0L;
    private BigDecimal totalPrice = BigDecimal.valueOf(0.00);
    private Set<CheckItem> checkItems = new HashSet<>();
    private DiscountCard discountCard = DiscountCardTestBuilder.aDiscountCard().build();

    private CheckTestBuilder() { }

    public static CheckTestBuilder aCheck() {
        return new CheckTestBuilder();
    }

    public CheckTestBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CheckTestBuilder totalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public CheckTestBuilder checkItems(Set<CheckItem> checkItems) {
        this.checkItems = checkItems;
        return this;
    }

    public CheckTestBuilder discountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
        return this;
    }

    @Override
    public Check build() {
        return Check.builder()
                .id(id)
                .totalPrice(totalPrice)
                .checkItems(checkItems)
                .discountCard(discountCard)
                .build();
    }
}
