package com.clevertec.clevertectesttaskrest.repository;

import com.clevertec.clevertectesttaskrest.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
