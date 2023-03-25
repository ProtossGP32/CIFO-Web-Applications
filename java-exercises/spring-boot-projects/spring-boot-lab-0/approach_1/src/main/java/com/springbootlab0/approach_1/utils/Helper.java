package com.springbootlab0.approach_1.utils;

import java.util.UUID;

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
}
