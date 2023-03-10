package com.clevertec.clevertectesttaskrest.jsonlib.deserializer;

import com.clevertec.clevertectesttaskrest.arguments.testobject.UserTestObjectArgumentsProvider;
import com.clevertec.clevertectesttaskrest.jsonlib.serializer.JsonSerializer;
import com.clevertec.clevertectesttaskrest.testobject.UserTestObject;
import com.google.gson.Gson;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonDeserializerTest {
    @ParameterizedTest
    @ArgumentsSource(UserTestObjectArgumentsProvider.class)
    void checkDeserializeShouldDeserializeObject(UserTestObject userTestObject) throws IllegalAccessException,
            NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        String json = JsonSerializer.getInstance().serialize(userTestObject);
        assertEquals(new Gson().fromJson(json, UserTestObject.class),
                JsonDeserializer.getInstance().deserialize(json, UserTestObject.class));
    }
}