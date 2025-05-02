package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

public class Sa extends Piece {
    private Sa(PiecePosition piecePosition, TeamType teamType) {
        super(piecePosition, teamType);
    }

    public static Sa create(int rowIndex, int colIndex, TeamType teamType) {
        return new Sa(PiecePosition.create(rowIndex, colIndex), teamType);
    }

    @Override
    public String printPieceName() {
        return "사";
    }
}
