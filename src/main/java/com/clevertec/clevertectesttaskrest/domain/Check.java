package com.clevertec.clevertectesttaskrest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
@Table(name = "_check")
public class Check extends AbstractEntity {
    @Column(nullable = false, updatable = false)
    private BigDecimal totalPrice;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "check", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<CheckItem> checkItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @ToString.Exclude
    private DiscountCard discountCard;
}
