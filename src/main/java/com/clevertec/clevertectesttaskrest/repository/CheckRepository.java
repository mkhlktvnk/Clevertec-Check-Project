package com.clevertec.clevertectesttaskrest.repository;

import com.clevertec.clevertectesttaskrest.domain.Check;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckRepository extends CrudRepository<Check, Long> {
    List<Check> findAll(Pageable pageable);
}
