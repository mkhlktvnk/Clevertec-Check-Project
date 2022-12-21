package com.clevertec.clevertectesttaskrest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@Table
public class DiscountCard extends AbstractEntity {
    @Column(nullable = false)
    private Integer discount;

    @OneToMany(mappedBy = "discountCard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Check> checks = new HashSet<>();
}
