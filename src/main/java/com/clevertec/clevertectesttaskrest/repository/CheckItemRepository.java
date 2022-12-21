package com.clevertec.clevertectesttaskrest.repository;

import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckItemRepository extends CrudRepository<CheckItem, Long> {
}
