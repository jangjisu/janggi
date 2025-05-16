package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;

public class Sang extends Piece {
    protected Sang(TeamType teamType) {
        super(teamType);
    }

    public static Sang create(TeamType teamType) {
        return new Sang(teamType);
    }

    @Override
    public String printPieceName() {
        return "상";
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
