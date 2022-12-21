package com.clevertec.clevertectesttaskrest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@Table
public class Product extends AbstractEntity {
    @Column(nullable = false)
    private String name;

    @Digits(integer = 38, fraction = 2)
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean promotional;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
