package com.clevertec.clevertectesttaskrest.arguments.testobject;

import com.clevertec.clevertectesttaskrest.testobject.ContactTestObject;
import com.clevertec.clevertectesttaskrest.testobject.UserTestObject;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class UserTestObjectArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(UserTestObject.builder().id(1L).username("username-1").contacts(List.of(
                                ContactTestObject.builder().id(1L).phone("123123").build(),
                                ContactTestObject.builder().id(2L).phone("123123").build(),
                                ContactTestObject.builder().id(3L).phone("123123").build()
                        )).build()
                ),
                Arguments.of(UserTestObject.builder().id(2L).username("username-2").contacts(List.of(
                                ContactTestObject.builder().id(1L).phone("123123").build(),
                                ContactTestObject.builder().id(2L).phone("123123").build(),
                                ContactTestObject.builder().id(3L).phone("123123").build()
                        )).build()
                ),
                Arguments.of(UserTestObject.builder().id(3L).username("username-3").contacts(List.of(
                                ContactTestObject.builder().id(1L).phone("123123").build(),
                                ContactTestObject.builder().id(2L).phone("123123").build(),
                                ContactTestObject.builder().id(3L).phone("123123").build()
                        )).build()
                ),
                Arguments.of(UserTestObject.builder().id(4L).username("username-4").contacts(List.of(
                                ContactTestObject.builder().id(1L).phone("123123").build(),
                                ContactTestObject.builder().id(2L).phone("123123").build(),
                                ContactTestObject.builder().id(3L).phone("123123").build()
                        )).build()
                ),
                Arguments.of(UserTestObject.builder().id(5L).username("username-5").contacts(List.of(
                                ContactTestObject.builder().id(1L).phone("123123").build(),
                                ContactTestObject.builder().id(2L).phone("123123").build(),
                                ContactTestObject.builder().id(3L).phone("123123").build()
                        )).build()
                ),
                Arguments.of(UserTestObject.builder().id(6L).username("username-6").contacts(List.of(
                                ContactTestObject.builder().id(1L).phone("123123").build(),
                                ContactTestObject.builder().id(2L).phone("123123").build(),
                                ContactTestObject.builder().id(3L).phone("123123").build()
                        )).build()
                ),
                Arguments.of(UserTestObject.builder().id(7L).username("username-7").contacts(List.of(
                                ContactTestObject.builder().id(1L).phone("123123").build(),
                                ContactTestObject.builder().id(2L).phone("123123").build(),
                                ContactTestObject.builder().id(3L).phone("123123").build()
                        )).build()
                ),
                Arguments.of(UserTestObject.builder().id(8L).username("username-8").contacts(List.of(
                                ContactTestObject.builder().id(1L).phone("123123").build(),
                                ContactTestObject.builder().id(2L).phone("123123").build(),
                                ContactTestObject.builder().id(3L).phone("123123").build()
                        )).build()
                ),
                Arguments.of(UserTestObject.builder().id(9L).username("username-9").contacts(List.of(
                                ContactTestObject.builder().id(1L).phone("123123").build(),
                                ContactTestObject.builder().id(2L).phone("123123").build(),
                                ContactTestObject.builder().id(3L).phone("123123").build()
                        )).build()
                ),
                Arguments.of(UserTestObject.builder().id(10L).username("username-10").contacts(List.of(
                                ContactTestObject.builder().id(1L).phone("123123").build(),
                                ContactTestObject.builder().id(2L).phone("123123").build(),
                                ContactTestObject.builder().id(3L).phone("123123").build()
                        )).build()
                )
        );
    }
}
