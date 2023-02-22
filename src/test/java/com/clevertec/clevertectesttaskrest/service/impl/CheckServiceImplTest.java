package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.builder.impl.CheckRequestTestBuilder;
import com.clevertec.clevertectesttaskrest.builder.impl.CheckTestBuilder;
import com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder;
import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.repository.CheckRepository;
import com.clevertec.clevertectesttaskrest.service.CheckItemService;
import com.clevertec.clevertectesttaskrest.service.DiscountCardService;
import com.clevertec.clevertectesttaskrest.service.ProductService;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequest;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequestPosition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.clevertec.clevertectesttaskrest.builder.impl.CheckRequestTestBuilder.*;
import static com.clevertec.clevertectesttaskrest.builder.impl.CheckTestBuilder.aCheck;
import static com.clevertec.clevertectesttaskrest.builder.impl.DiscountCardTestBuilder.aDiscountCard;
import static com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder.aProduct;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckServiceImplTest {
    @Mock
    private CheckRepository checkRepository;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private DiscountCardServiceImpl discountCardService;

    @Mock
    private CheckItemServiceImpl checkItemService;

    @InjectMocks
    private CheckServiceImpl checkService;

    private static final Long ID = 1L;

    @Test
    void checkGetByPagingShouldCallRepositoryAndReturnActualChecks() {
        Pageable pageRequest = PageRequest.of(0, 3);
        List<Check> checks = List.of(
                aCheck().id(1L).totalPrice(BigDecimal.valueOf(0.1)).build(),
                aCheck().id(2L).totalPrice(BigDecimal.valueOf(0.2)).build(),
                aCheck().id(3L).totalPrice(BigDecimal.valueOf(0.3)).build()
        );
        when(checkRepository.findAll(pageRequest)).thenReturn(new ArrayList<>(checks));

        List<Check> actual = checkService.getByPaging(pageRequest);

        assertEquals(actual, checks);
        verify(checkRepository).findAll(pageRequest);
    }

    @Test
    void checkGetByIdShouldReturnActualCheckAndCallRepository() {
        Check check = CheckTestBuilder.aCheck()
                .id(ID)
                .totalPrice(BigDecimal.valueOf(0.1))
                .build();
        when(checkRepository.findById(ID)).thenReturn(Optional.of(check));

        Check actual = checkService.getById(ID);

        assertEquals(actual, check);
        verify(checkRepository).findById(ID);
    }

    @Test
    void checkGetByIdShouldThrowEntityNotFoundExceptionWhenCheckIsNotPresent() {
        when(checkRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> checkService.getById(ID));
    }

    @Test
    void createCheckShouldCallServices() {
        CheckRequest checkRequest = aCheckRequest()
                .discountCardId(1L)
                .positions(List.of(
                        new CheckRequestPosition(1L, 1),
                        new CheckRequestPosition(2L, 1),
                        new CheckRequestPosition(3L, 1)
                ))
                .build();
        List<Product> products = List.of(
                aProduct().id(1L).name("name-1").price(BigDecimal.valueOf(10.00)).build(),
                aProduct().id(1L).name("name-2").price(BigDecimal.valueOf(20.00)).build(),
                aProduct().id(1L).name("name-3").price(BigDecimal.valueOf(30.00)).build()
        );
        DiscountCard discountCard = aDiscountCard().id(1L).discount(20).build();
        when(productService.getProductsByIdIn(any())).thenReturn(products);
        when(discountCardService.getById(checkRequest.getDiscountCardId())).thenReturn(discountCard);

        checkService.createCheck(checkRequest);

        verify(productService).getProductsByIdIn(anySet());
        verify(checkItemService).saveAllItems(anySet());
        verify(discountCardService).getById(checkRequest.getDiscountCardId());
        verify(checkRepository).save(any(Check.class));
    }
}