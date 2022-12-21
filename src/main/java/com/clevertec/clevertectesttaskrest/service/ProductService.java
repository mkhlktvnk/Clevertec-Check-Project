package com.clevertec.clevertectesttaskrest.service;

import com.clevertec.clevertectesttaskrest.domain.Product;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    public Product getById(Long id);

    public Product create(Product entity);

    public void update(Long id, Product entity);

    public void deleteById(Long id);

    public List<Product> getProductsByIdIn(Collection<Long> ids);

    List<Product> getByPaging(Pageable pageable);
}
