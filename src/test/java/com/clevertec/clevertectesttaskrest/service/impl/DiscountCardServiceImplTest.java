package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
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

import java.util.List;
import java.util.Optional;

import static com.clevertec.clevertectesttaskrest.builder.impl.DiscountCardTestBuilder.aDiscountCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        DiscountCard discountCard = aDiscountCard()
                .discount(20)
                .build();
        doReturn(Optional.of(discountCard)).when(discountCardRepository).findById(ID);

        DiscountCard actual = discountCardService.getById(ID);

        assertThat(actual).isEqualTo(discountCard);
        verify(discountCardRepository).findById(ID);
    }

    @Test
    void checkGetByIdShouldThrowEntityNotFoundExceptionWhenProductIsNotPresent() {
        doReturn(Optional.empty()).when(discountCardRepository).findById(ID);

        assertThatThrownBy(() -> discountCardService.getById(ID)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void checkGetByPagingShouldReturnActualDiscountCardsAndCallRepository() {
        PageRequest pageRequest = PageRequest.of(0, 4);
        List<DiscountCard> discountCards = List.of(
                aDiscountCard().discount(1).build(),
                aDiscountCard().discount(2).build(),
                aDiscountCard().discount(3).build(),
                aDiscountCard().discount(4).build(),
                aDiscountCard().discount(5).build()
        );
        doReturn(discountCards).when(discountCardRepository).findAll(pageRequest);

        List<DiscountCard> actual = discountCardService.getByPaging(pageRequest);

        verify(discountCardRepository).findAll(pageRequest);
        assertThat(actual).isEqualTo(discountCards);
    }

    @Test
    void checkCreateShouldReturnCreatedDiscountCardAndCallRepository() {
        DiscountCard discountCard = aDiscountCard().discount(20).build();
        doReturn(discountCard).when(discountCardRepository).save(discountCard);

        DiscountCard actual = discountCardService.create(discountCard);

        verify(discountCardRepository).save(discountCardCaptor.capture());
        assertThat(actual).isEqualTo(discountCard);
    }

    @Test
    void checkCreateShouldThrowIncorrectRequestExceptionWhenDiscountCardIsInvalid() {
        DiscountCard discountCard = aDiscountCard().discount(-100).build();

        assertThatThrownBy(() -> discountCardService.create(discountCard))
                .isInstanceOf(IncorrectRequestException.class);
    }

    @Test
    void checkDeleteByIdShouldCallRepository() {
        doReturn(true).when(discountCardRepository).existsById(ID);

        discountCardService.deleteById(ID);

        verify(discountCardRepository).existsById(ID);
        verify(discountCardRepository).deleteById(ID);
    }

    @Test
    void checkDeleteByIdShouldThrowEntityNotFoundExceptionWhenDiscountCardIsNotPresent() {
        doReturn(false).when(discountCardRepository).existsById(ID);

        assertThrows(EntityNotFoundException.class, () -> discountCardService.deleteById(ID));
    }
}