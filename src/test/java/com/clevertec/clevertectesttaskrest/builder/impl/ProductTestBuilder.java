package com.clevertec.clevertectesttaskrest.builder.impl;

import com.clevertec.clevertectesttaskrest.builder.TestDataBuilder;
import com.clevertec.clevertectesttaskrest.domain.Product;

import java.math.BigDecimal;

public class ProductTestBuilder implements TestDataBuilder<Product> {
    private Long id = 0L;
    private String name = "";
    private BigDecimal price = BigDecimal.valueOf(0.00);

    private ProductTestBuilder() {}

    public ProductTestBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ProductTestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductTestBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public static ProductTestBuilder aProduct() {
        return new ProductTestBuilder();
    }

    @Override
    public Product build() {
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
    }
}
