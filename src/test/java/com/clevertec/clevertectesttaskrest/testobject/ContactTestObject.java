package com.clevertec.clevertectesttaskrest.testobject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactTestObject {
    private long id;
    private String phone;
}
