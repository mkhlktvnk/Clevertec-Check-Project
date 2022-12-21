package com.clevertec.clevertectesttaskrest.service;

import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.web.model.CheckRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CheckService {
    List<Check> getByPaging(Pageable pageable);
    Check getById(Long id);
    Check createCheck(CheckRequest cashReceiptRequest);
}
