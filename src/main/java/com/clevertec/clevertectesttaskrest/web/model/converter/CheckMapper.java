package com.clevertec.clevertectesttaskrest.web.model.converter;

import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.web.model.CheckModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {
        CheckItemMapper.class,
        DiscountCardMapper.class,
        ProductMapper.class
})
public interface CheckMapper {
    CheckMapper INSTANCE = Mappers.getMapper(CheckMapper.class);

    @Mapping(target = "discountCard", source = "discountCard")
    @Mapping(target = "items", source = "checkItems")
    CheckModel toModel(Check check);

    @Mapping(target = "discountCard", source = "discountCard")
    @Mapping(target = "checkItems", source = "items")
    Check toEntity(CheckModel checkModel);

    List<CheckModel> allToModel(Collection<Check> entities);

    List<CheckModel> allToEntity(Collection<CheckModel> models);
}
