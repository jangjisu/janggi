package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.ChaMovePosition;
import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class Cha extends Piece {
    protected Cha(TeamType teamType) {
        super(teamType);
    }

    private final MovePosition movePosition = new ChaMovePosition();

    public static Cha create(TeamType teamType) {
        return new Cha(teamType);
    }

    @Override
    public String printPieceName() {
        return "차";
    }

    @Override
    protected List<PiecePosition> getMoveAblePositions(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition) {
        return movePosition.getMoveablePosition(pieceMap, currentPosition);
    }
}
