package com.clevertec.clevertectesttaskrest.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckItemModel {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("product")
    private ProductModel product;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;
}
