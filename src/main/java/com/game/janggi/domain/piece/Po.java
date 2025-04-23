package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;

public class Po extends Piece {
    private Po(PiecePosition piecePosition) {
        super(piecePosition);
    }

    public static Po create(int rowIndex, int colIndex) {
        return new Po(PiecePosition.create(rowIndex, colIndex));
    }
}
