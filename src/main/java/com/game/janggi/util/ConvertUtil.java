package com.game.janggi.util;

import java.util.function.Supplier;

public class ConvertUtil {
    private ConvertUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static int convertToInt(String input, Supplier<Integer> supplier) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: " + input, e);
        }
    }
}
