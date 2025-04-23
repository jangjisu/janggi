package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;

public class King extends Piece {
    private King(PiecePosition piecePosition) {
        super(piecePosition);
    }

    public static King create(int rowIndex, int colIndex) {
        return new King(PiecePosition.create(rowIndex, colIndex));
    }
}
