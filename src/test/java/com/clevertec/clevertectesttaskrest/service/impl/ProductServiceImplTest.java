package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.repository.ProductRepository;
import com.clevertec.clevertectesttaskrest.service.ProductService;
import com.clevertec.clevertectesttaskrest.service.exception.EntityAlreadyExistsException;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

class ProductServiceImplTest {
    private static final BigDecimal VALID_PRICE = BigDecimal.valueOf(222.22);
    private static final BigDecimal INVALID_PRICE = BigDecimal.valueOf(222.222);
    private static final Integer VALID_NAME_LENGTH = 10;
    private static final String VALID_NAME = "Product";
    private final static Long ID = 1L;
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private final ProductService productService = new ProductServiceImpl(productRepository);

    @Test
    void getById_shouldCallRepositoryOnce() {
        final Product product = Mockito.mock(Product.class);
        Mockito.when(productRepository.findById(ID)).thenReturn(Optional.of(product));

        final Product actual = productService.getById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(product, actual);
        Mockito.verify(productRepository, Mockito.only()).findById(ID);
    }

    @Test
    void create_shouldCallRepository() {
        final Product product = Product.builder()
                .name(VALID_NAME)
                .price(VALID_PRICE)
                .promotional(true)
                .build();

        Mockito.when(productRepository.existsByName(product.getName())).thenReturn(false);

        final Product actual = productService.create(product);

        Mockito.verify(productRepository).save(product);
    }

    @Test
    void create_shouldThrowIncorrectRequestException_whenProductInvalid() {
        final Product product = Product.builder()
                .name(VALID_NAME)
                .price(INVALID_PRICE)
                .build();

        Mockito.when(productRepository.existsByName(product.getName())).thenReturn(false);

        Assertions.assertThrows(IncorrectRequestException.class, () -> {
            productService.create(product);
        });
    }

    @Test
    void create_shouldThrowEntityAlreadyExistsException_whenProductExists() {
        final Product product = Product.builder()
                .name(VALID_NAME)
                .price(VALID_PRICE)
                .promotional(true)
                .build();
        Mockito.when(productRepository.existsByName(product.getName())).thenReturn(true);

        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> {
            productService.create(product);
        });
    }

    @Test
    void getProductsByIds_shouldCallRepositoryOnce() {
        List<Long> ids = List.of(1L, 2L, 6L, 9L, 2L);

        productService.getProductsByIdIn(ids);

        Mockito.verify(productRepository, Mockito.only()).findByIdIn(ids);
    }

    @Test
    void updateProduct_shouldThrowEntityNotFoundException_whenNotExists() {
        final Product product = Product.builder()
                .name(VALID_NAME)
                .price(VALID_PRICE)
                .promotional(true)
                .build();
        Mockito.when(productRepository.existsById(ID)).thenReturn(false);

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productService.update(ID, product);
        });
    }

    @Test
    void deleteById_shouldCallRepositoryTwice() {
        Mockito.when(productRepository.existsById(ID)).thenReturn(true);

        productService.deleteById(ID);

        Mockito.verify(productRepository).existsById(ID);
        Mockito.verify(productRepository).deleteById(ID);
    }

    @Test
    void deleteById_shouldThrowEntityNotFoundException_whenNotExists() {
        Mockito.when(productRepository.existsById(ID)).thenReturn(false);

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productService.deleteById(ID);
        });
    }
}