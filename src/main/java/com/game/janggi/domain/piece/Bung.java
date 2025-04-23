package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;

public class Bung extends Piece {
    private Bung(PiecePosition piecePosition) {
        super(piecePosition);
    }

    public static Bung create(int rowIndex, int colIndex) {
        return new Bung(PiecePosition.create(rowIndex, colIndex));
    }
}
