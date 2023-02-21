package com.clevertec.clevertectesttaskrest.builder.impl;

import com.clevertec.clevertectesttaskrest.builder.TestDataBuilder;
import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.domain.Product;

import java.math.BigDecimal;

import static com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder.aProduct;

public class CheckItemTestBuilder implements TestDataBuilder<CheckItem> {
    private Long id = 0L;
    private Product product = aProduct().build();
    private Integer quantity = 0;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    private CheckItemTestBuilder() { }

    public static CheckItemTestBuilder aCheckItem() {
        return new CheckItemTestBuilder();
    }

    public CheckItemTestBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CheckItemTestBuilder product(Product product) {
        this.product = product;
        return this;
    }

    public CheckItemTestBuilder quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public CheckItemTestBuilder totalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    @Override
    public CheckItem build() {
        return CheckItem.builder()
                .id(id)
                .product(product)
                .quantity(quantity)
                .totalPrice(totalPrice)
                .build();
    }
}
