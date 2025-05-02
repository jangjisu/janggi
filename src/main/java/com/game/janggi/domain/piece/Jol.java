package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

public class Jol extends Piece {
    private Jol(PiecePosition piecePosition, TeamType teamType) {
        super(piecePosition, teamType);
    }

    public static Jol create(int rowIndex, int colIndex, TeamType teamType) {
        return new Jol(PiecePosition.create(rowIndex, colIndex), teamType);
    }

    @Override
    public String printPieceName() {
        return "졸";
    }
}
