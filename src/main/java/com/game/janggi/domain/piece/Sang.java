package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;

public class Sang extends Piece {
    private Sang(PiecePosition piecePosition) {
        super(piecePosition);
    }

    public static Sang create(int rowIndex, int colIndex) {
        return new Sang(PiecePosition.create(rowIndex, colIndex));
    }
}
