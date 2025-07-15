package com.game.janggi.domain.board;

import com.game.janggi.domain.team.TeamType;

public enum GameStatus {
    CHO_WIN,
    HAN_WIN,
    IN_PROGRESS,
    ;


    public GameStatus changeGameEnd(TeamType winTeamType) {
        if (winTeamType == TeamType.HAN) {
            return HAN_WIN;
        }

        return CHO_WIN;
    }
}
