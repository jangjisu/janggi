package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Piece {
    @Getter
    protected final TeamType teamType;

    public abstract String printPieceName();

    protected abstract List<PiecePosition> getMovablePositions(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition);

    public boolean canMoveTo(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition, PiecePosition targetPosition) {
        return getMovablePositions(pieceMap, currentPosition).contains(targetPosition);
    }

    public boolean canMove(Map<PiecePosition, Piece> pieceMap, PiecePosition currentPosition) {
        return getMovablePositions(pieceMap, currentPosition).isEmpty();
    }

    public boolean isSameTeam(TeamType teamType) {
        return this.teamType == teamType;
    }

    public boolean isDifferentTeam(TeamType teamType) {
        return !isSameTeam(teamType);
    }
}
