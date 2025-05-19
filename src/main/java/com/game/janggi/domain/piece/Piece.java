package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Piece {
    protected final TeamType teamType;

    public abstract String printPieceName();

    protected abstract List<PiecePosition> getMovablePositions(PiecePosition currentPosition);

    public abstract boolean canMove(PiecePosition currentPosition, PiecePosition targetPosition);

    public boolean isSameTeam(TeamType teamType) {
        return this.teamType == teamType;
    }

    public boolean isDifferentTeam(TeamType teamType) {
        return !isSameTeam(teamType);
    }
}
