package com.clevertec.clevertectesttaskrest.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductModel {
    private Long id;
    private String name;
    private BigDecimal price;
    private boolean promotional;
}
