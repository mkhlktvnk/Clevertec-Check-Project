package com.clevertec.clevertectesttaskrest.service;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiscountCardService {
    DiscountCard getById(Long id);

    List<DiscountCard> getByPaging(Pageable pageable);

    DiscountCard create(DiscountCard entity);

    public void deleteById(Long id);
}
