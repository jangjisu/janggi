package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

public class Sang extends Piece {
    private Sang(PiecePosition piecePosition, TeamType teamType) {
        super(piecePosition, teamType);
    }

    public static Sang create(int rowIndex, int colIndex, TeamType teamType) {
        return new Sang(PiecePosition.create(rowIndex, colIndex), teamType);
    }
}
