package com.game.janggi.domain.piece.position;

import com.game.janggi.domain.piece.move.Movement;

public interface MoveAble {
    boolean canMove(Movement movement);
}
