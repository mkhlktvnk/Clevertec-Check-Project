package com.clevertec.clevertectesttaskrest.web.model.converter;

import com.clevertec.clevertectesttaskrest.arguments.ProductArgumentsProvider;
import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.web.model.ProductModel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @ParameterizedTest
    @ArgumentsSource(ProductArgumentsProvider.class)
    void toModelShouldConvert(Product product) {
        ProductModel model = mapper.toModel(product);
        assertAll(
                () -> assertEquals(model.getId(), product.getId()),
                () -> assertEquals(model.getName(), product.getName()),
                () -> assertEquals(model.getPrice(), product.getPrice())
        );
    }
}