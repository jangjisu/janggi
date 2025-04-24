package com.game.janggi.domain.team;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TeamType {
    CHO("초"),
    HAN("한");

    private final String description;
}
