package com.game.janggi.domain.board;

import com.game.janggi.domain.team.TeamType;
import lombok.Getter;

public enum GameStatus {
    CHO_WIN("초가 이겼습니다."),
    HAN_WIN("한이 이겼습니다."),
    IN_PROGRESS("게임이 진행 중입니다."),
    ;

    @Getter
    private final String comment;

    GameStatus(String comment) {
        this.comment = comment;
    }

    public GameStatus changeGameEnd(TeamType winTeamType) {
        if (winTeamType == TeamType.HAN) {
            return HAN_WIN;
        }

        return CHO_WIN;
    }
}
