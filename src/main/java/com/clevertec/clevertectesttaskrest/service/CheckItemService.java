package com.clevertec.clevertectesttaskrest.service;

import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.domain.Product;

import java.util.*;

public interface CheckItemService {
    Iterable<CheckItem> saveAllItems(Iterable<CheckItem> items);
    Set<CheckItem> generateCheckItems(List<Product> products, Map<Long, Integer> requestMap, Check check);
}
