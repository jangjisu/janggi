package com.game.janggi.domain.piece;

import com.game.janggi.domain.piece.position.PiecePosition;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Piece {
    protected final PiecePosition piecePosition;
}
