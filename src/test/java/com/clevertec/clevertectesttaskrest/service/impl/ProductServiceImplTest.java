package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.repository.ProductRepository;
import com.clevertec.clevertectesttaskrest.service.exception.EntityAlreadyExistsException;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import com.clevertec.clevertectesttaskrest.service.exception.IncorrectRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder.aProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @InjectMocks
    private ProductServiceImpl productService;

    private static final Long ID = 1L;

    @Test
    void checkGetByIdShouldReturnActualProduct() {
        Product product = aProduct().name("name").price(BigDecimal.valueOf(99.99)).build();
        doReturn(Optional.of(product)).when(productRepository).findById(ID);

        Product actual = productService.getById(ID);

        assertThat(actual).isEqualTo(product);
    }

    @Test
    void checkGetByIdShouldCallRepositoryOnce() {
        Product product = aProduct().name("name").price(BigDecimal.valueOf(99.99)).build();
        when(productRepository.findById(ID)).thenReturn(Optional.of(product));

        productService.getById(ID);

        verify(productRepository).findById(ID);
    }

    @Test
    void checkGetByIdShouldThrowEntityNotFoundExceptionWhenProductIsNotPresent() {
        doReturn(Optional.empty()).when(productRepository).findById(ID);

        assertThatThrownBy(() -> productService.getById(ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void checkGetByPagingShouldReturnActualProductsAndCallRepository() {
        List<Product> products = List.of(
                aProduct().name("name1").price(BigDecimal.valueOf(99.92)).build(),
                aProduct().name("name2").price(BigDecimal.valueOf(99.93)).build()
        );
        doReturn(new ArrayList<>(products)).when(productRepository).findAll(any(PageRequest.class));

        List<Product> actual = productService.getByPaging(PageRequest.of(0, 2));

        verify(productRepository).findAll(any(PageRequest.class));
        assertThat(actual).isEqualTo(products);
    }

    @Test
    void checkCreateShouldReturnCreatedProductAndCallRepository() {
        Product product = aProduct().name("name").price(BigDecimal.valueOf(89.32)).build();
        doReturn(false).when(productRepository).existsByName(product.getName());
        doReturn(product).when(productRepository).save(product);

        Product actual = productService.create(product);

        verify(productRepository).existsByName(product.getName());
        verify(productRepository).save(productCaptor.capture());
        assertThat(actual).isEqualTo(product);
    }

    @Test
    void checkCreateShouldThrowEntityAlreadyExistsExceptionWhenProductNameIsNotUnique() {
        Product product = aProduct().name("name").price(BigDecimal.valueOf(89.32)).build();
        doReturn(true).when(productRepository).existsByName(product.getName());

        assertThatThrownBy(() -> productService.create(product))
                .isInstanceOf(EntityAlreadyExistsException.class);
    }

    @Test
    void checkCreateShouldIncorrectRequestExceptionWhenProductIsNotValid() {
        Product product = aProduct().name("").price(BigDecimal.valueOf(99.999)).build();

        assertThatThrownBy(() -> productService.create(product))
                .isInstanceOf(IncorrectRequestException.class);
    }

    @Test
    void checkUpdateShouldCallRepositoryAndProductGetters() {
        Product product = aProduct().name("name").price(BigDecimal.valueOf(92.12)).build();
        doReturn(true).when(productRepository).existsById(ID);

        productService.update(ID, product);

        verify(productRepository).existsById(ID);
        verify(productRepository).save(productCaptor.capture());
    }

    @Test
    void checkUpdateShouldThrowEntityNotFoundExceptionWhenProductIsNotPresent() {
        Product product = aProduct().name("name").price(BigDecimal.valueOf(92.12)).build();
        doReturn(false).when(productRepository).existsById(ID);

        assertThatThrownBy(() -> productService.update(ID, product))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void checkUpdateShouldThrowIncorrectRequestExceptionWhenProductIsInvalid() {
        Product product = aProduct().name("").price(BigDecimal.valueOf(99.99)).build();
        doReturn(true).when(productRepository).existsById(ID);

        assertThatThrownBy(() -> productService.update(ID, product))
                .isInstanceOf(IncorrectRequestException.class);
    }

    @Test
    void checkDeleteByIdShouldCallRepository() {
        doReturn(true).when(productRepository).existsById(ID);

        productService.deleteById(ID);

        verify(productRepository).existsById(ID);
        verify(productRepository).deleteById(ID);
    }

    @Test
    void checkDeleteByIdShouldThrowEntityNotFoundException() {
        when(productRepository.existsById(ID)).thenReturn(false);

        assertThatThrownBy(() -> productService.deleteById(ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void checkGetProductsByIdInShouldCallRepository() {
        List<Long> ids = List.of(1L, 2L, 3L, 4L, 5L);
        List<Product> products = List.of(
                aProduct().name("name1").price(BigDecimal.valueOf(0.01)).build(),
                aProduct().name("name2").price(BigDecimal.valueOf(0.02)).build(),
                aProduct().name("name3").price(BigDecimal.valueOf(0.03)).build(),
                aProduct().name("name4").price(BigDecimal.valueOf(0.04)).build(),
                aProduct().name("name1").price(BigDecimal.valueOf(0.05)).build()
        );
        doReturn(products).when(productRepository).findByIdIn(ids);

        List<Product> actual = productService.getProductsByIdIn(ids);

        verify(productRepository).findByIdIn(ids);
        assertThat(actual).isEqualTo(products);
    }
}