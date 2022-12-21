package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.repository.ProductRepository;
import com.clevertec.clevertectesttaskrest.service.ProductService;
import com.clevertec.clevertectesttaskrest.service.exception.*;
import com.clevertec.clevertectesttaskrest.service.validation.ProductValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND.getValue()));
    }

    @Override
    public List<Product> getByPaging(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product create(Product entity) {
        ProductValidator.validateProduct(entity);
        if (productRepository.existsByName(entity.getName())) {
            throw new EntityAlreadyExistsException(ErrorMessage.PRODUCT_ALREADY_EXISTS.getValue());
        }
        return productRepository.save(entity);
    }

    @Transactional
    public void update(Long id, Product entity) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND.getValue());
        }
        ProductValidator.validateProduct(entity);
        Product productToUpdate = Product.builder()
                .id(id)
                .name(entity.getName())
                .price(entity.getPrice())
                .promotional(entity.isPromotional())
                .build();
        productRepository.save(productToUpdate);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND.getValue());
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByIdIn(Collection<Long> ids) {
        return productRepository.findByIdIn(ids);
    }

}
