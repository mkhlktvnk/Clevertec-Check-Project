package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.builder.impl.CheckTestBuilder;
import com.clevertec.clevertectesttaskrest.builder.impl.DiscountCardTestBuilder;
import com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder;
import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.repository.CheckItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckItemServiceImplTest {
    @Mock
    private CheckItemRepository checkItemRepository;

    @InjectMocks
    private CheckItemServiceImpl checkItemService;

    @Test
    void saveAllItemsShouldCallRepository() {
        List<CheckItem> checkItems = Collections.singletonList(CheckItem.builder().build());

        checkItemService.saveAllItems(checkItems);

        verify(checkItemRepository).saveAll(checkItems);
    }

    @Test
    void generateCheckItemsShouldReturnItemWithSizeEqualToProductsSize() {
        Product product = ProductTestBuilder.aProduct()
                .id(1L)
                .price(BigDecimal.TEN)
                .build();
        List<Product> products = Collections.singletonList(product);
        Map<Long, Integer> map = Collections.singletonMap(1L, 1);
        Check check = CheckTestBuilder.aCheck().build();

        Set<CheckItem> actual = checkItemService.generateCheckItems(products, map, check);

        assertEquals(actual.size(), products.size());
    }
}