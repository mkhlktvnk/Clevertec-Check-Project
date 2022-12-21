package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.repository.CheckItemRepository;
import com.clevertec.clevertectesttaskrest.service.CheckItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class CheckItemServiceImplTest {
    private static final Long ID = 1L;
    private final CheckItemRepository repository = Mockito.mock(CheckItemRepository.class);
    private final CheckItemService service = new CheckItemServiceImpl(repository);

    @Test
    void saveAllItems_shouldCallRepositoryOnce() {
        List<CheckItem> checkItems = Collections.singletonList(Mockito.mock(CheckItem.class));

        service.saveAllItems(checkItems);

        Mockito.verify(repository, Mockito.only()).saveAll(checkItems);
    }

    @Test
    void generateCheckItems_shouldNotCallRepository() {
        Product product = Mockito.mock(Product.class);
        Mockito.when(product.getPrice()).thenReturn(BigDecimal.TEN);
        List<Product> products = Collections.singletonList(product);
        Map<Long, Integer> map = Collections.singletonMap(1L, 1);
        Check check = Mockito.mock(Check.class);

        Mockito.when(product.getId()).thenReturn(1L);

        service.generateCheckItems(products, map, check);
    }
}