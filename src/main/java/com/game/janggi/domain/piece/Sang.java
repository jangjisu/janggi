package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.SangMovePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class Sang extends Piece {
    protected Sang(TeamType teamType) {
        super(teamType);
    }

    private final SangMovePosition movePosition = new SangMovePosition();

    public static Sang create(TeamType teamType) {
        return new Sang(teamType);
    }

    @Override
    public String printPieceName() {
        return "상";
    }

    @Override
    protected List<PiecePosition> getMoveAblePositions(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition) {
        return movePosition.getMoveablePosition(pieceMap, currentPosition);
    }
}
