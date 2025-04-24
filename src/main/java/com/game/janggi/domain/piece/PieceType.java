package com.game.janggi.domain.piece;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PieceType {
    KING("왕", 0),
    JOL("졸", 2),
    BUNG("병", 2),
    SA("사", 3),
    SANG("상", 3),
    MA("마", 5),
    PHO("포", 7),
    CHA("차", 13),
    ;

    private final String description;
    private final int score;
}
