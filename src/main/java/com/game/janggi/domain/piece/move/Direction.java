package com.game.janggi.domain.piece.move;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1);

    private final int row;
    private final int col;
}
