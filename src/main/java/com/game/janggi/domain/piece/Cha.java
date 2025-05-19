package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;

public class Cha extends Piece {
    protected Cha(TeamType teamType) {
        super(teamType);
    }

    public static Cha create(TeamType teamType) {
        return new Cha(teamType);
    }

    @Override
    public String printPieceName() {
        return "차";
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
