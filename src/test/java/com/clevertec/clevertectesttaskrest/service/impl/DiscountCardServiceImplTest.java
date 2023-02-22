package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.builder.impl.DiscountCardTestBuilder;
import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.repository.DiscountCardRepository;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscountCardServiceImplTest {
    @Mock
    private DiscountCardRepository discountCardRepository;

    @Captor
    private ArgumentCaptor<DiscountCard> discountCardCaptor;

    @InjectMocks
    private DiscountCardServiceImpl discountCardService;

    private static final Long ID = 1L;

    @Test
    void checkGetByIdShouldReturnActualDiscountCardAndCallRepository() {
        DiscountCard discountCard = DiscountCardTestBuilder.aDiscountCard()
                .discount(20)
                .build();
        when(discountCardRepository.findById(ID)).thenReturn(Optional.of(discountCard));

        DiscountCard actual = discountCardService.getById(ID);

        assertEquals(actual, discountCard);
        verify(discountCardRepository).findById(ID);
    }

    @Test
    void checkGetByIdShouldThrowEntityNotFoundExceptionWhenProductIsNotPresent() {
        when(discountCardRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> discountCardService.getById(ID));
    }

    @Test
    void checkGetByPagingShouldReturnActualDiscountCardsAndCallRepository() {
        PageRequest pageRequest = PageRequest.of(0, 4);
        List<DiscountCard> discountCards = List.of(
                DiscountCardTestBuilder.aDiscountCard().discount(1).build(),
                DiscountCardTestBuilder.aDiscountCard().discount(2).build(),
                DiscountCardTestBuilder.aDiscountCard().discount(3).build(),
                DiscountCardTestBuilder.aDiscountCard().discount(4).build(),
                DiscountCardTestBuilder.aDiscountCard().discount(5).build()
        );
        when(discountCardRepository.findAll(pageRequest)).thenReturn(discountCards);

        List<DiscountCard> actual = discountCardService.getByPaging(pageRequest);

        assertEquals(discountCards, actual);
        verify(discountCardRepository).findAll(pageRequest);
    }

    @Test
    void checkCreateShouldReturnCreatedDiscountCardAndCallRepository() {
        DiscountCard discountCard = DiscountCardTestBuilder.aDiscountCard()
                .discount(20)
                .build();
        when(discountCardRepository.save(discountCard)).thenReturn(discountCard);

        DiscountCard actual = discountCardService.create(discountCard);

        assertEquals(actual, discountCard);
        verify(discountCardRepository).save(discountCardCaptor.capture());
    }

    @Test
    void checkCreateShouldThrowIncorrectRequestExceptionWhenDiscountCardIsInvalid() {
        DiscountCard discountCard = DiscountCardTestBuilder.aDiscountCard()
                .discount(-100)
                .build();

        assertThrows(IncorrectRequestException.class, () -> discountCardService.create(discountCard));
    }

    @Test
    void checkDeleteByIdShouldCallRepository() {
        when(discountCardRepository.existsById(ID)).thenReturn(true);

        discountCardService.deleteById(ID);

        verify(discountCardRepository).existsById(ID);
        verify(discountCardRepository).deleteById(ID);
    }

    @Test
    void checkDeleteByIdShouldThrowEntityNotFoundExceptionWhenDiscountCardIsNotPresent() {
        when(discountCardRepository.existsById(ID)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> discountCardService.deleteById(ID));
    }
}