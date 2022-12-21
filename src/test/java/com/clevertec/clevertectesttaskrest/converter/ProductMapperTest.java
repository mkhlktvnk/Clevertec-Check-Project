package com.clevertec.clevertectesttaskrest.converter;

import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.web.model.ProductModel;
import com.clevertec.clevertectesttaskrest.web.model.converter.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductMapperTest {
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Test
    public void toModel_shouldConvertToProduct() {
        Product product = Mockito.mock(Product.class);
        ProductModel productModel = productMapper.toModel(product);

        Assertions.assertEquals(product.getId(), productModel.getId());
        Assertions.assertEquals(product.getName(), productModel.getName());
        Assertions.assertEquals(product.getPrice(), productModel.getPrice());
        Assertions.assertEquals(product.isPromotional(), productModel.isPromotional());
    }
}
