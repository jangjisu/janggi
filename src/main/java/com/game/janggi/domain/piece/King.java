package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;

public class King extends Piece {
    protected King(TeamType teamType) {
        super(teamType);
    }

    public static King create(TeamType teamType) {
        return new King(teamType);
    }

    @Override
    protected List<PiecePosition> getMovablePositions(PiecePosition currentPosition) {
        return List.of();
    }

    @Override
    public boolean canMove(PiecePosition currentPosition, PiecePosition targetPosition) {
        return false;
    }

    @Override
    public String printPieceName() {
        return "왕";
    }
}
