package com.clevertec.clevertectesttaskrest.arguments;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static com.clevertec.clevertectesttaskrest.builder.impl.DiscountCardTestBuilder.aDiscountCard;

public class DiscountCardArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(aDiscountCard().id(1L).discount(1).build()),
                Arguments.of(aDiscountCard().id(2L).discount(2).build()),
                Arguments.of(aDiscountCard().id(3L).discount(3).build()),
                Arguments.of(aDiscountCard().id(4L).discount(4).build()),
                Arguments.of(aDiscountCard().id(5L).discount(5).build()),
                Arguments.of(aDiscountCard().id(6L).discount(6).build()),
                Arguments.of(aDiscountCard().id(7L).discount(7).build()),
                Arguments.of(aDiscountCard().id(8L).discount(8).build()),
                Arguments.of(aDiscountCard().id(9L).discount(9).build()),
                Arguments.of(aDiscountCard().id(10L).discount(10).build())
        );
    }
}
