package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class Po extends Piece {
    protected Po(TeamType teamType) {
        super(teamType);
    }

    public static Po create(TeamType teamType) {
        return new Po(teamType);
    }

    @Override
    public String printPieceName() {
        return "포";
    }

    @Override
    protected List<PiecePosition> getMovablePositions(PiecePosition currentPosition) {
        return List.of();
    }

    @Override
    public boolean canMove(PiecePosition currentPosition, PiecePosition targetPosition, Map<PiecePosition, Piece> pieceMap) {
        return false;
    }
}
