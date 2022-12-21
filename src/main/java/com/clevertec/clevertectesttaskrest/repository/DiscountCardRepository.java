package com.clevertec.clevertectesttaskrest.repository;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountCardRepository extends CrudRepository<DiscountCard, Long> {
    List<DiscountCard> findAll(Pageable pageable);
}
