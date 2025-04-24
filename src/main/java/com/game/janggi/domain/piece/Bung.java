package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

public class Bung extends Piece {
    private Bung(PiecePosition piecePosition, TeamType teamType) {
        super(piecePosition, teamType);
    }

    public static Bung create(int rowIndex, int colIndex, TeamType teamType) {
        return new Bung(PiecePosition.create(rowIndex, colIndex), teamType);
    }
}
