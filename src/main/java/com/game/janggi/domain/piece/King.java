package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.KingMovePosition;
import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class King extends Piece {
    protected King(TeamType teamType) {
        super(teamType);
    }

    private final MovePosition movePosition = new KingMovePosition();

    public static King create(TeamType teamType) {
        return new King(teamType);
    }

    @Override
    public String printPieceName() {
        return "왕";
    }

    @Override
    protected List<PiecePosition> getMovablePositions(PiecePosition currentPosition) {
        return List.of();
    }

    @Override
    public boolean canMove(PiecePosition currentPosition, PiecePosition targetPosition, Map<PiecePosition, Piece> pieceMap) {
        return movePosition.getMovablePosition(pieceMap, currentPosition).contains(targetPosition);
    }
}
