package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

public class Cha extends Piece {
    private Cha(PiecePosition piecePosition, TeamType teamType) {
        super(piecePosition, teamType);
    }

    public static Cha create(int rowIndex, int colIndex, TeamType teamType) {
        return new Cha(PiecePosition.create(rowIndex, colIndex), teamType);
    }

    @Override
    public String printPieceName() {
        return "차";
    }
}
