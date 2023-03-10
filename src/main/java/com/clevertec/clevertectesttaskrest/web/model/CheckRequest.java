package com.clevertec.clevertectesttaskrest.web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class CheckRequest {
    private List<CheckRequestPosition> items;
    private Long discountCardId;
}
