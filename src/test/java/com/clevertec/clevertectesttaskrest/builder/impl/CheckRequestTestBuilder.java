package com.clevertec.clevertectesttaskrest.builder.impl;

import com.clevertec.clevertectesttaskrest.builder.TestDataBuilder;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequest;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequestPosition;

import java.util.ArrayList;
import java.util.List;

public class CheckRequestTestBuilder implements TestDataBuilder<CheckRequest> {
    private List<CheckRequestPosition> items = new ArrayList<>();
    private Long discountCardId = 0L;

    private CheckRequestTestBuilder() { }

    public static CheckRequestTestBuilder aCheckRequest() {
        return new CheckRequestTestBuilder();
    }

    public CheckRequestTestBuilder positions(List<CheckRequestPosition> items) {
        this.items = new ArrayList<>(items);
        return this;
    }

    public CheckRequestTestBuilder discountCardId(Long discountCardId) {
        this.discountCardId = discountCardId;
        return this;
    }

    @Override
    public CheckRequest build() {
        CheckRequest checkRequest = new CheckRequest();
        checkRequest.setItems(items);
        checkRequest.setDiscountCardId(discountCardId);
        return checkRequest;
    }
}
