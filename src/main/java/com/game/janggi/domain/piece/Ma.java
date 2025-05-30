package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MaMovePosition;
import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class Ma extends Piece {
    protected Ma(TeamType teamType) {
        super(teamType);
    }

    private final MovePosition movePosition = new MaMovePosition();

    public static Ma create(TeamType teamType) {
        return new Ma(teamType);
    }

    @Override
    public String printPieceName() {
        return "마";
    }

    @Override
    protected List<PiecePosition> getMoveAblePositions(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition) {
        return movePosition.getMoveablePosition(pieceMap, currentPosition);
    }
}
