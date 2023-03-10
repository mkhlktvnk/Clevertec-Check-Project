package com.clevertec.clevertectesttaskrest.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckModel {
    @JsonProperty("checkId")
    private Long id;

    @JsonProperty("items")
    private List<CheckItemModel> items;

    @JsonProperty("discountCard")
    private DiscountCardModel discountCard;

    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    @JsonProperty("createdAt")
    private Timestamp createdAt;
}
