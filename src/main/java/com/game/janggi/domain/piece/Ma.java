package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

public class Ma extends Piece {
    private Ma(PiecePosition piecePosition, TeamType teamType) {
        super(piecePosition, teamType);
    }

    public static Ma create(int rowIndex, int colIndex, TeamType teamType) {
        return new Ma(PiecePosition.create(rowIndex, colIndex), teamType);
    }

    @Override
    public String printPieceName() {
        return "마";
    }
}
