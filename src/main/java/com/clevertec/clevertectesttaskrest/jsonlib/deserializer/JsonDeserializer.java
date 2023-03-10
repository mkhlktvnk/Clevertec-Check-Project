package com.clevertec.clevertectesttaskrest.jsonlib.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor(staticName = "getInstance")
public class JsonDeserializer {
    public <T> T deserialize(String json, Class<T> tClass) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        T target = tClass.getDeclaredConstructor().newInstance();
        for (var jsonEntry : jsonObject.entrySet()) {
            Field field = tClass.getDeclaredField(jsonEntry.getKey());
            field.setAccessible(true);
            if (jsonEntry.getValue().isJsonArray()) {
                ParameterizedType listType = (ParameterizedType) field.getGenericType();
                Class<?> actualTypeArgument = (Class<?>) listType.getActualTypeArguments()[0];
                field.set(target, mapToSequence(jsonEntry.getValue().getAsJsonArray(), field.getType(),
                        actualTypeArgument)
                );
            } else {
                field.set(target, deserializeSingleObject(jsonEntry.getValue(), field.getType()));
            }
            field.setAccessible(false);
        }
        return target;
    }

    private Object deserializeSingleObject(JsonElement value, Class<?> type) throws NoSuchFieldException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (value.isJsonNull()) {
            return null;
        } else if (value.isJsonPrimitive()) {
            return deserializePrimitiveValue(value.getAsJsonPrimitive(), type);
        } else {
            return deserialize(value.toString(), type);
        }
    }

    private Object deserializePrimitiveValue(JsonElement value, Class<?> type) {
        if (type == Integer.class || type == int.class) {
            return value.getAsInt();
        } else if (type == Long.class || type == long.class) {
            return value.getAsLong();
        } else if (type == Float.class || type == float.class) {
            return value.getAsFloat();
        } else if (type == Double.class || type == double.class) {
            return value.getAsDouble();
        } else if (type == Boolean.class || type == boolean.class) {
            return value.getAsBoolean();
        } else if (type == String.class) {
            return value.getAsString();
        } else {
            throw new IllegalArgumentException("Cannot parse this type: " + type.getName());
        }
    }

    private Object mapToSequence(JsonArray sequence, Class<?> fieldClass, Class<?> elementType)
            throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        if (fieldClass.isArray()) {
            return mapToSequence(sequence, elementType).toArray();
        } else if (Collection.class.isAssignableFrom(fieldClass)) {
            return mapToSequence(sequence, elementType);
        } else {
            throw new IllegalArgumentException("Cannot parse this type: " + fieldClass.getName());
        }
    }

    private Collection<Object> mapToSequence(JsonArray sequence, Class<?> componentType) throws NoSuchFieldException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Object> list = new ArrayList<>();
        for (var element : sequence.asList()) {
            Object object = deserializeSingleObject(element, componentType);
            list.add(object);
        }
        return list;
    }
}
