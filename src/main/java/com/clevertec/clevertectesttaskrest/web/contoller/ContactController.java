package com.clevertec.clevertectesttaskrest.web.contoller;

import com.clevertec.clevertectesttaskrest.service.ContactService;
import com.clevertec.clevertectesttaskrest.web.model.ContactModel;
import com.clevertec.clevertectesttaskrest.web.model.converter.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
    private final ContactMapper mapper = ContactMapper.INSTANCE;

    @GetMapping(value = "/contacts/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ContactModel getContact(@PathVariable Long id) {
        return mapper.toModel(contactService.getById(id));
    }

    @PostMapping("/contacts")
    public ContactModel addContact(@RequestBody ContactModel contactModel) {
        return mapper.toModel(contactService.create(mapper.toEntity(contactModel)));
    }

    @PutMapping("/contacts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContact(@PathVariable Long id, @RequestBody ContactModel contactModel) {
        contactService.update(id, mapper.toEntity(contactModel));
    }

    @DeleteMapping("/contacts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteById(id);
    }
}
