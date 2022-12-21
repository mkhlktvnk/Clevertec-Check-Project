package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.repository.DiscountCardRepository;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DiscountCardServiceImplTest {
    private static final Long ID = 1L;
    private static final Integer VALID_DISCOUNT = 50;
    private static final Integer INVALID_DISCOUNT = 101;
    @Mock
    private DiscountCardRepository repository;
    @InjectMocks
    private DiscountCardServiceImpl service;

    @Test
    void getById_shouldCallRepositoryOnceAndReturnDiscountCard_whenFound() {
        DiscountCard discountCard = Mockito.mock(DiscountCard.class);
        Mockito.when(repository.findById(ID)).thenReturn(Optional.ofNullable(discountCard));

        DiscountCard actual = service.getById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(discountCard, actual);
        Mockito.verify(repository, Mockito.only()).findById(ID);
    }

    @Test
    void getById_shouldCallRepositoryOnceAndThrowEntityNotFoundException_whenNotFound() {
        Mockito.when(repository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            DiscountCard actual = service.getById(ID);
        });

        Mockito.verify(repository, Mockito.only()).findById(ID);
    }

    @Test
    void create_shouldCallRepositoryOnce() {
        DiscountCard discountCard = DiscountCard.builder()
                .discount(VALID_DISCOUNT)
                .build();

        service.create(discountCard);

        Mockito.verify(repository, Mockito.only()).save(discountCard);
    }

    @Test
    void create_shouldThrowIncorrectRequestException_whenDiscountIsInvalid() {
        DiscountCard discountCard = DiscountCard.builder()
                .discount(INVALID_DISCOUNT)
                .build();

        Assertions.assertThrows(IncorrectRequestException.class, ()-> {
            service.create(discountCard);
        });
    }

    @Test
    void deleteById_shouldCallRepositoryTwice() {
        Mockito.when(repository.existsById(ID)).thenReturn(true);

        service.deleteById(ID);

        Mockito.verify(repository).existsById(ID);
        Mockito.verify(repository).deleteById(ID);
    }
}