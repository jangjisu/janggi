package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.PiecePosition;

import java.util.List;

public interface MovePosition {
    List<PiecePosition> getMovablePosition(PiecePosition currentPosition);
}
