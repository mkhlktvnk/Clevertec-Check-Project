package com.clevertec.clevertectesttaskrest.web.contoller;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.service.DiscountCardService;
import com.clevertec.clevertectesttaskrest.web.model.DiscountCardModel;
import com.clevertec.clevertectesttaskrest.web.model.converter.DiscountCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiscountCardController {
    private final DiscountCardService discountCardService;
    private final DiscountCardMapper discountCardMapper = DiscountCardMapper.INSTANCE;

    @GetMapping("/discountCards")
    public List<DiscountCardModel> getDiscountCards(@PageableDefault Pageable pageable) {
        return discountCardMapper.allToModel(discountCardService
                .getByPaging(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())));
    }

    @GetMapping("/discountCards/{id}")
    public DiscountCardModel getDiscountCard(@PathVariable Long id) {
        return discountCardMapper.toModel(discountCardService.getById(id));
    }

    @PostMapping("/discountCards")
    @ResponseStatus(HttpStatus.CREATED)
    public DiscountCardModel createDiscountCard(@RequestBody DiscountCardModel discountCardModel) {
        DiscountCard discountCard = discountCardMapper.toEntity(discountCardModel);
        return discountCardMapper.toModel(discountCardService.create(discountCard));
    }
}
