package com.clevertec.clevertectesttaskrest.repository;

import com.clevertec.clevertectesttaskrest.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    boolean existsByName(String name);
    List<Product> findAll(Pageable pageable);
    List<Product> findByIdIn(Collection<Long> id);
}
