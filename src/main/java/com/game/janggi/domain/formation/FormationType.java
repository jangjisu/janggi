package com.game.janggi.domain.formation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FormationType {
    SANG_MA_MA_SANG("상마마상", 1),
    SANG_MA_SANG_MA("상마상마", 2),
    MA_SANG_MA_SANG("마상마상", 3),
    MA_SANG_SANG_MA("마상상마", 4);

    private final String description;
    private final int inputNumber;

    public static FormationType getFromInputNumber(int inputNumber) {
        for (FormationType formationType : FormationType.values()) {
            if (formationType.inputNumber == inputNumber) {
                return formationType;
            }
        }

        throw new IllegalArgumentException("Invalid input number: " + inputNumber);
    }
}
