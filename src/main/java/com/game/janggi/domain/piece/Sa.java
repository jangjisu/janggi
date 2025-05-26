package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.piece.move.SaMovePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class Sa extends Piece {
    protected Sa(TeamType teamType) {
        super(teamType);
    }

    private final MovePosition movePosition = new SaMovePosition();

    public static Sa create(TeamType teamType) {
        return new Sa(teamType);
    }

    @Override
    public String printPieceName() {
        return "사";
    }

    @Override
    protected List<PiecePosition> getMovablePositions(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition) {
        return movePosition.getMovablePosition(pieceMap, currentPosition);
    }
}
