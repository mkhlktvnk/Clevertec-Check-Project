package com.clevertec.clevertectesttaskrest.web.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CheckRequestPosition {
    private Long productId;
    private Integer quantity;
}
