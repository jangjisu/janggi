package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Piece {
    protected final PiecePosition piecePosition;
    protected final TeamType teamType;

    public boolean isAtPosition(int rowIndex, int colIndex) {
        return piecePosition.isAtPosition(rowIndex, colIndex);
    }

    abstract public String printPieceName();
}
