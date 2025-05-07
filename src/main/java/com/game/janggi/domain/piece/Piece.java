package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Piece {
    protected final TeamType teamType;

    abstract String printPieceName();
}
