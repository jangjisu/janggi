package com.game.janggi.domain.piece.position;

import com.game.janggi.domain.piece.move.Directions;

public interface MoveAble {
    boolean canMove(Directions directions);
}
