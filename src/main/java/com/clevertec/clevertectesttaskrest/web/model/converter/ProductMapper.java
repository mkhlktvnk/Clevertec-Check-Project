package com.clevertec.clevertectesttaskrest.web.model.converter;

import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.web.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;


@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "promotional", source = "promotional")
    ProductModel toModel(Product product);
    Product toEntity(ProductModel productModel);

    List<ProductModel> allToModel(Collection<Product> entites);
    List<Product> allToEntity(Collection<Product> models);

}
