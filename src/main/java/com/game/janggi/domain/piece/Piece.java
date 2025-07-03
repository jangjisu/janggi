package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.move.MovePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Piece {
    protected final TeamType teamType;
    protected final MovePosition movePosition;

    public abstract String printPieceName();

    protected List<PiecePosition> getMoveAblePositions(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition) {
        return movePosition.getMoveablePosition(pieceMap, currentPosition);
    }

    public boolean canMoveTo(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition, PiecePosition targetPosition) {
        return getMoveAblePositions(pieceMap, currentPosition).contains(targetPosition);
    }

    public boolean canMove(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition) {
        return getMoveAblePositions(pieceMap, currentPosition).isEmpty();
    }

    public boolean isSameTeam(TeamType teamType) {
        return this.teamType == teamType;
    }

    public boolean isDifferentTeam(TeamType teamType) {
        return !isSameTeam(teamType);
    }
}
