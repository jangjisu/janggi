package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;

public class Jol extends Piece {
    private Jol(PiecePosition piecePosition) {
        super(piecePosition);
    }

    public static Jol create(int rowIndex, int colIndex) {
        return new Jol(PiecePosition.create(rowIndex, colIndex));
    }
}
