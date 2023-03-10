package com.clevertec.clevertectesttaskrest.testobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactTestObject {
    private long id;
    private String phone;
}
