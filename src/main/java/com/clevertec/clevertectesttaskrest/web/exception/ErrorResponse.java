package com.clevertec.clevertectesttaskrest.web.exception;

import lombok.Builder;

@Builder
public class ErrorResponse {
    private int code;
    private String message;
}
