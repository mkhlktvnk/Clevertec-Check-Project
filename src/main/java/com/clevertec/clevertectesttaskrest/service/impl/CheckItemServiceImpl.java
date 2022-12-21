package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.repository.CheckItemRepository;
import com.clevertec.clevertectesttaskrest.service.CheckItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CheckItemServiceImpl implements CheckItemService {
    private final CheckItemRepository checkItemRepository;

    @Override
    public Iterable<CheckItem> saveAllItems(Iterable<CheckItem> items) {
         return checkItemRepository.saveAll(items);
    }

    @Override
    public Set<CheckItem> generateCheckItems(List<Product> products, Map<Long, Integer> requestMap, Check check) {
        Set<CheckItem> checkItems = new HashSet<>();
        products.forEach(product -> {
            Integer quantity = requestMap.get(product.getId());
            BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            if (product.isPromotional() && quantity > 5) {
                totalPrice = totalPrice.subtract(totalPrice.multiply(BigDecimal.valueOf(0.10)));
            }
            CheckItem checkItem = CheckItem.builder()
                    .check(check)
                    .product(product)
                    .quantity(quantity)
                    .totalPrice(totalPrice)
                    .build();
            checkItems.add(checkItem);
        });
        return checkItems;
    }
}
