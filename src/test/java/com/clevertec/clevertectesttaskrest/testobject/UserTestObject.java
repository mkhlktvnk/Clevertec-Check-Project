package com.clevertec.clevertectesttaskrest.testobject;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class UserTestObject {
    private long id;
    private String username;
    private Collection<ContactTestObject> contacts;
}
