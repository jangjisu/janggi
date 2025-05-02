package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

public class Po extends Piece {
    private Po(PiecePosition piecePosition, TeamType teamType) {
        super(piecePosition, teamType);
    }

    public static Po create(int rowIndex, int colIndex, TeamType teamType) {
        return new Po(PiecePosition.create(rowIndex, colIndex), teamType);
    }

    @Override
    public String printPieceName() {
        return "포";
    }
}
