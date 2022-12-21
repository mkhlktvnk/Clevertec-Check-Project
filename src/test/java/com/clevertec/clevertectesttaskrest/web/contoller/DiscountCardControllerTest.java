package com.clevertec.clevertectesttaskrest.web.contoller;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.service.impl.DiscountCardServiceImpl;
import com.clevertec.clevertectesttaskrest.web.model.DiscountCardModel;
import com.clevertec.clevertectesttaskrest.web.model.converter.DiscountCardMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class DiscountCardControllerTest {
    private static final Long ID = 1L;

    @Mock
    private DiscountCardMapper discountCardMapper;

    @Mock
    private DiscountCardServiceImpl discountCardService;

    @InjectMocks
    private DiscountCardController discountCardController;

    @Test
    void getDiscountCards() {
        PageRequest pageRequest = PageRequest.of(1, 1);

        discountCardController.getDiscountCards(pageRequest);

        Mockito.verify(discountCardService, Mockito.only()).getByPaging(pageRequest);
    }

    @Test
    void getDiscountCard_shouldCallRepositoryOnce() {
        final DiscountCard discountCard = Mockito.mock(DiscountCard.class);
        Mockito.when(discountCardService.getById(ID)).thenReturn(discountCard);

        DiscountCardModel actual = discountCardController.getDiscountCard(ID);

        Assertions.assertNotNull(actual);
        Mockito.verify(discountCardService).getById(ID);
    }
}