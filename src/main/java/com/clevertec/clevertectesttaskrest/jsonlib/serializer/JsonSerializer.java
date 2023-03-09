package com.clevertec.clevertectesttaskrest.jsonlib.serializer;

import com.clevertec.clevertectesttaskrest.jsonlib.constant.JsonSigns;
import com.clevertec.clevertectesttaskrest.jsonlib.joiner.JoiningSigns;
import com.clevertec.clevertectesttaskrest.jsonlib.joiner.JsonDataJoiner;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonSerializer {
    private JsonSerializer() {
    }

    public static JsonSerializer getInstance() {
        return new JsonSerializer();
    }

    public String serialize(Object object) throws IllegalAccessException {
        return serializeObjectDependOnType(object).toString();
    }

    private StringBuilder serializeObjectDependOnType(Object object) throws IllegalAccessException {
        if (object == null) {
            return new StringBuilder(JsonSigns.NULL);
        } else if (object.getClass().isArray()) {
            return serializeArrayToJson(object);
        } else if (Collection.class.isAssignableFrom(object.getClass())) {
            return serializeCollectionToJson(object);
        } else if (Map.class.isAssignableFrom(object.getClass())) {
            return serializeMapToJson(object);
        } else if (isObjectOfClass(object)) {
            return serializeObjectOfClassToJson(object);
        } else {
            if (isValueNeedDoubleQuote(object.getClass())) {
                return new StringBuilder(String.format(JsonSigns.DOUBLE_QUOTE, object));
            }
            return new StringBuilder(object.toString());
        }
    }

    private static boolean isObjectOfClass(Object object) {
        return !object.getClass().isPrimitive()
                && !Number.class.isAssignableFrom(object.getClass())
                && !Collection.class.isAssignableFrom(object.getClass())
                && !Enum.class.isAssignableFrom(object.getClass())
                && object.getClass() != Array.class
                && object.getClass() != String.class
                && object.getClass() != StringBuffer.class
                && object.getClass() != Boolean.class
                && object.getClass() != Character.class;
    }

    private static boolean isValueNeedDoubleQuote(Class<?> fieldType) {
        return (!fieldType.isPrimitive() || fieldType == char.class)
                && !Number.class.isAssignableFrom(fieldType)
                && fieldType != Boolean.class;
    }

    private StringBuilder serializeArrayToJson(Object object) throws IllegalAccessException {
        JsonDataJoiner jsonDataJoiner = new JsonDataJoiner(JoiningSigns.OBJECTS_SEQUENCE);
        int length = Array.getLength(object);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(object, i);
            String serializedElement = serializeObjectDependOnType(element).toString();
            if (i == length - 1) {
                jsonDataJoiner.join(serializedElement);
            } else {
                jsonDataJoiner.joinWithDelimiter(serializedElement);
            }
        }
        return jsonDataJoiner.getResult();
    }

    private StringBuilder serializeCollectionToJson(Object object) throws IllegalAccessException {
        JsonDataJoiner jsonDataJoiner = new JsonDataJoiner(JoiningSigns.OBJECTS_SEQUENCE);
        AtomicInteger currentIndex = new AtomicInteger(0);
        Collection<?> collection = (Collection<?>) object;
        for (var element : collection) {
            String serializedElement = serializeObjectDependOnType(element).toString();
            if (currentIndex.incrementAndGet() == collection.size()) {
                jsonDataJoiner.join(serializedElement);
            } else {
                jsonDataJoiner.joinWithDelimiter(serializedElement);
            }
        }
        return jsonDataJoiner.getResult();
    }

    private StringBuilder serializeMapToJson(Object object) throws IllegalAccessException {
        JsonDataJoiner jsonDataJoiner = new JsonDataJoiner(JoiningSigns.SINGLE_OBJECT);
        AtomicInteger currentIndex = new AtomicInteger(0);
        Map<?, ?> map = (Map<?, ?>) object;
        for (var entry : map.entrySet()) {
            String convertedKey = String.format(JsonSigns.DOUBLE_QUOTE, entry.getKey());
            String convertedValue = serializeObjectDependOnType(entry.getValue()).toString();
            jsonDataJoiner.join(convertedKey).join(JsonSigns.COLON);
            if (currentIndex.incrementAndGet() == map.size()) {
                jsonDataJoiner.join(convertedValue);
            } else {
                jsonDataJoiner.joinWithDelimiter(convertedValue);
            }
        }
        return jsonDataJoiner.getResult();
    }

    private StringBuilder serializeObjectOfClassToJson(Object object) throws IllegalAccessException {
        JsonDataJoiner jsonDataJoiner = new JsonDataJoiner(JoiningSigns.SINGLE_OBJECT);
        int index = 0;
        Field[] fields = object.getClass().getDeclaredFields();
        for (var field : fields) {
            field.setAccessible(true);
            String fieldName = String.format(JsonSigns.DOUBLE_QUOTE, field.getName());
            String fieldValue = serializeObjectDependOnType(field.get(object)).toString();
            jsonDataJoiner.join(fieldName).join(JsonSigns.COLON);
            if (index == fields.length - 1) {
                jsonDataJoiner.join(fieldValue);
            } else {
                jsonDataJoiner.joinWithDelimiter(fieldValue);
            }
            field.setAccessible(false);
            index++;
        }
        return jsonDataJoiner.getResult();
    }
}
