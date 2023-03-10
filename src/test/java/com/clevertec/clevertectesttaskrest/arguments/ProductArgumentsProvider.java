package com.clevertec.clevertectesttaskrest.arguments;

import com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.clevertec.clevertectesttaskrest.builder.impl.ProductTestBuilder.*;

public class ProductArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(aProduct().id(1L).name("name-1").price(BigDecimal.valueOf(1.0)).build()),
                Arguments.of(aProduct().id(2L).name("name-2").price(BigDecimal.valueOf(2.0)).build()),
                Arguments.of(aProduct().id(3L).name("name-3").price(BigDecimal.valueOf(3.0)).build()),
                Arguments.of(aProduct().id(4L).name("name-4").price(BigDecimal.valueOf(4.0)).build()),
                Arguments.of(aProduct().id(5L).name("name-5").price(BigDecimal.valueOf(5.0)).build()),
                Arguments.of(aProduct().id(6L).name("name-6").price(BigDecimal.valueOf(6.0)).build()),
                Arguments.of(aProduct().id(7L).name("name-7").price(BigDecimal.valueOf(7.0)).build()),
                Arguments.of(aProduct().id(8L).name("name-8").price(BigDecimal.valueOf(8.0)).build()),
                Arguments.of(aProduct().id(9L).name("name-9").price(BigDecimal.valueOf(9.0)).build()),
                Arguments.of(aProduct().id(10L).name("name-10").price(BigDecimal.valueOf(10.0)).build())
        );
    }
}
