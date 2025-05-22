package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public abstract class MovePosition {
    abstract List<PiecePosition> getMovablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition, TeamType teamType);

    protected boolean isTherePiece(Map<PiecePosition, Piece> pieces, PiecePosition willMovePosition) {
        return pieces.containsKey(willMovePosition);
    }

    protected boolean isPieceOfSameTeam(Piece piece, TeamType currentTurnTeamType) {
        return piece.isSameTeam(currentTurnTeamType);
    }


}
