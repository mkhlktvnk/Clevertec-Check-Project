package com.clevertec.clevertectesttaskrest.jsonlib.serializer;

import com.clevertec.clevertectesttaskrest.arguments.testobject.UserTestObjectArgumentsProvider;
import com.clevertec.clevertectesttaskrest.arguments.testobject.UserTestObjectArrayArgumentsProvider;
import com.clevertec.clevertectesttaskrest.arguments.testobject.UserTestObjectCollectionArgumentsProvider;
import com.clevertec.clevertectesttaskrest.arguments.testobject.UserTestObjectMapArgumentsProvider;
import com.clevertec.clevertectesttaskrest.testobject.UserTestObject;
import com.google.gson.Gson;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonSerializerTest {

    @ParameterizedTest
    @ArgumentsSource(UserTestObjectArgumentsProvider.class)
    void checkSerializeShouldSerializeTestObjects(UserTestObject userTestObject) throws IllegalAccessException {
        assertEquals(new Gson().toJson(userTestObject), JsonSerializer.getInstance().serialize(userTestObject));
    }

    @ParameterizedTest
    @ArgumentsSource(UserTestObjectArrayArgumentsProvider.class)
    void checkSerializeShouldSerializeArraysOfTestObjects(UserTestObject[] userTestObjects) throws IllegalAccessException {
        assertEquals(new Gson().toJson(userTestObjects), JsonSerializer.getInstance().serialize(userTestObjects));
    }

    @ParameterizedTest
    @ArgumentsSource(UserTestObjectCollectionArgumentsProvider.class)
    void checkSerializeShouldSerializeCollectionOfTestObjects(Collection<UserTestObject> userTestObjects) throws IllegalAccessException {
        assertEquals(new Gson().toJson(userTestObjects), JsonSerializer.getInstance().serialize(userTestObjects));
    }

    @ParameterizedTest
    @ArgumentsSource(UserTestObjectMapArgumentsProvider.class)
    void checkSerializeShouldSerializeMapsOfTestObjects(Map<String, UserTestObject> userTestObjectMap) throws IllegalAccessException {
        assertEquals(new Gson().toJson(userTestObjectMap), JsonSerializer.getInstance().serialize(userTestObjectMap));
    }
}