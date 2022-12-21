package com.clevertec.clevertectesttaskrest.web.contoller;

import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    private static final Long ID = 1L;
    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController controller;

    @Test
    void getProducts() {
        PageRequest pageRequest = PageRequest.of(1, 1);

        controller.getProducts(pageRequest);

        Mockito.verify(productService, Mockito.only()).getByPaging(pageRequest);
    }

    @Test
    void getProduct() {
        final Product product = Mockito.mock(Product.class);
        Mockito.when(productService.getById(ID)).thenReturn(product);

        controller.getProduct(ID);

        Mockito.verify(productService).getById(ID);
    }

    @Test
    void deleteProduct() {
        controller.deleteProduct(ID);
        Mockito.verify(productService).deleteById(ID);
    }
}
