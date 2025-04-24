package com.game.janggi.domain.formation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FormationType {
    SANG_MA_MA_SANG("상마마상"),
    SANG_MA_SANG_MA("상마상마"),
    MA_SANG_MA_SANG("마상마상"),
    MA_SANG_SANG_MA("마상상마");

    private final String description;
}
