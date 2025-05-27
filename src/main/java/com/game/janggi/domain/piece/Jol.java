package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.JolMovePosition;
import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public class Jol extends Piece {
    protected Jol(TeamType teamType) {
        super(teamType);
    }

    private final MovePosition movePosition = new JolMovePosition();

    public static Jol create(TeamType teamType) {
        return new Jol(teamType);
    }

    @Override
    public String printPieceName() {
        return "졸";
    }

    @Override
    protected List<PiecePosition> getMoveAblePositions(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition) {
        return movePosition.getMoveablePosition(pieceMap, currentPosition);
    }
}
