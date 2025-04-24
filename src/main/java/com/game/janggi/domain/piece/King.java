package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

public class King extends Piece {
    private King(PiecePosition piecePosition, TeamType teamType) {
        super(piecePosition, teamType);
    }

    public static King create(int rowIndex, int colIndex, TeamType teamType) {
        return new King(PiecePosition.create(rowIndex, colIndex), teamType);
    }
}
