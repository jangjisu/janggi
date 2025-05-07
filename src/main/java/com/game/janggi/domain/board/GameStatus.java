package com.game.janggi.domain.board;

public enum GameStatus {
    CHO_WIN("초가 이겼습니다."),
    HAN_WIN("한이 이겼습니다."),
    IN_PROGRESS("게임이 진행 중입니다."),;

    private final String comment;

    GameStatus(String comment) {
        this.comment = comment;
    }
}
