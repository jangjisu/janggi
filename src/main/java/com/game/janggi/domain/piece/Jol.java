package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;

public class Jol extends Piece {
    protected Jol(TeamType teamType) {
        super(teamType);
    }

    public static Jol create(TeamType teamType) {
        return new Jol(teamType);
    }

    @Override
    public String printPieceName() {
        return "졸";
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
