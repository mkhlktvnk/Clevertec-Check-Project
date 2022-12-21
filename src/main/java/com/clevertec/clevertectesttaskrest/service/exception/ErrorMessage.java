package com.clevertec.clevertectesttaskrest.service.exception;

public enum ErrorMessage {
    INCORRECT_ID("Incorrect id!"),
    ID_ALREADY_EXISTS("Bad id!"),
    ID_NOT_EXISTS("Id does not exists!"),
    INVALID_PRODUCT_NAME_LENGTH("Product name length is invalid!"),
    INVALID_PRODUCT_PRICE("Invalid price of product!"),
    PRODUCT_ALREADY_EXISTS("Product with this name already exists!"),
    PRODUCT_NOT_FOUND("Product not found!"),
    DUPLICATE_POSITIONS_ID("Duplicates in requested id values!"),
    INCORRECT_QUANTITY("Incorrect quantity of product!"),
    INVALID_DISCOUNT("Invalid discount!");


    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
