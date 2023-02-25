package com.clevertec.clevertectesttaskrest.service.impl;

import com.clevertec.clevertectesttaskrest.domain.Contact;
import com.clevertec.clevertectesttaskrest.repository.ContactRepository;
import com.clevertec.clevertectesttaskrest.service.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.clevertec.clevertectesttaskrest.builder.impl.ContactTestBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {
    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    private static final Long ID = 1L;

    @Test
    void checkGetByIdShouldReturnActualContactAndCallRepository() {
        Contact contact = aContact()
                .withId(ID)
                .withFirstname("firstname")
                .withLastname("lastname")
                .withEmail("email@email.com")
                .withAge(20)
                .build();
        when(contactRepository.findById(ID)).thenReturn(Optional.of(contact));

        Contact actual = contactService.getById(ID);

        assertEquals(contact, actual);
        verify(contactRepository).findById(ID);
    }

    @Test
    void checkByIdShouldThrowEntityNotFoundExceptionWhenContactIsNotPresent() {
        when(contactRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> contactService.getById(ID));
    }

    @Test
    void checkCreateShouldReturnCreatedContactAndCallRepository() {
        Contact contact = aContact()
                .withId(ID)
                .withFirstname("firstname")
                .withLastname("lastname")
                .withEmail("email@email.com")
                .withAge(20)
                .build();
        when(contactRepository.save(contact)).thenReturn(contact);

        Contact created = contactService.create(contact);

        assertEquals(contact, created);
        verify(contactRepository).save(contact);
    }

    @Test
    void checkUpdateShouldCallRepository() {
        Contact contact = aContact()
                .withId(0L)
                .withFirstname("firstname")
                .withLastname("lastname")
                .withEmail("email@email.com")
                .withAge(20)
                .build();
        when(contactRepository.existsById(ID)).thenReturn(true);

        contactService.update(ID, contact);

        verify(contactRepository).existsById(ID);
        verify(contactRepository).save(any(Contact.class));
    }

    @Test
    void checkUpdateShouldThrowEntityNotFoundExceptionWhenContactIsNotPresent() {
        Contact contact = aContact()
                .withId(0L)
                .withFirstname("firstname")
                .withLastname("lastname")
                .withEmail("email@email.com")
                .withAge(20)
                .build();
        when(contactRepository.existsById(ID)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> contactService.update(ID, contact));
    }

    @Test
    void checkDeleteByIdShouldCallRepository() {
        when(contactRepository.existsById(ID)).thenReturn(true);

        contactService.deleteById(ID);

        verify(contactRepository).existsById(ID);
        verify(contactRepository).deleteById(ID);
    }

    @Test
    void checkDeleteByIdShouldThrowEntityNotFoundExceptionWhenProductIsNotPresent() {
        when(contactRepository.existsById(ID)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> contactService.deleteById(ID));
    }
}