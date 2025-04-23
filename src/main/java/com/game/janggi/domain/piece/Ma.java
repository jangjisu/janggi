package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;

public class Ma extends Piece {
    private Ma(PiecePosition piecePosition) {
        super(piecePosition);
    }

    public static Ma create(int rowIndex, int colIndex) {
        return new Ma(PiecePosition.create(rowIndex, colIndex));
    }
}
