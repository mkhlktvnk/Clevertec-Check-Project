package com.clevertec.clevertectesttaskrest.web.model.converter;

import com.clevertec.clevertectesttaskrest.domain.Contact;
import com.clevertec.clevertectesttaskrest.web.model.ContactModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactModel toModel(Contact contact);
    Contact toEntity(ContactModel contactModel);
}
