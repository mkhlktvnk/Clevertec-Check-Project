package com.clevertec.clevertectesttaskrest.web.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckRequest {
    private List<CheckRequestPosition> items;
    private Long discountCardId;
}
