package com.game.janggi.util;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.exception.NeedStopException;
import com.game.janggi.exception.RecoverableException;

import java.util.Objects;
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
        if (Objects.isNull(userInput)) {
            throw new RecoverableException("값을 입력해 주세요 예)A0");
        }

        if (userInput.length() != 2) {
            throw new RecoverableException("2자리 입력해 주세요 예)A0");
        }

        char colChar = userInput.charAt(0);
        char rowChar = userInput.charAt(1);

        if (!Character.isLetter(colChar)) {
            throw new RecoverableException("첫번째 값은 알파벳 대문자여야 합니다 (예: 'A')");
        }

        colChar = Character.toUpperCase(colChar);

        if (!Character.isDigit(rowChar)) {
            throw new RecoverableException("두번째 값은 숫자여야 합니다 (예: '0')");
        }

        int columnInput = colChar - 'A';
        int rowInput = Character.getNumericValue(rowChar); // Safely convert char digit to int

        return PiecePosition.create(rowInput, columnInput);
    }
}
