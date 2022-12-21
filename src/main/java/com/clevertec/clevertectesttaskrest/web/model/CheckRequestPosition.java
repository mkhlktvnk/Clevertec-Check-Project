package com.clevertec.clevertectesttaskrest.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckRequestPosition {
    private Long productId;
    private Integer quantity;
}
