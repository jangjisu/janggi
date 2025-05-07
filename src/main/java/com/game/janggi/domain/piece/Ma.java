package com.game.janggi.domain.piece;

import com.game.janggi.domain.team.TeamType;

public class Ma extends Piece {
    protected Ma(TeamType teamType) {
        super(teamType);
    }

    public static Ma create(TeamType teamType) {
        return new Ma(teamType);
    }

    @Override
    public String printPieceName() {
        return "마";
    }
}
