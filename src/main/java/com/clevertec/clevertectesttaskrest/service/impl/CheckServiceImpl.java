package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.repository.CheckRepository;
import com.clevertec.clevertectesttaskrest.service.CheckItemService;
import com.clevertec.clevertectesttaskrest.service.CheckService;
import com.clevertec.clevertectesttaskrest.service.DiscountCardService;
import com.clevertec.clevertectesttaskrest.service.ProductService;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import com.clevertec.clevertectesttaskrest.service.validation.CheckRequestValidator;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequest;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequestPosition;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;
    private final ProductService productService;
    private final DiscountCardService discountCardService;
    private final CheckItemService checkItemService;

    @Override
    public List<Check> getByPaging(Pageable pageable) {
        return checkRepository.findAll(pageable);
    }

    @Override
    public Check getById(Long id) {
        return checkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Check not found!"));
    }

    @Override
    @Transactional
    public Check createCheck(CheckRequest checkRequest) {
        CheckRequestValidator.validateCheckRequest(checkRequest);
        Map<Long, Integer> idQuantityMap = checkRequest.getItems().stream()
                .collect(Collectors.toMap(CheckRequestPosition::getProductId, CheckRequestPosition::getQuantity));
        List<Product> products = productService.getProductsByIdIn(idQuantityMap.keySet());
        Check check = new Check();
        Set<CheckItem> checkItems = checkItemService.generateCheckItems(products, idQuantityMap, check);
        double totalPrice = checkItems.stream().mapToDouble(item -> item.getTotalPrice().doubleValue()).sum();
        if (checkRequest.getDiscountCardId() != null) {
            DiscountCard discountCard = discountCardService.getById(checkRequest.getDiscountCardId());
            check.setDiscountCard(discountCard);
            totalPrice -= totalPrice * discountCard.getDiscount() / 100;
        }
        check.setCheckItems(checkItems);
        check.setTotalPrice(BigDecimal.valueOf(totalPrice));
        checkItemService.saveAllItems(new HashSet<>(checkItems));
        return checkRepository.save(check);
    }
}
