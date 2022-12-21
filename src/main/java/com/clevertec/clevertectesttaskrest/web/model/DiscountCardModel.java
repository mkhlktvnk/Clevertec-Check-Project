package com.clevertec.clevertectesttaskrest.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscountCardModel {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("discount")
    private Integer discount;
}
