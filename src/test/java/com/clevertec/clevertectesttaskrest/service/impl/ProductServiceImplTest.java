package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder;
import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.repository.ProductRepository;
import com.clevertec.clevertectesttaskrest.service.exception.EntityAlreadyExistsException;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private static final Long ID = 1L;

    @Test
    void checkGetByIdShouldReturnActualProduct() {
        Product product = ProductTestBuilder.aProduct()
                .name("name")
                .price(BigDecimal.valueOf(99.99))
                .build();
        when(productRepository.findById(ID)).thenReturn(Optional.of(product));

        Product actual = productService.getById(ID);

        assertEquals(actual, product);
    }

    @Test
    void checkGetByIdShouldCallRepositoryOnce() {
        Product product = ProductTestBuilder.aProduct()
                .name("name")
                .price(BigDecimal.valueOf(99.99))
                .build();
        when(productRepository.findById(ID)).thenReturn(Optional.of(product));

        productService.getById(ID);

        verify(productRepository).findById(ID);
    }

    @Test
    void checkGetByIdShouldThrowEntityNotFoundExceptionWhenProductIsNotPresent() {
        when(productRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> productService.getById(ID));
    }

    @Test
    void checkGetByPagingShouldReturnActualProductsAndCallRepository() {
        List<Product> products = List.of(
                ProductTestBuilder.aProduct()
                        .name("name1")
                        .price(BigDecimal.valueOf(99.92))
                        .build(),
                ProductTestBuilder.aProduct()
                        .name("name2")
                        .price(BigDecimal.valueOf(99.93))
                        .build()
        );
        when(productRepository.findAll(any(PageRequest.class))).thenReturn(new ArrayList<>(products));

        List<Product> actual = productService.getByPaging(PageRequest.of(0, 2));

        assertEquals(actual, products);
        verify(productRepository).findAll(any(PageRequest.class));
    }

    @Test
    void checkCreateShouldReturnCreatedProductAndCallRepository() {
        Product product = ProductTestBuilder
                .aProduct()
                .name("name")
                .price(BigDecimal.valueOf(89.32))
                .build();
        when(productRepository.existsByName(product.getName())).thenReturn(false);
        when(productRepository.save(product)).thenReturn(product);

        Product actual = productService.create(product);

        assertEquals(actual, product);
        verify(productRepository).existsByName(product.getName());
        verify(productRepository).save(product);
    }

    @Test
    void checkCreateShouldThrowEntityNotFoundExceptionWhenProductNameIsNotUnique() {
        Product product = ProductTestBuilder
                .aProduct()
                .name("name")
                .price(BigDecimal.valueOf(89.32))
                .build();
        when(productRepository.existsByName(product.getName())).thenReturn(true);

        assertThrows(EntityAlreadyExistsException.class, () -> productService.create(product));
    }

    @Test
    void checkCreateShouldIncorrectRequestExceptionWhenProductIsNotValid() {
        Product product = ProductTestBuilder.aProduct()
                .name("")
                .price(BigDecimal.valueOf(99.999))
                .build();

        assertThrows(IncorrectRequestException.class, () -> productService.create(product));
    }

    @Test
    void checkUpdateShouldCallRepositoryAndProductGetters() {
        Product product = ProductTestBuilder.aProduct()
                .name("name")
                .price(BigDecimal.valueOf(92.12))
                .build();
        when(productRepository.existsById(ID)).thenReturn(true);

        productService.update(ID, product);

        verify(productRepository).existsById(ID);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void checkUpdateShouldThrowIncorrectRequestExceptionWhenProductIsInvalid() {
        Product product = ProductTestBuilder.aProduct()
                .name("")
                .price(BigDecimal.valueOf(99.99))
                .build();
        when(productRepository.existsById(ID)).thenReturn(true);

        assertThrows(IncorrectRequestException.class, () -> productService.update(ID, product));
    }

    @Test
    void checkDeleteByIdShouldCallRepository() {
        when(productRepository.existsById(ID)).thenReturn(true);

        productService.deleteById(ID);

        verify(productRepository).existsById(ID);
        verify(productRepository).deleteById(ID);
    }

    @Test
    void checkDeleteByIdShouldThrowEntityNotFoundException() {
        when(productRepository.existsById(ID)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> productService.deleteById(ID));
    }

    @Test
    void checkGetProductsByIdInShouldCallRepository() {
        List<Long> ids = List.of(1L, 2L, 3L, 4L, 5L);
        List<Product> products = List.of(
                ProductTestBuilder.aProduct().name("name1").price(BigDecimal.valueOf(0.01)).build(),
                ProductTestBuilder.aProduct().name("name2").price(BigDecimal.valueOf(0.02)).build(),
                ProductTestBuilder.aProduct().name("name3").price(BigDecimal.valueOf(0.03)).build(),
                ProductTestBuilder.aProduct().name("name4").price(BigDecimal.valueOf(0.04)).build(),
                ProductTestBuilder.aProduct().name("name1").price(BigDecimal.valueOf(0.05)).build()
        );
        when(productRepository.findByIdIn(ids)).thenReturn(products);

        List<Product> actual = productService.getProductsByIdIn(ids);

        assertEquals(actual, products);
        verify(productRepository).findByIdIn(ids);
    }
}