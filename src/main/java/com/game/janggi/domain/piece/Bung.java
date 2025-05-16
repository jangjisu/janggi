package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;

public class Bung extends Piece {
    protected Bung(TeamType teamType) {
        super(teamType);
    }

    public static Bung create(TeamType teamType) {
        return new Bung(teamType);
    }

    @Override
    public String printPieceName() {
        return "병";
    }

    @Override
    protected List<PiecePosition> getMovablePositions(PiecePosition currentPosition) {
        return List.of();
    }

    @Override
    public boolean canMove(PiecePosition currentPosition, PiecePosition targetPosition) {
        return false;
    }
}
