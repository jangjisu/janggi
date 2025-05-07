package com.game.janggi.util;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.exception.NeedStopException;

import java.util.function.IntSupplier;

public class ConvertUtil {
    private ConvertUtil() {
        throw new NeedStopException("Utility class");
    }

    public static int convertToInt(String input, IntSupplier defaultSupplier) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return defaultSupplier.getAsInt();
        }
    }

    public static PiecePosition convertToPiecePosition(String userInput) {
        int columnInput = userInput.charAt(0) - 'A';
        int rowInput = userInput.charAt(1) - '0';

        return PiecePosition.create(rowInput, columnInput);
    }
}
