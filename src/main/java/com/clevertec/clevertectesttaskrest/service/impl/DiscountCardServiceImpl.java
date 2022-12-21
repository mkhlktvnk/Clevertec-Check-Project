package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.repository.DiscountCardRepository;
import com.clevertec.clevertectesttaskrest.service.DiscountCardService;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import com.clevertec.clevertectesttaskrest.service.validation.DiscountCardValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountCardServiceImpl implements DiscountCardService {
    private final DiscountCardRepository discountCardRepository;

    @Override
    public DiscountCard getById(Long id) {
        return discountCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discount card not found!"));
    }

    @Override
    public List<DiscountCard> getByPaging(Pageable pageable) {
        return discountCardRepository.findAll(pageable);
    }

    @Override
    public DiscountCard create(DiscountCard entity) {
        DiscountCardValidator.validateDiscountCard(entity);
        return discountCardRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!discountCardRepository.existsById(id)) {
            throw new EntityNotFoundException("Discount card not found!");
        }
        discountCardRepository.deleteById(id);
    }
}
