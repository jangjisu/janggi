package com.game.janggi.domain.team;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeamType {
    CHO("초"),
    HAN("한");

    private final String teamName;

    public TeamType getOppositeTeam() {
        if (this == CHO) {
            return HAN;
        }
        return CHO;
    }


}
