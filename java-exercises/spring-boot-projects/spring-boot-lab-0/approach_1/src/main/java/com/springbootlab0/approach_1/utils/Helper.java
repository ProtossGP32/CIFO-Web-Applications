package com.springbootlab0.approach_1.utils;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Helper {
    private Helper() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * Creates a random UUID and returns it as a String
     * @return String: a new UUID
     */
    public static String createUUID () {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * Return the declared fields from both the class and its inherited classes
     * @param tClass
     * @return
     */
    public static List<Field> getDeclaredFields(Class<?> tClass) {
        List<Field> fields = new LinkedList<>();
        while (tClass != null) {
            fields.addAll(Arrays.asList(tClass.getDeclaredFields()));
            tClass = tClass.getSuperclass();
        }
        return fields;
    }


    // Generics

    /**
     * Return a class object from a JSON string
     * @param objectJSON JSON with the attributes of the object
     * @param clazz the class of the object to return
     * @return the class object with the given attributes
     * @param <T> Generic class
     */
    public static <T> T createFromJson(String objectJSON, Class<T> clazz) {
        // First, convert the JSON into a Map
        JsonParser bodyParser = JsonParserFactory.getJsonParser();
        Map<String, Object> bodyMap = bodyParser.parseMap(objectJSON);
        // Then, create a new User with all the fields of the JSON
        try {
            T newObject = clazz.getDeclaredConstructor().newInstance();
            // Retrieve all fields from the class, even the inherited ones
            List<Field> fields = Helper.getDeclaredFields(clazz);
            fields.stream()
                    .filter(field -> {
                        // Only get fields that exist in the User class
                        String fieldName = field.getName();
                        Object userValue = bodyMap.getOrDefault(fieldName, null);
                        return userValue != null &&
                                (!(userValue instanceof Number) || ((Number) userValue).intValue() != 0) &&
                                (!(userValue instanceof Boolean) || (Boolean) userValue);
                    })
                    .forEach(field -> {
                        try {
                            // Set field as accessible
                            field.setAccessible(true);
                            Object value = bodyMap.get(field.getName());
                            field.set(newObject, value);
                        } catch (IllegalAccessException e) {
                            // TODO: Handle the stream exception
                        }
                    });
            // Return the newly created User
            return newObject;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
