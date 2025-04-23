package com.game.janggi.domain.piece.position;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PiecePosition {
    private final int rowIndex;
    private final int colIndex;

    public static PiecePosition create(int rowIndex, int colIndex) {
        return new PiecePosition(rowIndex, colIndex);
    }

}
