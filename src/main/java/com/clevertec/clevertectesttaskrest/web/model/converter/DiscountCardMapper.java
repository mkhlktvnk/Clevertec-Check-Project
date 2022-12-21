package com.clevertec.clevertectesttaskrest.web.model.converter;

import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.web.model.DiscountCardModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DiscountCardMapper {
    DiscountCardMapper INSTANCE = Mappers.getMapper(DiscountCardMapper.class);

    DiscountCardModel toModel(DiscountCard discountCard);

    @Mapping(target = "checks", ignore = true)
    DiscountCard toEntity(DiscountCardModel discountCardModel);

    List<DiscountCardModel> allToModel(Collection<DiscountCard> entites);
    List<DiscountCard> allToEntity(Collection<DiscountCardModel> models);

}
