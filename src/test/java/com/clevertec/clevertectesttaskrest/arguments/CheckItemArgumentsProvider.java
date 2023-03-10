package com.clevertec.clevertectesttaskrest.arguments;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.clevertec.clevertectesttaskrest.builder.impl.CheckItemTestBuilder.aCheckItem;
import static com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder.aProduct;

public class CheckItemArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(aCheckItem()
                        .id(1L)
                        .product(aProduct().id(1L).name("name-1").price(BigDecimal.ONE).build())
                        .quantity(1)
                        .totalPrice(BigDecimal.ONE)
                        .build()),
                Arguments.of(aCheckItem()
                        .id(2L)
                        .product(aProduct().id(2L).name("name-2").price(BigDecimal.valueOf(2)).build())
                        .quantity(1)
                        .totalPrice(BigDecimal.valueOf(2))
                        .build()),
                Arguments.of(aCheckItem()
                        .id(3L)
                        .product(aProduct().id(3L).name("name-3").price(BigDecimal.valueOf(3)).build())
                        .quantity(1)
                        .totalPrice(BigDecimal.valueOf(3))
                        .build()),
                Arguments.of(aCheckItem()
                        .id(4L)
                        .product(aProduct().id(4L).name("name-4").price(BigDecimal.valueOf(4)).build())
                        .quantity(1)
                        .totalPrice(BigDecimal.valueOf(4))
                        .build()),
                Arguments.of(aCheckItem()
                        .id(5L)
                        .product(aProduct().id(5L).name("name-5").price(BigDecimal.valueOf(5)).build())
                        .quantity(5)
                        .totalPrice(BigDecimal.valueOf(5))
                        .build())
        );
    }
}
