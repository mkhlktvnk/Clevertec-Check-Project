package com.clevertec.clevertectesttaskrest.web.model.converter;

import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.web.model.CheckItemModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {ProductMapper.class})
public interface CheckItemMapper {
    CheckItemMapper INSTANCE = Mappers.getMapper(CheckItemMapper.class);
    @Mapping(source = "checkItem.product", target = "product")
    CheckItemModel toModel(CheckItem checkItem);

    @Mapping(target = "check", ignore = true)
    @Mapping(source = "checkItemModel.product", target = "product")
    CheckItem convertToCheckItem(CheckItemModel checkItemModel);

    List<CheckItemModel> allToModel(Collection<CheckItem> entities);

    List<CheckItem> allToEntity(Collection<CheckItemModel> models);
}
