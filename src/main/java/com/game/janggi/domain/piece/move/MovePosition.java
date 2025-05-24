package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.List;
import java.util.Map;

public abstract class MovePosition {
    public abstract List<PiecePosition> getMovablePosition(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition);

    private boolean isTherePiece(Map<PiecePosition, Piece> pieces, PiecePosition willMovePosition) {
        return pieces.containsKey(willMovePosition);
    }

    private boolean isThereEmpty(Map<PiecePosition, Piece> pieces, PiecePosition willMovePosition) {
        return !isTherePiece(pieces, willMovePosition);
    }

    private boolean isPieceOfSameTeam(Piece piece, TeamType currentTurnTeamType) {
        return piece.isSameTeam(currentTurnTeamType);
    }

    private boolean isPieceOfDifferentTeam(Piece piece, TeamType currentTurnTeamType) {
        return !isPieceOfSameTeam(piece, currentTurnTeamType);
    }

    protected TeamType getSelectedPieceTeamType(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        return pieces.get(currentPosition).getTeamType();
    }

    protected boolean isEmptyOrEnemyPiece(Map<PiecePosition, Piece> pieces, PiecePosition willMovePosition, TeamType currentTurnTeamType) {
        if (isThereEmpty(pieces, willMovePosition)) {
            return true;
        }

        return isPieceOfDifferentTeam(pieces.get(willMovePosition), currentTurnTeamType);
    }


}
