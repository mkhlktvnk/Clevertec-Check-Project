package com.clevertec.clevertectesttaskrest.builder.impl;

import com.clevertec.clevertectesttaskrest.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@NoArgsConstructor(staticName = "aContact")
@AllArgsConstructor
@With
public class ContactTestBuilder {
    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private Integer age;

    public Contact build() {
        return Contact.builder()
                .id(id)
                .firstname(firstname)
                .lastname(lastname)
                .age(age)
                .build();
    }
}
