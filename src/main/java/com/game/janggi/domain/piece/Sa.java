package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;

public class Sa extends Piece {
    private Sa(PiecePosition piecePosition) {
        super(piecePosition);
    }

    public static Sa create(int rowIndex, int colIndex) {
        return new Sa(PiecePosition.create(rowIndex, colIndex));
    }
}
