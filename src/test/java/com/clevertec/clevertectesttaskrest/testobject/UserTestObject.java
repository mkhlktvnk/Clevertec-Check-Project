package com.clevertec.clevertectesttaskrest.testobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTestObject {
    private long id;
    private String username;
    private Collection<ContactTestObject> contacts;
}
