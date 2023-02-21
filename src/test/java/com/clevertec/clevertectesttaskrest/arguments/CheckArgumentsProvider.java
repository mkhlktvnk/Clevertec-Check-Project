package com.clevertec.clevertectesttaskrest.arguments;

import com.clevertec.clevertectesttaskrest.builder.impl.CheckTestBuilder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static com.clevertec.clevertectesttaskrest.builder.impl.CheckItemTestBuilder.aCheckItem;
import static com.clevertec.clevertectesttaskrest.builder.impl.CheckTestBuilder.*;
import static com.clevertec.clevertectesttaskrest.builder.impl.DiscountCardTestBuilder.aDiscountCard;

public class CheckArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(aCheck()
                        .id(1L)
                        .checkItems(Collections.singleton(aCheckItem().build()))
                        .discountCard(aDiscountCard().id(1L).discount(1).build())
                        .totalPrice(BigDecimal.ONE)
                        .build()),
                Arguments.of(aCheck()
                        .id(2L)
                        .checkItems(Collections.singleton(aCheckItem().build()))
                        .discountCard(aDiscountCard().id(2L).discount(2).build())
                        .totalPrice(BigDecimal.ONE)
                        .build()),
                Arguments.of(aCheck()
                        .id(3L)
                        .checkItems(Collections.singleton(aCheckItem().build()))
                        .discountCard(aDiscountCard().id(3L).discount(3).build())
                        .totalPrice(BigDecimal.ONE)
                        .build()),
                Arguments.of(aCheck()
                        .id(4L)
                        .checkItems(Collections.singleton(aCheckItem().build()))
                        .discountCard(aDiscountCard().id(4L).discount(4).build())
                        .totalPrice(BigDecimal.ONE)
                        .build()),
                Arguments.of(aCheck()
                        .id(5L)
                        .checkItems(Collections.singleton(aCheckItem().build()))
                        .discountCard(aDiscountCard().id(5L).discount(5).build())
                        .totalPrice(BigDecimal.ONE)
                        .build())
        );
    }
}
