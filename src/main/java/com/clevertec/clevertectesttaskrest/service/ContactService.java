package com.clevertec.clevertectesttaskrest.service;

import com.clevertec.clevertectesttaskrest.domain.Contact;
import com.clevertec.clevertectesttaskrest.domain.Product;

public interface ContactService {
    Contact getById(Long id);
    Contact create(Contact product);
    void update(Long id, Contact contact);
    void deleteById(Long id);
}
