package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;

public class Ma extends Piece {
    protected Ma(TeamType teamType) {
        super(teamType);
    }

    public static Ma create(TeamType teamType) {
        return new Ma(teamType);
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
        return "마";
    }
}
