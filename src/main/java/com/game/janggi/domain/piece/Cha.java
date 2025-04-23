package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;

public class Cha extends Piece {
    private Cha(PiecePosition piecePosition) {
        super(piecePosition);
    }

    public static Cha create(int rowIndex, int colIndex) {
        return new Cha(PiecePosition.create(rowIndex, colIndex));
    }
}
