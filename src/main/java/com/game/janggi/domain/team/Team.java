package com.game.janggi.domain.team;

import com.game.janggi.domain.piece.Pieces;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Team {
    protected final Pieces pieces;
}
