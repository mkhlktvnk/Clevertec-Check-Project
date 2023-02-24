package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.Contact;
import com.clevertec.clevertectesttaskrest.repository.ContactRepository;
import com.clevertec.clevertectesttaskrest.service.ContactService;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public Contact getById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found"));
    }

    @Override
    public Contact create(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void update(Long id, Contact contact) {
        Contact contactToUpdate = Contact.builder()
                .id(id)
                .firstName(contact.getFirstName())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .age(contact.getAge())
                .build();
        contactRepository.save(contactToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new EntityNotFoundException("Contact not found!");
        }
        contactRepository.deleteById(id);
    }
}
